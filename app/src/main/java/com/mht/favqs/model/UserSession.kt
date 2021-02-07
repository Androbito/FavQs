package com.mht.favqs.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserSession(

        @Json(name = "User-Token")
        val token: String,
        val login: String,
        val email: String,
        val error_code: Int?,
        val message: String?
)