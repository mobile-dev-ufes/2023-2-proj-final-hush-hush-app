package com.example.a2023_2_proj_final_hush_hush_app.entities;
import com.google.gson.annotations.SerializedName

public class TokenEntity {
    @SerializedName("access_token")
    var accessToken: String = ""

    @SerializedName("token_type")
    var tokenType: String = ""
}
