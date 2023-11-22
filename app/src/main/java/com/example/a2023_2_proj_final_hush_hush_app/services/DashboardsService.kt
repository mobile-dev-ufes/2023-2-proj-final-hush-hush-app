package com.example.a2023_2_proj_final_hush_hush_app.services

import com.example.a2023_2_proj_final_hush_hush_app.responses.dashboards.DashboardsResponse
import retrofit2.Call
import retrofit2.http.GET

interface DashboardsService {
    @GET("/dashboards")
    fun dashboards(): Call<DashboardsResponse>
}