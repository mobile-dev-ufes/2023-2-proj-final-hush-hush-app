package com.example.a2023_2_proj_final_hush_hush_app.entities
import com.google.gson.annotations.SerializedName

open class PaginationEntity {
    @SerializedName("current_page")
    var currentPage: Int = 0;

    @SerializedName("first_page_url")
    var firstPageUrl: String = "";

    @SerializedName("from")
    var from: Int = 0;

    @SerializedName("last_page")
    var lastPage: String = "";

    @SerializedName("last_page_url")
    var lastPageUrl: String = "";

    @SerializedName("next_page_url")
    var nextPageUrl: String = "";

    @SerializedName("path")
    var path: String = "";

    @SerializedName("per_page")
    var perPage: Int = 0;

    @SerializedName("prev_page_url")
    var prevPageUrl: String = "";

    @SerializedName("to")
    var to: Int = 0;

    @SerializedName("total")
    var total: Int = 0;
}
