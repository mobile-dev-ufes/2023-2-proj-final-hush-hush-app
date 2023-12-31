package com.example.a2023_2_proj_final_hush_hush_app.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse

class ShowHushHushViewModel : ViewModel() {
    private var hushHush = MutableLiveData<ShowResponse>()
    private var commentContent = MutableLiveData<String>()
    private var isLoading = MutableLiveData<Boolean>()
    private var clearCommentContent = MutableLiveData(true)

    fun hushHush(): LiveData<ShowResponse> {
        return this.hushHush
    }

    fun commentContent(): LiveData<String>{
        return this.commentContent
    }

    fun isLoading():  LiveData<Boolean> {
        return this.isLoading
    }

    fun clearCommentContent():  LiveData<Boolean> {
        return this.clearCommentContent
    }

    fun setHushHush(hushHush: ShowResponse) {
        this.hushHush.value = hushHush
    }

    fun setCommentContent(commentContent: String){
        this.commentContent.value = commentContent
    }

    fun setIsLoading(isLoading: Boolean){
        this.isLoading.value = isLoading
    }

    fun executeClearCommentContent() {
        this.clearCommentContent.value = !this.clearCommentContent.value!!
    }
}