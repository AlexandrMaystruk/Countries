package com.jay.countries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jay.countries.R
import com.jay.countries.util.ui.ScaleViewBehaviour
import kotlinx.android.synthetic.main.navigation_fragment.*
import java.lang.NullPointerException

class NavigationFragment : BaseFragment(){

    var navigationItemSelectedListener: OnNavigationItemSelectedListener? = null
    private val scaleViewBehaviour = ScaleViewBehaviour()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.navigation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        onClickNavigateView()
    }

    private fun onClickNavigateView() {
        navigationItemSelectedListener ?: throw NullPointerException("listener must be set")

        scaleViewBehaviour.setViews(all, liked)
        scaleViewBehaviour.enlargeView(all)

        all.setOnClickListener {
            navigationItemSelectedListener?.onAllInformation()
            scaleViewBehaviour.enlargeView(all)
        }
        liked.setOnClickListener {
            navigationItemSelectedListener?.onLiked()
            scaleViewBehaviour.enlargeView(liked)
        }
    }

    companion object {
        fun newInstance() = NavigationFragment()
    }

    interface OnNavigationItemSelectedListener {
        fun onAllInformation()
        fun onLiked()
    }
}