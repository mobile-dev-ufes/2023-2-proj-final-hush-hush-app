package com.example.a2023_2_proj_final_hush_hush_app.entities
import com.google.gson.annotations.SerializedName

class ShowResponse : UserEntity() {
    @SerializedName("posts_count")
    var postsCount: Int = 0

    @SerializedName("comments_count")
    var commentsCount: Int = 0
}
