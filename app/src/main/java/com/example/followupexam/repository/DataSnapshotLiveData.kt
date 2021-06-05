package com.example.followupexam.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

/**
 * Yis class responsible in retrieve the data from firebase real time database
 * within any reference and return them as live data by inherit from [LiveData]
 * and implement [ValueEventListener]
 * @constructor : take the reference to fetch
 */
class DataSnapshotLiveData(private val reference: DatabaseReference):LiveData<DataSnapshot?>(),ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        value = snapshot
    }

    override fun onCancelled(error: DatabaseError) {
        Log.e("DataSnapshotLiveData","error data retrieving")
    }

    override fun onActive() {
        reference.addValueEventListener(this)
    }

    override fun onInactive() {
        reference.removeEventListener(this)
    }
}