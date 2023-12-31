package com.example.a2023_2_proj_final_hush_hush_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateHushHushViewModel : ViewModel() {
    private var title = MutableLiveData<String>()       //title_hush_hush
    private var content = MutableLiveData<String>()     //body_hush_hush
    private var isLoading = MutableLiveData<Boolean>()
    private var clearContent = MutableLiveData(true)

    fun title(): LiveData<String> {
        return this.title
    }

    fun content(): LiveData<String> {
        return this.content
    }

    fun isLoading(): LiveData<Boolean> {
        return this.isLoading
    }

    fun clearContent():  LiveData<Boolean> {
        return this.clearContent
    }

    fun setTitle(title: String) {
        this.title.value = title
    }

    fun setContent(content: String){
        this.content.value = content
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    fun executeClearContent() {
        this.clearContent.value = !this.clearContent.value!!
    }
}