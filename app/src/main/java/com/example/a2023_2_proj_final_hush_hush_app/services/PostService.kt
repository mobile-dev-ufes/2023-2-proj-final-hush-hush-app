package com.example.a2023_2_proj_final_hush_hush_app.services

import com.example.a2023_2_proj_final_hush_hush_app.bodies.post.PatchBody
import com.example.a2023_2_proj_final_hush_hush_app.bodies.post.StoreUpdateBody
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.post.StoreUpdatePatchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @Headers("Accept: application/json")
    @POST("post")
    fun store(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Body data: StoreUpdateBody
    ): Call<StoreUpdatePatchResponse>

    @Headers("Accept: application/json")
    @PUT("post/{postId}")
    fun update(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Body data: StoreUpdateBody
    ): Call<StoreUpdatePatchResponse>

    @Headers("Accept: application/json")
    @PATCH("post-is-active")
    fun patch(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Body data: PatchBody
    ): Call<StoreUpdatePatchResponse>

    @Headers("Accept: application/json")
    @GET("posts")
    fun index(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Query("title") title: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null
    ): Call<IndexResponse>

    @Headers("Accept: application/json")
    @GET("user/posts")
    fun indexByLoggedUser(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Query("title") title: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null
    ): Call<IndexResponse>

    @Headers("Accept: application/json")
    @GET("user/{userId}/posts")
    fun indexByUserId(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Path("userId") userId: Int,
        @Query("title") title: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null
    ): Call<IndexResponse>

    @Headers("Accept: application/json")
    @GET("post/{postId}")
    fun show(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Path("postId") postId: Int
    ): Call<ShowResponse>

    @Headers("Accept: application/json")
    @DELETE("post/{postId}")
    fun delete(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Path("postId") postId: Int
    ): Call<Void>
}
