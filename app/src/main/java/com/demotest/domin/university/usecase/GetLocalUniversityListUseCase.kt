package com.demotest.domin.university.usecase

import com.demotest.domin.university.model.UniversityItem
import com.demotest.domin.university.repository.UniversityRepository

class GetLocalUniversityListUseCase(private val universityRepository: UniversityRepository) {
    suspend fun getUniversities(): List<UniversityItem> {
        return universityRepository.getLocalUniversities()
    }
}