package com.example.a2023_2_proj_final_hush_hush_app.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences(context: Context) {
    private val preferencesKey: String = "secret"
    private val tokenKey = "token"
    private val usernameKey = "username"
    private val profilePictureKey = "profile_picture"

    private val sp: SharedPreferences = context.getSharedPreferences(this.preferencesKey, Context.MODE_PRIVATE)

    fun setToken(token: String) {
        sp.edit().putString(this.tokenKey, token).apply()
    }

    fun getToken() {
        sp.getString(this.tokenKey, "") ?: ""
    }

    fun setUsername(username: String) {
        sp.edit().putString(this.usernameKey, username).apply()
    }

    fun getUsername() {
        sp.getString(this.usernameKey, "") ?: ""
    }

    fun setProfilePicture(profilePicture: String?) {
        sp.edit().putString(this.profilePictureKey, profilePicture?:"").apply()
    }

    fun getProfilePicture() {
        sp.getString(this.profilePictureKey, "") ?: ""
    }
}