package com.mht.favqs.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mht.favqs.repository.FavqsRepository
import com.mht.favqs.model.UserInfo
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

class MyProfileViewModel : ViewModel() {


    var userInfo = MutableLiveData<UserInfo>()
    var errorUserInfo = MutableLiveData<String>()

    fun getMyProfile(login:String, accessToken:String){
        FavqsRepository.getUserInfo(login, accessToken, object : Subscriber<UserInfo> {
            override fun onComplete() = Unit

            override fun onSubscribe(s: Subscription?) = Unit

            override fun onNext(info: UserInfo?) {
                userInfo.postValue(info)
            }

            override fun onError(t: Throwable?) {
                errorUserInfo.postValue(t?.message)
            }

        })
    }
}