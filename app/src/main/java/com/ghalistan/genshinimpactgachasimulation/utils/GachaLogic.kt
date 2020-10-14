package com.ghalistan.genshinimpactgachasimulation.utils

import android.util.Log
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

        return data.sortedWith(compareBy<PullableModel> { it.featuredItem }.reversed().thenBy { it.objectType }.thenBy { it.pullableObject.name })
    }

    fun doGacha(pullData: List<ItemModel>, gachaPullAmount: Int): List<ItemModel> {
        val placeholder = mutableListOf<ItemModel>()

        repeat(gachaPullAmount) { index ->
            val rand = Random.nextInt(0, 1000)
            if (rand >= 994) {
                if (Random.nextBoolean()) {
                    placeholder.add(pullData[0])
                } else {
                    placeholder.add(pullData[Random.nextInt(1,5)])
                }
            }
            else if (rand >= 943) {
                placeholder.add(pullData[Random.nextInt(6, 35)])
            } else {
                placeholder.add(pullData[Random.nextInt(36, 48)])
            }
        }

        return placeholder
    }
}