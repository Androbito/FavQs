package com.mht.favqs.repository;

import com.mht.favqs.model.*
import com.mht.favqs.network.Constants.Companion.APP_TOKEN
import com.mht.favqs.network.FavqsApiUtils
import org.reactivestreams.Subscriber
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response


object FavqsRepository {

    var apiService = FavqsApiUtils.apiService

    fun getSession(login: String, password: String, subscriber: Subscriber<UserSession>) {

        var header: Map<String, String> = hashMapOf("Content-Type" to "application/json", "Authorization" to "Token token=\"$APP_TOKEN\"")
        var bodyRequest = SessionRequest(User(login, password))

        apiService.getSession(header, bodyRequest).enqueue(object : Callback<UserSession> {
            override fun onFailure(call: Call<UserSession>?, t: Throwable?) {
                subscriber.onError(t)
            }

            override fun onResponse(call: Call<UserSession>?, response: Response<UserSession>?) {
                if (response!!.isSuccessful) {
                    if (response.body()?.token == null)
                        subscriber.onError(object : Throwable() {
                            override val message: String?
                                get() = response.body()?.message

                        })
                    else
                        subscriber.onNext(response.body())
                } else {
                    subscriber.onError(HttpException(response))
                }
            }

        })
    }

    fun getUserInfo(login: String, accessToken: String, subscriber: Subscriber<UserInfo>) {

        var header: Map<String, String> = hashMapOf("Content-Type" to "application/json", "Authorization" to "Token token=\"$APP_TOKEN\"", "User-Token" to accessToken)


        apiService.getProfile(header, login).enqueue(object : Callback<UserInfo> {
            override fun onFailure(call: Call<UserInfo>?, t: Throwable?) {
                subscriber.onError(t)
            }

            override fun onResponse(call: Call<UserInfo>?, response: Response<UserInfo>?) {
                if (response!!.isSuccessful) {
                    subscriber.onNext(response.body())
                } else {
                    subscriber.onError(HttpException(response))
                }
            }

        })
    }

    fun getFavQsList(login: String, accessToken: String, subscriber: Subscriber<FavQuotesResponse>) {

        var header: Map<String, String> = hashMapOf("Content-Type" to "application/json", "Authorization" to "Token token=\"$APP_TOKEN\"", "User-Token" to accessToken)


        apiService.getFavQuotes(header, login,"user").enqueue(object : Callback<FavQuotesResponse> {
            override fun onFailure(call: Call<FavQuotesResponse>?, t: Throwable?) {
                subscriber.onError(t)
            }

            override fun onResponse(call: Call<FavQuotesResponse>?, response: Response<FavQuotesResponse>?) {
                if (response!!.isSuccessful) {
                    subscriber.onNext(response.body())
                } else {
                    subscriber.onError(HttpException(response))
                }
            }

        })
    }
}
