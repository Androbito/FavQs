package com.mht.favqs.adapters

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gaurav.cdsrecyclerview.CdsRecyclerViewAdapter
import com.mht.favqs.R
import com.mht.favqs.model.Quote


class FavQsAdapter(var context: Context?, var data: ArrayList<Quote>) : CdsRecyclerViewAdapter<Quote, FavQsAdapter.MyViewHolder>(context, data) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.favqs_itemview, parent, false));
    }

    class MyViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
        var mTextView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).mTextView.text = list?.get(position)?.body
    }

    fun updateFavQuotesList(quotes: List<Quote>) {
        val diffCallback = FavQsListDiffCallback(this.data, quotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        this.data.addAll(quotes)
        diffResult.dispatchUpdatesTo(this)
    }

}