package com.demotest.presentation.universityDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demotest.extenstion.update
import com.demotest.presentation.home.UniversityViewModel.Route
import kotlinx.coroutines.launch

class UniversityDetailsFragmentViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    companion object {
        const val WEB_SITE = "website"
    }

    private val _uiState = MutableLiveData(UiState())
    val uiState: LiveData<UiState> = _uiState
    private val website = savedStateHandle.get<String>(WEB_SITE)

    data class UiState(
        val error: String? = null,
        val website: String? = null,
        val route: Route? = null
    )

    init {
        viewModelScope.launch {
            try {
                website?.let {
                    _uiState.update {
                        it.copy(website = website)
                    }

                } ?: run {
                    _uiState.update {
                        it.copy(
                            error = "Not found"
                        )
                    }
                }
            } catch (t: Throwable) {
                _uiState.update {
                    it.copy(
                        error = t.message
                    )
                }
            }
        }
    }
}