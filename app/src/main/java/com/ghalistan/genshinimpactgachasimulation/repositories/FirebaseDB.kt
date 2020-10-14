package com.ghalistan.genshinimpactgachasimulation.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ghalistan.genshinimpactgachasimulation.models.BannerModel
import com.ghalistan.genshinimpactgachasimulation.models.PullableModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDB {
    private val database: DatabaseReference = Firebase.database.reference

    private var _bannerData = MutableLiveData<BannerModel>()
    val bannerData: LiveData<BannerModel>
        get() = _bannerData

    private var _pullableData = MutableLiveData<List<PullableModel>>()
    val pullableData: LiveData<List<PullableModel>>
        get() = _pullableData

    fun getDetailBannerData(bannerName: String) {
        database.child("Banners").child(bannerName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    _bannerData.value = snapshot.getValue(BannerModel::class.java)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("GIGS", "Error fetching detail banner: ", error.toException())
                }

            })
    }

    fun getPullablesData(bannerName: String) {
        database.child("Pullables").child(bannerName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val placeholder = mutableListOf<PullableModel>()
                    for (pullable in snapshot.children) {
                        placeholder.add(pullable.getValue(PullableModel::class.java)!!)
                    }
                    _pullableData.value = placeholder
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("GIGS", "Error fetching pullable data: ", error.toException())
                }
            })
    }
}