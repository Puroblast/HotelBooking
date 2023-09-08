package com.puroblast.common_recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface CommonAdapterDelegate {

    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: CommonDelegateItem, position: Int)

    fun isOfViewType(item: CommonDelegateItem): Boolean

}
