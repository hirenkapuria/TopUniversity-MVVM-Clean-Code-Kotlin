package com.demotest.domin.university.usecase

import com.demotest.domin.university.repository.UniversityRepository

class GetUniversityListUseCase(private val universityRepository: UniversityRepository) {
    suspend fun getUniversities() {
        return universityRepository.fetchAndSaveUniversities()
    }
}