package com.demotest.domin.university.repository

import androidx.paging.PagingData
import com.demotest.data.home.entity.UniversityEntity
import com.demotest.domin.university.model.UniversityItem
import kotlinx.coroutines.flow.Flow

interface UniversityRepository {
    suspend fun fetchAndSaveUniversities()
    suspend fun getLocalUniversities(): List<UniversityItem>
    fun getPagedUniversities(): Flow<PagingData<UniversityEntity>>
}