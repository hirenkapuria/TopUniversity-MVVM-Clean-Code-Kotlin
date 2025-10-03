package com.demotest.data.home.mapper

import com.demotest.data.home.entity.UniversityResponse
import com.demotest.domin.university.model.UniversityItem
import kotlin.collections.List

class UniversityMapper {
    fun map(response: List<UniversityResponse>): MutableList<UniversityItem> {
        return response.map {
            UniversityItem(
                webPage = it.webPages[0],
                name = it.name,
                alphaTwoCode = it.alphaTwoCode,
                domain = it.domains[0],
                country = it.country
            )
        }.toMutableList()
    }
}