package com.example.a2023_2_proj_final_hush_hush_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    private var search = MutableLiveData<String>()
    private var isLoading = MutableLiveData<Boolean>()

    fun search(): LiveData<String> {
        return this.search
    }

    fun isLoading(): LiveData<Boolean> {
        return this.isLoading
    }

    fun setSearch(search: String) {
        this.search.value = search
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}