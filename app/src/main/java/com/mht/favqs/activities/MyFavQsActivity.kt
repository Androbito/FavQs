package com.mht.favqs.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log.i
import android.view.View
import com.gaurav.cdsrecyclerview.CdsItemTouchCallback
import com.gaurav.cdsrecyclerview.CdsRecyclerView
import com.mht.favqs.*
import com.mht.favqs.adapters.FavQsAdapter
import com.mht.favqs.model.FavQuotesResponse
import com.mht.favqs.model.Quote
import com.mht.favqs.network.Constants
import com.mht.favqs.repository.SecurePreferences
import com.mht.favqs.viewModel.FavQsViewModel


class MyFavQsActivity : AppCompatActivity(), Observer<FavQuotesResponse>, CdsRecyclerView.ItemClickListener, CdsItemTouchCallback.ItemDragCompleteListener {
    override fun onItemDragComplete(fromPosition: Int, toPosition: Int) {

    }

    private var data: ArrayList<Quote> = ArrayList()

    private lateinit var mRecyclerView: CdsRecyclerView

    private lateinit var mRecyclerViewAdapter: FavQsAdapter

    private lateinit var viewModel: FavQsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_favqs)

        initUi()
        initVM()
    }


    private fun initUi() {
        mRecyclerView = CdsRecyclerView(this)
        mRecyclerView = findViewById(R.id.favRecyclerView)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        mRecyclerViewAdapter = FavQsAdapter(this, data)
        mRecyclerView.adapter = mRecyclerViewAdapter

        mRecyclerView.setItemClickListener(this)
        mRecyclerView.enableItemDrag()
        mRecyclerView.setItemDragCompleteListener(this)

    }


    private fun initVM() {
        viewModel = ViewModelProviders.of(this).get(FavQsViewModel::class.java)
        val accessToken = SecurePreferences.getInstance(this@MyFavQsActivity).getString(this@MyFavQsActivity, Constants.ACCESS_TOKEN, "")
        val login = SecurePreferences.getInstance(this@MyFavQsActivity).getString(this@MyFavQsActivity, Constants.LOGIN, "")
        viewModel.getMyFavoriteQs(login, accessToken)
        viewModel.favQsLiveData.observe(this, this)

    }

    override fun onChanged(response: FavQuotesResponse?) {
        if (response != null) {
            (mRecyclerView.adapter as FavQsAdapter).updateFavQuotesList(response.quotes)
        }
    }

    override fun onItemClick(position: Int) {
        i("position", data[position].url)
    }


    companion object {
        fun newIntent(ctx: Context): Intent {
            return Intent(ctx, MyFavQsActivity::class.java)
        }
    }

    fun goToMyProfile(v:View) {
        val intent = MyProfileActivity.newIntent(this)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}