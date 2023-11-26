package com.example.a2023_2_proj_final_hush_hush_app.clients

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var INSTANCE: Retrofit
        private const val API_PORT = 8000
//        private const val IP = "192.168.10.19"
//        private const val BASE_URL = "http://10.0.2.2:${API_PORT}/api/"

        //TAVA ASSIM
        //private const val BASE_URL = "http://10.0.2.2:8000/api/"


//        private const val BASE_URL = "http://localhost:8000/api/"
//        private const val BASE_URL = "http://${IP}:${API_PORT}/api/"
//        private const val BASE_URL = "http://559d-177-154-164-73.ngrok-free.app/api/"
        //MUDEI PARA
        private const val BASE_URL = "http://192.168.0.101/api/"


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