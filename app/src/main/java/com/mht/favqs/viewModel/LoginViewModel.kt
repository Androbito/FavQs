package com.mht.favqs.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mht.favqs.repository.FavqsRepository
import com.mht.favqs.model.UserSession
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class LoginViewModel : ViewModel() {


    var userSession = MutableLiveData<UserSession>()
    var errorUserSession = MutableLiveData<String>()

    fun signIn(login:String, password:String){
        FavqsRepository.getSession(login, password, object : Subscriber<UserSession> {
            override fun onComplete() = Unit

            override fun onSubscribe(s: Subscription?) = Unit

            override fun onNext(session: UserSession?) {
                userSession.postValue(session)
            }

            override fun onError(t: Throwable?) {
                errorUserSession.postValue(t?.message)
            }

        })
    }
}