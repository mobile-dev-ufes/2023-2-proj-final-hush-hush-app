package com.example.a2023_2_proj_final_hush_hush_app.services

import com.example.a2023_2_proj_final_hush_hush_app.responses.dashboards.DashboardsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface DashboardsService {
    @Headers("Accept: application/json")
    @GET("/dashboards")
    fun dashboards(
        @Header("Authorization") token: String,
        @Header("Accept-Language") language: String,
    ): Call<DashboardsResponse>
}
