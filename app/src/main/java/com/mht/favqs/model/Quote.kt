package com.mht.favqs.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Quote(
        val id: Int,
        val dialogue: Boolean,
        val private: Boolean,
        val tags: List<String>,
        val url: String,
        @Json(name="favorites_count") val favoritesCount: Int,
        @Json(name="upvotes_count") val upvotesCount: Int,
        @Json(name="downvotes_count") val downvotesCount: Int,
        val author: String,
        @Json(name="author_permalink") val authorPermalink: String,
        val body: String,
        @Json(name="user_details") val userDetails: UserDetails
)
@JsonClass(generateAdapter = true)
data class UserDetails(
        val favorite: Boolean,
        val upvote: Boolean,
        val downvote: Boolean,
        val hidden: Boolean
)