package com.mht.favqs.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mht.favqs.model.FavQuotesResponse
import com.mht.favqs.repository.FavqsRepository
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class FavQsViewModel : ViewModel() {


    var favQsLiveData = MutableLiveData<FavQuotesResponse>()
    var errorFavQs = MutableLiveData<String>()

    fun getMyFavoriteQs(login:String,accessToken:String ){
        FavqsRepository.getFavQsList(login, accessToken, object : Subscriber<FavQuotesResponse> {
            override fun onComplete() = Unit

            override fun onSubscribe(s: Subscription?) = Unit

            override fun onNext(session: FavQuotesResponse?) {
                favQsLiveData.postValue(session)
            }

            override fun onError(t: Throwable?) {
                errorFavQs.postValue(t?.message)
            }

        })
    }
}