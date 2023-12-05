package com.example.a2023_2_proj_final_hush_hush_app.responses.post

import com.example.a2023_2_proj_final_hush_hush_app.entities.PaginationEntity

import com.google.gson.annotations.SerializedName

class IndexResponse : PaginationEntity() {
    // Index, Index By Logged User and Index By User Id

    @SerializedName("data")
    var data: Array<ShowResponse> = arrayOf<ShowResponse>()
}
