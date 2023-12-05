package com.example.a2023_2_proj_final_hush_hush_app.responses.comment

import com.example.a2023_2_proj_final_hush_hush_app.entities.PaginationEntity
import com.google.gson.annotations.SerializedName

class IndexResponse : PaginationEntity() {
    @SerializedName("data")
    var data: Array<ShowResponse> = arrayOf<ShowResponse>()
}