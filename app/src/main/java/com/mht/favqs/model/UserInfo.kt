package com.mht.favqs.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInfo(
        val login: String,
        @Json(name = "pic_url") val picUrl: String,
        @Json(name = "public_favorites_count") val publicFavoritesCount: Int,
        val following: Int,
        val followers: Int,
        val pro: Boolean,
        @Json(name = "account_details") val accountDetails: AccountDetails
)
@JsonClass(generateAdapter = true)
data class AccountDetails(
        val email: String,
        @Json(name = "private_favorites_count") val privateFavoritesCount: Int
)