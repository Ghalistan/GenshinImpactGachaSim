package com.ghalistan.genshinimpactgachasimulation.models

data class BannerModel(
    val name: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val bannerPict: String = "",
    val pullables: List<PullableModel> = listOf()
)