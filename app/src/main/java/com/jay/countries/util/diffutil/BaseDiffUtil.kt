package com.jay.countries.util.diffutil

import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.jay.countries.BaseApplication

abstract class BaseDiffUtil<T>: DiffUtil.Callback() {

    protected val context: Context = BaseApplication.baseComponent.application.baseContext
    protected val oldList: MutableList<T> = mutableListOf()
    protected val newList: MutableList<T> = mutableListOf()

    fun setData(oldList: List<T>, newList: List<T>) {
        this.oldList.apply { clear(); addAll(oldList) }
        this.newList.apply { clear(); addAll(newList) }
    }

    override fun getNewListSize(): Int = newList.size

    override fun getOldListSize(): Int = oldList.size
}