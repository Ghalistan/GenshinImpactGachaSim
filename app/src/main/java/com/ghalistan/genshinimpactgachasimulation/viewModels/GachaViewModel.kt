package com.ghalistan.genshinimpactgachasimulation.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ghalistan.genshinimpactgachasimulation.database.GachaDatabase
import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.ghalistan.genshinimpactgachasimulation.repositories.RoomRepo
import com.ghalistan.genshinimpactgachasimulation.utils.GachaLogic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GachaViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var itemPullable: List<ItemModel>
    private val roomRepo: RoomRepo

    private var _pullResult = MutableLiveData<List<ItemModel>>()
    val pullResult: LiveData<List<ItemModel>>
        get() = _pullResult

    init {
        val database = GachaDatabase.getDatabase(application, viewModelScope)
        val userDao = database.userDao()
        val itemDao = database.itemDao()
        roomRepo = RoomRepo(userDao, itemDao)
    }

    fun doGachaProcess(onePull: Boolean) {
        val gachaMachine = GachaLogic()
        var gachaResult: List<ItemModel>

        if (onePull) {
            gachaResult = gachaMachine.doGacha(itemPullable, 1)
            _pullResult.value = gachaResult
        } else {
            gachaResult = gachaMachine.doGacha(itemPullable, 10)
            _pullResult.value = gachaResult
        }

        viewModelScope.launch(Dispatchers.IO) {
            roomRepo.insertItems(gachaResult)
        }
    }

    fun generateItemOnlyList(pullData: List<PullableModel>) {
        val placeholder = mutableListOf<ItemModel>()

        for (data in pullData) {
            placeholder.add(data.pullableObject)
        }

        itemPullable = placeholder
    }
}