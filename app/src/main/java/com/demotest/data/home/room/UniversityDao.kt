package com.demotest.data.home.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demotest.data.home.entity.UniversityEntity

@Dao
interface UniversityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<UniversityEntity>)

    @Query("SELECT * FROM universities ORDER BY name ASC")
    suspend fun getAllUniversities(): MutableList<UniversityEntity>

    @Query("SELECT * FROM universities ORDER BY name ASC")
    fun getPagingUniversities(): PagingSource<Int, UniversityEntity>

    /*@Query("DELETE FROM universities")
    suspend fun clearAll()*/
}