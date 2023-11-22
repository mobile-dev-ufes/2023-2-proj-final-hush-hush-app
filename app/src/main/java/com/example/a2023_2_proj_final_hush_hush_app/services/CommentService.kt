package com.example.a2023_2_proj_final_hush_hush_app.services

import com.example.a2023_2_proj_final_hush_hush_app.bodies.comment.StoreUpdateBody
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.comment.StoreUpdateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {
    @POST("post/{postId}/comment")
    fun store(
        @Path("postId") postId: Int,
        @Body data: StoreUpdateBody
    ): Call<StoreUpdateResponse>

    @PUT("comment/{commentId}")
    fun update(
        @Path("commentId") commentId: Int,
        @Body data: StoreUpdateBody
    ): Call<StoreUpdateResponse>

    @GET("/post/{postId}/comments")
    fun index(
        @Path("postId") postId: Int,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null,
        @Query("order_by") orderBy: String? = null,
    ): Call<IndexResponse>

    @DELETE("/comment/{commentId}")
    fun delete(@Path("commentId") commentId: Int): Call<Void>
}
