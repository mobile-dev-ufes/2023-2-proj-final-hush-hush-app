package com.example.a2023_2_proj_final_hush_hush_app.utils

class HttpCodes {
    companion object {
        val UNAUTHORIZED = 401
        fun isInfoResponseCode(code: Int): Boolean {
            return code in 100..199
        }

        fun isSuccessResponseCode(code: Int): Boolean {
            return code in 200..299
        }

        fun isRedirectResponseCode(code: Int): Boolean {
            return code in 300..399
        }

        fun isClientErrorResponseCode(code: Int): Boolean {
            return code in 400..499
        }

        fun isServerResponseCode(code: Int): Boolean {
            return code in 500..599
        }
    }
}