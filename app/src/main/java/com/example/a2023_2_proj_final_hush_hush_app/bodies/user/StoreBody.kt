package com.example.a2023_2_proj_final_hush_hush_app.bodies.user
import com.google.gson.annotations.SerializedName

class StoreBody {
    @SerializedName("username")
    var username: String = ""

    @SerializedName("password")
    var password: String = ""

    @SerializedName("repeat_password")
    var repeatPassword: String = ""
}