package com.mht.favqs.model
import com.google.gson.annotations.SerializedName



data class UserInfo(
    @SerializedName("login") val login: String,
    @SerializedName("pic_url") val picUrl: String,
    @SerializedName("public_favorites_count") val publicFavoritesCount: Int,
    @SerializedName("following") val following: Int,
    @SerializedName("followers") val followers: Int,
    @SerializedName("pro") val pro: Boolean,
    @SerializedName("account_details") val accountDetails: AccountDetails
)

data class AccountDetails(
    @SerializedName("email") val email: String,
    @SerializedName("private_favorites_count") val privateFavoritesCount: Int
)