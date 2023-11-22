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
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @POST("post")
    fun store(@Body data: StoreUpdateBody): Call<StoreUpdatePatchResponse>

    @PUT("post/{postId}")
    fun update(@Body data: StoreUpdateBody): Call<StoreUpdatePatchResponse>

    @PATCH("post-is-active")
    fun patch(@Body data: PatchBody): Call<StoreUpdatePatchResponse>

    @GET("posts")
    fun index(
        @Query("title") title: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null
    ): Call<IndexResponse>

    @GET("user/posts")
    fun indexByLoggedUser(
        @Query("title") title: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null
    ): Call<IndexResponse>

    @GET("user/{userId}/posts")
    fun indexByUserId(
        @Path("userId") userId: Int,
        @Query("title") title: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
        @Query("start_date") startDate: String? = null,
        @Query("end_date") endDate: String? = null
    ): Call<IndexResponse>

    @GET("post/{postId}")
    fun show(@Path("postId") postId: Int): Call<ShowResponse>

    @DELETE("post/{postId}")
    fun delete(@Path("postId") postId: Int): Call<Void>
}