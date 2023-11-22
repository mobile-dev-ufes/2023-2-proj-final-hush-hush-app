package com.example.a2023_2_proj_final_hush_hush_app.bodies.post
import com.google.gson.annotations.SerializedName

class StoreUpdateBody {
    @SerializedName("title")
    var title: String = ""

    @SerializedName("content")
    var content: String = ""
}