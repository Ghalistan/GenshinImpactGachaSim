package com.ghalistan.genshinimpactgachasimulation.daos

import androidx.room.Query
import com.ghalistan.genshinimpactgachasimulation.models.BannerModel

interface BannerDao {
    @Query("SELECT * from banner ORDER BY name ASC")
    fun getAllBanner(): List<BannerModel>
}