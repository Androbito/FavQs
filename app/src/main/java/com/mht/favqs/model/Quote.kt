package com.mht.favqs.model

import com.google.gson.annotations.SerializedName


data class Quote(
        @SerializedName("id") val id: Int,
        @SerializedName("dialogue") val dialogue: Boolean,
        @SerializedName("private") val private: Boolean,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("url") val url: String,
        @SerializedName("favorites_count") val favoritesCount: Int,
        @SerializedName("upvotes_count") val upvotesCount: Int,
        @SerializedName("downvotes_count") val downvotesCount: Int,
        @SerializedName("author") val author: String,
        @SerializedName("author_permalink") val authorPermalink: String,
        @SerializedName("body") val body: String,
        @SerializedName("user_details") val userDetails: UserDetails
)

data class UserDetails(
        @SerializedName("favorite") val favorite: Boolean,
        @SerializedName("upvote") val upvote: Boolean,
        @SerializedName("downvote") val downvote: Boolean,
        @SerializedName("hidden") val hidden: Boolean
)