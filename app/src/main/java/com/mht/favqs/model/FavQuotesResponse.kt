package com.mht.favqs.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FavQuotesResponse(
        val page: Int,
        @Json(name = "last_page") val lastPage: Boolean,
        val quotes: List<Quote>
)

