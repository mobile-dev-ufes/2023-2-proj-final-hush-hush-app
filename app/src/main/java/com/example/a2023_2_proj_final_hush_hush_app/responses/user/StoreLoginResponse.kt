package com.example.a2023_2_proj_final_hush_hush_app.responses.user
import com.example.a2023_2_proj_final_hush_hush_app.entities.TokenEntity
import com.example.a2023_2_proj_final_hush_hush_app.entities.UserEntity
import com.google.gson.annotations.SerializedName

class StoreLoginResponse() : UserEntity() {
    // Login and Store responses

    @SerializedName("token")
    var token: TokenEntity = TokenEntity()
}
