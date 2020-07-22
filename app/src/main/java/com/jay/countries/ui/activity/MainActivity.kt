package com.jay.countries.ui.activity

import android.os.Bundle
import com.jay.countries.R
import com.jay.countries.ui.fragment.ContinentsFragment
import com.jay.countries.ui.fragment.CountriesFragment

class MainActivity : BaseActivity()  {

    private val continentsFragment = ContinentsFragment.newInstance()
    private val countriesFragment = CountriesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attachFragments()
    }

    private fun attachFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_1, continentsFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.container_2, countriesFragment)
            .commit()
    }
}