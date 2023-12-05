package com.example.a2023_2_proj_final_hush_hush_app.services

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface EvaluationService {
    @Headers("Accept: application/json")
    @POST("comment/{commentId}/like")
    fun like(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Call<Void>

    @Headers("Accept: application/json")
    @POST("comment/{commentId}/dislike")
    fun dislike(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Call<Void>

    @Headers("Accept: application/json")
    @POST("comment/{commentId}/None")
    fun none(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Call<Void>
}
