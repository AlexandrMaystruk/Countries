package com.jay.countries.util.ui

import android.view.View

class ScaleViewBehaviour {

    private val viewList: MutableList<View> = mutableListOf()

    fun setViews(vararg view: View) {
        viewList.addAll(view)
    }

    fun enlargeView(view: View, coefficient: Float = 1.2f) {
        if (viewList.isEmpty()) throw IllegalAccessError("the view list should not be empty")

        viewList.forEach { it.animate().setDuration(200).scaleX(1f).scaleY(1f).start() }
        view.animate().setDuration(200).scaleX(coefficient).scaleY(coefficient).start()
    }

    fun enlargeOneView(view: View, coefficient: Float = 1.2f) {
        view.animate().setDuration(200).scaleX(coefficient).scaleY(coefficient).start()
    }
}