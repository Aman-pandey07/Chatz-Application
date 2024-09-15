package com.aman.chatz.feature.home

import androidx.lifecycle.ViewModel
import com.aman.chatz.model.Channel
import com.google.firebase.Firebase
import com.google.firebase.database.database
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel : ViewModel(){
    private val firebaseDatabase = Firebase.database
    init {
        getChannels()
    }

    private fun getChannels(){
        firebaseDatabase.getReference("channel").get().addOnSuccessListener {
            val list = mutableListOf<Channel>()
            it.children.forEach{data ->
                val channel = Channel(data.key!!,data.value.toString())
                list.add(channel)
            }
        }
    }
}