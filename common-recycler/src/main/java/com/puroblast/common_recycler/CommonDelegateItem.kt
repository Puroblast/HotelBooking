package com.puroblast.common_recycler

interface CommonDelegateItem {

    fun content(): Any
    fun id(): Int
    fun compareToOther(other: CommonDelegateItem): Boolean

}
