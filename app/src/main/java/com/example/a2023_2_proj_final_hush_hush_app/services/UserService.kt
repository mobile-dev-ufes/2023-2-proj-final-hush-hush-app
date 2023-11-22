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
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @POST("login-user")
    fun login(@Body credentials: LoginBody): Call<StoreLoginResponse>

    @POST("user")
    fun store(@Body data: StoreBody): Call<StoreLoginResponse>

    @PUT("user")
    fun update(@Body data: UpdateBody): Call<UpdateResponse>

    @POST("user-profile-picture")
    fun updateProfilePicture(@Body data: UpdatePasswordBody): Call<UpdateResponse>

    @PATCH("user-password")
    fun updatePassword(@Body data: UpdatePasswordBody): Call<UpdateResponse>

    @POST("logout-user")
    fun logout(): Call<Void>

    @GET("users")
    fun index(
        @Query("name") name: String? = null,
        @Query("count") count: Int? = null,
        @Query("page") page: Int? = null
    ): Call<IndexResponse>

    @GET("user/{userId}")
    fun show(@Path("userId") userId: Int): Call<ShowResponse>

    @GET("user")
    fun showByLoggedUser(): Call<ShowResponse>

    @DELETE("user/{userId}")
    fun delete(@Path("userId") userId: Int): Call<Void>
}
