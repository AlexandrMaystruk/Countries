package com.jay.countries.ui.fragment

import FindCountriesOfAContinentQuery
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo.api.Response
import com.jay.countries.R
import com.jay.countries.di.viewmodel.DaggerViewModelComponent
import com.jay.countries.model.ResponseWrapper
import com.jay.countries.ui.adapter.CountriesAdapter
import com.jay.countries.ui.viewmodel.CountriesViewModel
import kotlinx.android.synthetic.main.countries_fragment.*
import javax.inject.Inject


class CountriesFragment : BaseFragment() {

    @Inject
    lateinit var countriesViewModel: CountriesViewModel

    private val countriesAdapter = CountriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.countries_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        DaggerViewModelComponent.builder().activity(activity!!).build().inject(this)
        super.onActivityCreated(savedInstanceState)

        setupCountriesList()

        observeContinentChoice()
        observeCountries()
    }

    private fun setupCountriesList() {
        countries_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
    }

    private fun observeContinentChoice() {
        continentDataObserver.observe(viewLifecycleOwner,
            Observer {continent: GetContinentsQuery.Continent ->

                countriesViewModel.fetchCountriesList(continent.code())
        })
    }

    private fun observeCountries() {
        countriesViewModel.countriesObserver.observe(viewLifecycleOwner,
        Observer { data: ResponseWrapper<Response<FindCountriesOfAContinentQuery.Data>> ->

            if (data.error != null){
                Toast.makeText(activity, data.error?.message, LENGTH_LONG).show()
            } else {
                data.response?.data?.continent()?.countries()?.let {
                    countriesAdapter.setData(it)
                }
            }
        })
    }

    companion object {
        fun newInstance() = CountriesFragment()
    }

}