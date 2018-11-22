package com.mht.favqs.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mht.favqs.network.Constants.Companion.ACCESS_TOKEN
import com.mht.favqs.network.Constants.Companion.LOGIN
import com.mht.favqs.viewModel.MyProfileViewModel
import com.mht.favqs.R
import com.mht.favqs.repository.SecurePreferences
import com.mht.favqs.model.UserInfo
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_profile.*
import java.lang.Exception

class MyProfileActivity : AppCompatActivity() {

    lateinit var viewModel: MyProfileViewModel
    var login: String = ""
    var accessToken: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)
        initVM()
        login = SecurePreferences.getInstance(this).getString(this,LOGIN,"")
        accessToken = SecurePreferences.getInstance(this).getString(this, ACCESS_TOKEN,"")
        viewModel.getMyProfile(login,accessToken)
    }

    private fun initVM() {
        viewModel = ViewModelProviders.of(this).get(MyProfileViewModel::class.java)
        viewModel.userInfo.observe(this, Observer {userInfo ->
            bindDataToUi(userInfo!!)
        })
    }

    private fun bindDataToUi(userInfo: UserInfo) {
        profile_login.text = userInfo.login
        profile_email.text = userInfo.accountDetails.email
        profile_fav_count.text = "${userInfo.publicFavoritesCount} public favorites quotes"
        Picasso.get().load(userInfo.picUrl)
                .into(profile_img, object : Callback {
                    override fun onSuccess() {
                        profile_img_loader?.visibility = View.GONE
                    }

                    override fun onError(e: Exception?) {
                        profile_img_loader?.visibility = View.GONE
                    }

                })
    }

    companion object {
        fun newIntent(ctx: Context): Intent {
            return Intent(ctx, MyProfileActivity::class.java)
        }
    }

}