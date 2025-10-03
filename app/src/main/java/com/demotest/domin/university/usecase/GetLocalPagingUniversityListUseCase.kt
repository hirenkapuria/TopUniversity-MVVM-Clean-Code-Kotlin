package com.demotest.domin.university.usecase

import androidx.paging.PagingData
import com.demotest.data.home.entity.UniversityEntity
import com.demotest.domin.university.repository.UniversityRepository
import kotlinx.coroutines.flow.Flow

class GetLocalPagingUniversityListUseCase(private val universityRepository: UniversityRepository) {
    fun getUniversities(): Flow<PagingData<UniversityEntity>> {
        return universityRepository.getPagedUniversities()
    }
}