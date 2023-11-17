package com.example.a2023_2_proj_final_hush_hush_app.entities
import com.google.gson.annotations.SerializedName
class LoginResponse() : UserEntity() {
    @SerializedName("access_token")
    var token: TokenEntity = TokenEntity()
}
