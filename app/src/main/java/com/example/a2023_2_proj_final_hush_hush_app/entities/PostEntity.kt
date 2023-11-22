package com.example.a2023_2_proj_final_hush_hush_app.entities

import com.google.gson.annotations.SerializedName
open class PostEntity {
    @SerializedName("title")
    var title: String = ""

    @SerializedName("content")
    var content: String = ""

    @SerializedName("is_active")
    var isActive: Boolean = true

    @SerializedName("user_id")
    var userId: Int = 0

    @SerializedName("created_at")
    var createdAt: String = ""

    @SerializedName("updated_at")
    var updatedAt: String = ""

    @SerializedName("id")
    var id: Int = 0
}