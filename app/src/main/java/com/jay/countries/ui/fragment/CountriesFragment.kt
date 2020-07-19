package com.jay.countries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.apollographql.apollo.api.Response
import com.jay.countries.R
import com.jay.countries.di.viewmodel.DaggerViewModelComponent
import com.jay.countries.model.ResponseWrapper
import com.jay.countries.ui.viewmodel.CountriesViewModel
import javax.inject.Inject

class CountriesFragment : Fragment() {

    @Inject
    lateinit var countriesViewModel: CountriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        DaggerViewModelComponent.builder().activity(activity!!).build().inject(this)
        super.onActivityCreated(savedInstanceState)

        observeContinentChoice()
        observeCountries()
    }

    private fun observeContinentChoice() {
        countriesViewModel.dataObserver.observe(viewLifecycleOwner,
            Observer {continent: GetContinentsQuery.Continent ->

                countriesViewModel.fetchCountriesList(continent.code())
        })
    }

    private fun observeCountries() {
        countriesViewModel.countriesObserver.observe(viewLifecycleOwner,
        Observer { data: ResponseWrapper<Response<FindCountriesOfAContinentQuery.Data>> ->

            if (data.error != null){
                //todo handle error
            } else {

            }
        })
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }

}