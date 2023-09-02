package com.puroblast.common_recycler

import androidx.recyclerview.widget.DiffUtil

object CommonDiffUtilCallback : DiffUtil.ItemCallback<CommonDelegateItem>() {

    override fun areItemsTheSame(
        oldItem: CommonDelegateItem,
        newItem: CommonDelegateItem
    ): Boolean {
        return oldItem::class == newItem::class && oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(
        oldItem: CommonDelegateItem,
        newItem: CommonDelegateItem
    ): Boolean {
        return oldItem.compareToOther(newItem)
    }

    override fun getChangePayload(
        oldItem: CommonDelegateItem,
        newItem: CommonDelegateItem
    ): Any? {
        if (areContentsTheSame(oldItem, newItem)) return newItem

        return super.getChangePayload(oldItem, newItem)
    }
}
