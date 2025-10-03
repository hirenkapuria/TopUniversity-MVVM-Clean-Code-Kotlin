package com.demotest.extenstion

import com.demotest.data.home.entity.UniversityEntity
import com.demotest.data.home.entity.UniversityResponse
import com.demotest.domin.university.model.UniversityItem

fun UniversityResponse.toUniversityEntity(): UniversityEntity {
    return UniversityEntity(
        name = this.name,
        country = this.country,
        alphaTwoCode = this.alphaTwoCode,
        domain = this.domains.firstOrNull() ?: "",
        webPage = this.webPages.firstOrNull() ?: ""
    )
}

fun UniversityEntity.toUniversityItem(): UniversityItem {
    return UniversityItem(
        webPage = this.webPage,
        name = this.name,
        alphaTwoCode = this.alphaTwoCode,
        domain= this.domain,
        country = this.country
    )
}