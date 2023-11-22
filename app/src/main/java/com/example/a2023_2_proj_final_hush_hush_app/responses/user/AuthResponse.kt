package com.example.a2023_2_proj_final_hush_hush_app.responses.user
import com.example.a2023_2_proj_final_hush_hush_app.entities.TokenEntity
import com.example.a2023_2_proj_final_hush_hush_app.entities.UserEntity
import com.google.gson.annotations.SerializedName

class AuthResponse() : UserEntity() {
    // Login and Store responses

    @SerializedName("access_token")
    var token: TokenEntity = TokenEntity()
}
