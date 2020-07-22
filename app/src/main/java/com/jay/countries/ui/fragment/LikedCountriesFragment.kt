package com.jay.countries.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.jay.countries.R
import com.jay.countries.databinding.LikedCountriesFragmentBinding
import com.jay.countries.di.viewmodel.DaggerViewModelComponent
import com.jay.countries.ui.adapter.CountriesAdapter
import com.jay.countries.ui.viewmodel.LikedCountriesViewModel
import kotlinx.android.synthetic.prod.liked_countries_fragment.*
import javax.inject.Inject

class LikedCountriesFragment : BaseFragment() {

    @Inject
    lateinit var likedCountriesViewModel: LikedCountriesViewModel

    private val countriesAdapter = CountriesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerViewModelComponent.builder().activity(activity!!).build().inject(this)

        val binding: LikedCountriesFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.liked_countries_fragment, container, false)
        binding.countriesVM = likedCountriesViewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupLikedCountriesList()

        likedCountriesViewModel.fetchLikedCountries()

        observeLikedCountries()
    }

    private fun observeLikedCountries() {
        likedCountriesViewModel.likedCountryObserver.observe(viewLifecycleOwner,
            Observer { response ->

                if (response.error != null) {
                    Toast.makeText(context, response?.error?.message, Toast.LENGTH_LONG).show()
                } else {
                    countriesAdapter.setCountries(response.response!!)
                }
            })
    }

    private fun setupLikedCountriesList() {
        countries_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }
    }

    companion object {
        fun newInstance() = LikedCountriesFragment()
    }
}