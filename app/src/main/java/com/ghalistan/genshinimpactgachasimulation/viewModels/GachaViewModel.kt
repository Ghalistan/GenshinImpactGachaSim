package com.ghalistan.genshinimpactgachasimulation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.ghalistan.genshinimpactgachasimulation.utils.GachaLogic
import com.ghalistan.genshinimpactgachasimulation.utils.viewModelHelper

class GachaViewModel(private val pullData: List<PullableModel>) : ViewModel() {
    private lateinit var itemPullable: List<ItemModel>

    private var _pullResult = MutableLiveData<List<ItemModel>>()
    val pullResult: LiveData<List<ItemModel>>
        get() = _pullResult

    init {
        generateItemOnlyList()
    }

    companion object {
        val FACTORY = viewModelHelper(::GachaViewModel)
    }

    fun doGachaProcess(onePull: Boolean) {
        if (onePull) {
            _pullResult.value = GachaLogic().doGacha(itemPullable, 1)
        } else {
            _pullResult.value = GachaLogic().doGacha(itemPullable, 10)
        }
    }

    fun generateItemOnlyList() {
        val placeholder = mutableListOf<ItemModel>()
        for (data in pullData) {
            placeholder.add(data.pullableObject)
        }
        itemPullable = placeholder
    }
}