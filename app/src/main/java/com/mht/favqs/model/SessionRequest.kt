package com.mht.favqs.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionRequest(
        val user: User
)

@JsonClass(generateAdapter = true)
data class User(
        val login: String,
        val password: String
)