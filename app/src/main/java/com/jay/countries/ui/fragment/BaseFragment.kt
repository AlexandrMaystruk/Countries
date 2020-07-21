package com.jay.countries.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

abstract class BaseFragment : Fragment() {

    companion object {
         val continentDataObserver: MutableLiveData<GetContinentsQuery.Continent> = MutableLiveData()
    }

}

