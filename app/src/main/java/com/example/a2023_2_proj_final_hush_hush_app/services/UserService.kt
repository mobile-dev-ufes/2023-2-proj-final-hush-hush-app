package com.example.a2023_2_proj_final_hush_hush_app.services
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.LoginBody
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.StoreBody
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.UpdateBody
import com.example.a2023_2_proj_final_hush_hush_app.bodies.user.UpdatePasswordBody
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.StoreLoginResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.IndexResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.ShowResponse
import com.example.a2023_2_proj_final_hush_hush_app.responses.user.UpdateResponse
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

interface UserService {
    @Headers("Accept: application/json")
    @POST("login-user")
    fun login(@Body credentials: LoginBody): Call<StoreLoginResponse>

    @Headers("Accept: application/json")
    @POST("user")
    fun store(@Body data: StoreBody): Call<StoreLoginResponse>

    @Headers("Accept: application/json")
    @PUT("user")
    fun update(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Body data: UpdateBody
    ): Call<UpdateResponse>

    @Headers("Accept: application/json")
    @POST("user-profile-picture")
    fun updateProfilePicture(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Body data: UpdatePasswordBody
    ): Call<UpdateResponse>

    @Headers("Accept: application/json")
    @PATCH("user-password")
    fun updatePassword(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Body data: UpdatePasswordBody
    ): Call<UpdateResponse>

    @Headers("Accept: application/json")
    @POST("logout-user")
    fun logout(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Call<Void>

    @Headers("Accept: application/json")
    @GET("users")
    fun index(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Query("name") name: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null
    ): Call<IndexResponse>

    @Headers("Accept: application/json")
    @GET("user/{userId}")
    fun show(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Path("userId") userId: Int
    ): Call<ShowResponse>

    @Headers("Accept: application/json")
    @GET("user")
    fun showByLoggedUser(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Call<ShowResponse>

    @Headers("Accept: application/json")
    @DELETE("user/{userId}")
    fun delete(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
        @Path("userId") userId: Int
    ): Call<Void>
}
