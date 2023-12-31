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

    fun getToken() : String{
        return sp.getString(this.tokenKey, "") ?: ""
    }

    fun setUsername(username: String) {
        sp.edit().putString(this.usernameKey, username).apply()
    }

    fun getUsername() : String {
        return sp.getString(this.usernameKey, "") ?: ""
    }

    fun setProfilePicture(profilePicture: String?) {
        sp.edit().putString(this.profilePictureKey, profilePicture?:"").apply()
    }

    fun getProfilePicture() : String {
        return sp.getString(this.profilePictureKey, "") ?: ""
    }

    fun clearSharedPreferences() {
        this.setToken("")
        this.setUsername("")
        this.setProfilePicture(null)
    }

    fun tokenIsFilled(): Boolean {
        return this.getToken() != ""
    }
}