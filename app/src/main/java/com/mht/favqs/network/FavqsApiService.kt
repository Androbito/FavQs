package com.mht.favqs.network

import com.mht.favqs.model.FavQuotesResponse
import com.mht.favqs.model.SessionRequest
import com.mht.favqs.model.UserInfo
import com.mht.favqs.model.UserSession
import retrofit2.Call
import retrofit2.http.*

interface FavqsApiService {


    @POST("session")
    fun getSession(@HeaderMap headerAuthorization: Map<String, String>,
                   @Body sessionReq: SessionRequest): Call<UserSession>


    @GET("users")
    fun getProfile(@HeaderMap headerAuthorization: Map<String, String>,
                   @Query("login") login: String): Call<UserInfo>

    @GET("quotes")
    fun getFavQuotes(@HeaderMap headerAuthorization: Map<String, String>,
                     @Query("filter") login: String,
                     @Query("type") type: String): Call<FavQuotesResponse>
}