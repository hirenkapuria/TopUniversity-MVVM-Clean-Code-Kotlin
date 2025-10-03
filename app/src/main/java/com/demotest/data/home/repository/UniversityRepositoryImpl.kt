package com.demotest.data.home.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demotest.data.home.entity.UniversityEntity
import com.demotest.data.home.room.UniversityDao
import com.demotest.data.service.UniversityServices
import com.demotest.domin.university.model.UniversityItem
import com.demotest.domin.university.repository.UniversityRepository
import com.demotest.extenstion.toUniversityEntity
import com.demotest.extenstion.toUniversityItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UniversityRepositoryImpl(
    private val dao: UniversityDao,
    private var universityServices: UniversityServices
): UniversityRepository {

    override suspend fun fetchAndSaveUniversities() {
        return withContext(Dispatchers.IO) {
            val response = universityServices.getUniversities("Netherlands").sortedBy { it.name }
            val entity = response.map { it.toUniversityEntity() }
            dao.insertAll(entity)
        }
    }

    override suspend fun getLocalUniversities(): List<UniversityItem> {
        val entity = dao.getAllUniversities()
        return entity.map { it.toUniversityItem() }
    }

    override fun getPagedUniversities(): Flow<PagingData<UniversityEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { dao.getPagingUniversities() }
        ).flow
    }
}