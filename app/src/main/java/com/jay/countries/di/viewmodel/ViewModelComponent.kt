package com.jay.countries.di.viewmodel

import androidx.fragment.app.FragmentActivity
import com.jay.countries.ui.fragment.ContinentsFragment
import com.jay.countries.ui.fragment.CountriesFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ViewModelModule::class])
interface ViewModelComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun activity(activity: FragmentActivity): Builder
        fun build(): ViewModelComponent
    }

    fun inject(continentsFragment: ContinentsFragment)
    fun inject(countriesFragment: CountriesFragment)
}