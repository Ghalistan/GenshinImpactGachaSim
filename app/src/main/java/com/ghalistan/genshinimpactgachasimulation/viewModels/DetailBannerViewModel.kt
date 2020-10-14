package com.ghalistan.genshinimpactgachasimulation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ghalistan.genshinimpactgachasimulation.models.BannerModel
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.ghalistan.genshinimpactgachasimulation.repositories.FirebaseDB
import com.ghalistan.genshinimpactgachasimulation.utils.GachaLogic
import com.ghalistan.genshinimpactgachasimulation.utils.viewModelHelper

class DetailBannerViewModel(private val bannerName: String) : ViewModel() {
    private val firebaseRepo: FirebaseDB by lazy {
        FirebaseDB()
    }
    private val dataBanner: LiveData<BannerModel> = firebaseRepo.bannerData
    private val pullableData: LiveData<List<PullableModel>> = firebaseRepo.pullableData

    companion object {
        val FACTORY = viewModelHelper(::DetailBannerViewModel)
    }

    fun getBannerData(): LiveData<BannerModel> {
        firebaseRepo.getDetailBannerData(bannerName)
        return dataBanner
    }

    fun getPullables(): LiveData<List<PullableModel>> {
        firebaseRepo.getPullablesData(bannerName)
        return pullableData
    }

    fun shortItem(itemData: List<PullableModel>): List<PullableModel> {
        return GachaLogic().shortPullableItem(itemData)
    }
}