package com.mht.favqs.model


data class SessionRequest(
        val user: User
)

data class User(
        val login: String,
        val password: String
)