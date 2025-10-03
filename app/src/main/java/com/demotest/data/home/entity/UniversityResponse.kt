    package com.demotest.data.home.entity

    import com.squareup.moshi.Json

    data class UniversityResponse(
        @Json(name = "web_pages") val webPages: List<String>,
        @Json(name = "name") val name: String,
        @Json(name = "state_province") val stateProvince: String?,
        @Json(name = "alpha_two_code") val alphaTwoCode: String,
        @Json(name = "domains") val domains: List<String>,
        @Json(name = "country") val country: String
    )

