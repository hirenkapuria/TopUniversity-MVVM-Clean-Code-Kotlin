package com.demotest.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demotest.data.home.entity.UniversityEntity
import com.demotest.domin.university.usecase.GetLocalPagingUniversityListUseCase
import com.demotest.domin.university.usecase.GetLocalUniversityListUseCase
import com.demotest.domin.university.usecase.GetUniversityListUseCase
import com.demotest.extenstion.update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UniversityViewModel(
    private val getUniversityListUseCase: GetUniversityListUseCase,
    private val getLocalUniversityListUseCase: GetLocalUniversityListUseCase,
    private val getLocalPagingUniversityListUseCase: GetLocalPagingUniversityListUseCase
) : ViewModel() {

    private val _universitiesFlow = MutableStateFlow<Flow<PagingData<UniversityEntity>>>(emptyFlow())

    val universities: StateFlow<PagingData<UniversityEntity>> =
        _universitiesFlow
            .flatMapLatest { it }
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    data class UiState(
        val loading: Boolean = false,
        val isEmpty: Boolean = false,
        val error: String?= null,
        //val universityList: List<UniversityItem>? = emptyList(),
        val route: Route? = null
    )

    sealed class Route {
        data class UniversityDetail(var websiteUrl: String?): Route()
    }

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState

    fun fetchUniversities(){
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(loading = true) }
                 getUniversityListUseCase.getUniversities()
                _universitiesFlow.value = getLocalPagingUniversityListUseCase.getUniversities()
                _uiState.update { it.copy(loading = false, isEmpty = false) }


                /*val universities = getLocalUniversityListUseCase.getUniversities()
                if (universities.isEmpty()){
                    _uiState.update {
                        it.copy(
                            loading = false,
                            isEmpty = true
                        )
                    }
                }else{
                    _uiState.update {
                        it.copy(
                            loading = false,
                            isEmpty = false,
                            universityList = universities
                        )
                    }
                }*/
            } catch (t:Throwable){
                _uiState.update { it.copy(loading = false, error = t.localizedMessage) }
            }
        }
    }

    fun onUniversityClick(website: String?){
        _uiState.update {
            it.copy(
                route = Route.UniversityDetail(website)
            )
        }
    }

    fun onRouted() {
        _uiState.update { it.copy(route = null) }
    }
}