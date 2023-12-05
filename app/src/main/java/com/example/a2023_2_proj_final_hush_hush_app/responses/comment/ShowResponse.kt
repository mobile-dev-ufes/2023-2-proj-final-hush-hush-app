package com.example.a2023_2_proj_final_hush_hush_app.responses.comment

import com.example.a2023_2_proj_final_hush_hush_app.entities.CommentEntity
import com.example.a2023_2_proj_final_hush_hush_app.entities.UserEntity
import com.google.gson.annotations.SerializedName

class ShowResponse : CommentEntity() {
    @SerializedName("user_evaluation")
    var userEvaluation: String = ""

    @SerializedName("likes_count")
    var likesCount: Int = 0

    @SerializedName("dislikes_count")
    var dislikesCount: Int = 0

    @SerializedName("user")
    var user: UserEntity = UserEntity()
}