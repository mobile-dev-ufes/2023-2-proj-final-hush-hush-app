package com.example.a2023_2_proj_final_hush_hush_app.clients

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val API_PORT = 8000
        private const val IP = "192.168.10.19"
        private const val BASE_URL = "http://${IP}:${API_PORT}/api/"

//        private const val BASE_URL = "http://192.168.0.101:8000/api/" // ip elaine
//        private const val BASE_URL = "http://192.168.10.19:${API_PORT}/api/" // ip filipe



        private fun getClientInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun <S> createService(className: Class<S>): S {
            return getClientInstance().create(className)
        }
    }
}