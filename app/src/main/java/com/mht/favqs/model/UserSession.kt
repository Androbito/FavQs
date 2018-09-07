package com.mht.favqs.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class UserSession(

        @SerializedName("User-Token")
        @Expose
        val token: String,
        val login: String,
        val email: String,
        val error_code: Int,
        val message: String
)