package com.ghalistan.genshinimpactgachasimulation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghalistan.genshinimpactgachasimulation.models.BannerModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BannerViewModel : ViewModel() {

    val _banners = MutableLiveData<List<BannerModel>>()

    val banners: LiveData<List<BannerModel>>
        get() = _banners

    init {
        getBannerFromDB()
    }

    fun getBannerFromDB() {
        val database = Firebase.database.reference
        _banners.value = listOf()
        database.child("Banners").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val placeholder = mutableListOf<BannerModel>()
                for (banner in snapshot.children) {
                    placeholder.add(banner.getValue(BannerModel::class.java)!!)
                }
                _banners.value = placeholder
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("GIGS", "Error fetching DB: ", error.toException())
            }
        })
    }
}