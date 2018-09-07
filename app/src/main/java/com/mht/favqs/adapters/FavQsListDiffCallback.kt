package com.mht.favqs.adapters

import android.support.v7.util.DiffUtil
import com.mht.favqs.model.Quote

class FavQsListDiffCallback(var fQuotesList: ArrayList<Quote>, var quotes: List<Quote>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return fQuotesList[oldItemPosition].id.equals(quotes[newItemPosition].id)
    }

    override fun getOldListSize(): Int {
        return fQuotesList.size
    }

    override fun getNewListSize(): Int {
        return quotes.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return fQuotesList[oldItemPosition].id == quotes[newItemPosition].id
    }

}
