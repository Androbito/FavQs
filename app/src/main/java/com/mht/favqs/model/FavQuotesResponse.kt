package com.mht.favqs.model

import com.google.gson.annotations.SerializedName


data class FavQuotesResponse(
        @SerializedName("page") val page: Int,
        @SerializedName("last_page") val lastPage: Boolean,
        @SerializedName("quotes") val quotes: List<Quote>
)

