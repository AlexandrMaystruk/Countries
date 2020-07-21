package com.jay.countries.ui.activity

import android.os.Bundle
import com.jay.countries.R
import com.jay.countries.ui.fragment.ContinentsFragment
import com.jay.countries.ui.fragment.CountriesFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        attachFragments()
    }

    private fun attachFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.left_main_container, ContinentsFragment.newInstance())
            .commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.right_main_container, CountriesFragment.newInstance())
            .commit()
    }
}