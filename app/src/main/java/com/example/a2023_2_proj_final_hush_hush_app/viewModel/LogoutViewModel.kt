package com.example.a2023_2_proj_final_hush_hush_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LogoutViewModel : ViewModel() {
    private var isLoading = MutableLiveData<Boolean>()

    fun isLoading(): LiveData<Boolean> {
        return this.isLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}