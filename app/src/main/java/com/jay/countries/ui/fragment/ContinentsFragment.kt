package com.jay.countries.ui.fragment

import GetContinentsQuery
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.apollographql.apollo.api.Response
import com.jay.countries.R
import com.jay.countries.databinding.ContinentsFragmentBinding
import com.jay.countries.di.viewmodel.DaggerViewModelComponent
import com.jay.countries.model.ResponseWrapper
import com.jay.countries.ui.adapter.ContinentsAdapter
import com.jay.countries.ui.viewmodel.ContinentsViewModel
import kotlinx.android.synthetic.main.continents_fragment.*
import javax.inject.Inject


class ContinentsFragment : BaseFragment() {

    @Inject
    lateinit var continentsVM: ContinentsViewModel

    private val continentsAdapter = ContinentsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        DaggerViewModelComponent.builder().activity(activity!!).build().inject(this)

        val binding: ContinentsFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.continents_fragment, container, false)
        binding.continentVM = continentsVM

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        continentsVM.fetchContinentsList()

        setupContinentsList()

        observeContinentsList()
        observeContinentsListItemClick()
    }

    private fun observeContinentsList() {
        continentsVM.continentsObserver.observe(viewLifecycleOwner,
            Observer { data: ResponseWrapper<Response<GetContinentsQuery.Data>> ->

                if (data.error != null) {
                    Toast.makeText(activity, data.error?.message, LENGTH_LONG).show()
                } else {
                    data.response?.data?.continents()?.let { continentsAdapter.setData(it) }
                }
            })
    }

    private fun setupContinentsList() {
        continents_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = continentsAdapter
        }
    }

    private fun observeContinentsListItemClick() {
        continentsAdapter.clickObserver.observe(viewLifecycleOwner,
            Observer { data: GetContinentsQuery.Continent ->

                continentDataObserver.value = data
            })
    }

    companion object {
        fun newInstance() = ContinentsFragment()
    }

}