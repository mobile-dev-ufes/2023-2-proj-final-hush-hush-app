package com.example.a2023_2_proj_final_hush_hush_app.bodies.user
import com.google.gson.annotations.SerializedName
class UpdatePasswordBody {
    @SerializedName("current_password")
    var currentPassword: String = ""

    @SerializedName("new_password")
    var newPassword: String = ""

    @SerializedName("repeat_new_password")
    var repeatNewPassword: String = ""
}