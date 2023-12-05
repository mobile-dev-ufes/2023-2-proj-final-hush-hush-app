package com.example.a2023_2_proj_final_hush_hush_app.entities
import com.google.gson.annotations.SerializedName

open class UserEntity {
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("username")
    var username: String = ""

    @SerializedName("created_at")
    var createdAt: String = ""

    @SerializedName("updated_at")
    var updatedAt: String = ""

    @SerializedName("profile_picture")
    var profilePicture: String? = ""
}