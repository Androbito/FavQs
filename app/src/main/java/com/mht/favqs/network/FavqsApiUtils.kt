package com.mht.favqs.network

import com.mht.favqs.network.Constants.Companion.BASE_URL

object FavqsApiUtils {

    val apiService: FavqsApiService
        get() = FavqsApiClient.getClient(BASE_URL)!!.create(FavqsApiService::class.java)


}