package com.ghalistan.genshinimpactgachasimulation.utils

import com.ghalistan.genshinimpactgachasimulation.models.ItemModel
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import kotlin.random.Random

class GachaLogic {
    fun getSpecificRarity(bannerPullable: List<PullableModel>, rarity: Int): List<PullableModel> {
        val data = mutableListOf<PullableModel>()

        for (pullable in bannerPullable) {
            if (pullable.pullableObject.rarity == rarity) {
                data.add(pullable)
            }
        }

        data.sortWith(compareBy<PullableModel> { it.objectType }.thenBy { it.pullableObject.name })

        return data.sortedByDescending { it.featuredItem }
    }

    fun doGacha(pullData: List<ItemModel>, gachaPullAmount: Int): List<ItemModel> {

        val splitedData = splitItemRarity(pullData)
        val tempResultItem = mutableListOf<ItemModel>()

        repeat(gachaPullAmount) {
            when (Random.nextInt(0, 1000)) {
                in 994..1000 -> {
                    val fiveStar = splitedData.getValue(5)
                    val bannerChar = Random.nextBoolean()

                    if (bannerChar) tempResultItem.add(fiveStar[0])
                    else tempResultItem.add(doRandom(fiveStar, 1))
                }
                in 943..995 -> {
                    val fourStar = splitedData.getValue(4)
                    tempResultItem.add(doRandom(fourStar))
                }
                else -> {
                    val threeStar = splitedData.getValue(3)
                    tempResultItem.add(doRandom(threeStar))
                }
            }
        }

        return tempResultItem
    }

    private fun splitItemRarity(pullData: List<ItemModel>): Map<Int, List<ItemModel>> {
        val tempItem = mutableMapOf<Int, List<ItemModel>>()
        val tempFiveStar = mutableListOf<ItemModel>()
        val tempFourStar = mutableListOf<ItemModel>()
        val tempThreeStar = mutableListOf<ItemModel>()

        for (data in pullData) {
            when (data.rarity) {
                5 -> tempFiveStar.add(data)
                4 -> tempFourStar.add(data)
                3 -> tempThreeStar.add(data)
            }
        }

        tempItem[5] = tempFiveStar
        tempItem[4] = tempFourStar
        tempItem[3] = tempThreeStar

        return tempItem
    }

    private fun doRandom(listData: List<ItemModel>, from: Int = 0): ItemModel {
        return listData[Random.nextInt(from, listData.size - 1)]
    }
}