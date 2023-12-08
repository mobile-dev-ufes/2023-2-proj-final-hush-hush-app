package com.example.a2023_2_proj_final_hush_hush_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CardProfileViewModel : ViewModel() {
    private var username = MutableLiveData<String>()
    private var numHushHush = MutableLiveData<String>()
    private var memberSince = MutableLiveData<String>()

    fun setUsername(username: String) {
        this.username.value = username
    }

    fun setNumHushHush(numHushHush : Int){
        this.numHushHush.value = numHushHush.toString()
    }

    fun setMemberSince(memberSince : String){
        this.memberSince.value = memberSince
    }


    fun username(): LiveData<String> {
        return this.username
    }

    fun numHushHush(): LiveData<String> {
        return this.numHushHush
    }

    fun memberSince(): LiveData<String> {
        return this.memberSince
    }
}