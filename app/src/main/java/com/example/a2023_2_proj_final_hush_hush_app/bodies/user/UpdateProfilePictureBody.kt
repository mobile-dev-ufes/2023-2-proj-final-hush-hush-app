package com.example.a2023_2_proj_final_hush_hush_app.bodies.user
import com.google.gson.annotations.SerializedName

class UpdateProfilePictureBody {
    @SerializedName("profile_picture")
    var profilePicture: String = ""
}