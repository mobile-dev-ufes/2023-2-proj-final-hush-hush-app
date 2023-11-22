package com.example.a2023_2_proj_final_hush_hush_app.responses.post

import com.example.a2023_2_proj_final_hush_hush_app.entities.PostEntity
import com.example.a2023_2_proj_final_hush_hush_app.entities.UserEntity
import com.google.gson.annotations.SerializedName
class ShowResponse : PostEntity() {
    @SerializedName("comments_count")
    var commentsCount: Int = 0

    @SerializedName("user")
    var user: UserEntity = UserEntity()
}