package com.example.a2023_2_proj_final_hush_hush_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    private var username = MutableLiveData<String>()
    private var password = MutableLiveData<String>()
    private var isLoading = MutableLiveData<Boolean>()

    fun username(): LiveData<String> {
        return this.username
    }

    fun password(): LiveData<String> {
        return this.password
    }

    fun isLoading(): LiveData<Boolean> {
        return this.isLoading
    }

    fun setUsername(username: String) {
        this.username.value = username
    }

    fun setPassword(password: String) {
        this.password.value = password
    }

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}