package com.example.a2023_2_proj_final_hush_hush_app.services

import retrofit2.Call
import retrofit2.http.POST

interface EvaluationService {
    @POST("comment/{commentId}/like")
    fun like(): Call<Void>

    @POST("comment/{commentId}/dislike")
    fun dislike(): Call<Void>

    @POST("comment/{commentId}/None")
    fun none(): Call<Void>
}