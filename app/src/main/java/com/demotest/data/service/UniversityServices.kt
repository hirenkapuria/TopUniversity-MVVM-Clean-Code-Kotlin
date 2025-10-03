package com.demotest.data.service

import com.demotest.data.home.entity.UniversityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityServices {

    @GET("search")
    suspend fun getUniversities(
        @Query("country") country: String
    ): MutableList<UniversityResponse>
}