package com.jay.countries.ui.activity

import android.os.Bundle
import com.jay.countries.R
import com.jay.countries.ui.fragment.ContinentsFragment
import com.jay.countries.ui.fragment.CountriesFragment
import com.jay.countries.ui.fragment.LikedCountriesFragment
import com.jay.countries.ui.fragment.NavigationFragment

class MainActivity : BaseActivity()  {

    private val continentsFragment = ContinentsFragment.newInstance()
    private val countriesFragment = CountriesFragment.newInstance()
    private val navigationFragment = NavigationFragment.newInstance()
    private val likedCountriesFragment = LikedCountriesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navigationFragment.navigationItemSelectedListener = this

        attachFragments()
    }

//    override fun onAllInformation() {
//        supportFragmentManager.beginTransaction()
//            .remove(likedCountriesFragment)
//            .commit()
//    }
//
//    override fun onLiked() {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container_4, likedCountriesFragment)
//            .commit()
//    }

    private fun attachFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container_1, continentsFragment)
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.container_2, countriesFragment)
            .commit()

//        supportFragmentManager.beginTransaction()
//            .add(R.id.container_3, navigationFragment)
//            .commit()
    }
}