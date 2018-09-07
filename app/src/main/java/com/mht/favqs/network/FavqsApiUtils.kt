package com.mht.favqs.network

object FavqsApiUtils {

    val BASE_URL = "https://favqs.com/api/"

    val apiService: FavqsApiService
        get() = FavqsApiClient.getClient(BASE_URL)!!.create(FavqsApiService::class.java)


}