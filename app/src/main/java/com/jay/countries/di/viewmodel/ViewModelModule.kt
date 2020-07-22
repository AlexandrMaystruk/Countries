package com.jay.countries.di.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.jay.countries.ui.viewmodel.ContinentsViewModel
import com.jay.countries.ui.viewmodel.CountriesViewModel
import com.jay.countries.ui.viewmodel.LikedCountriesViewModel
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    companion object {
        @Provides
        fun provideContinentViewModel(activity: FragmentActivity): ContinentsViewModel {
            return ViewModelProviders.of(activity).get(ContinentsViewModel::class.java)
        }

        @Provides
        fun provideCountriesViewModel(activity: FragmentActivity): CountriesViewModel {
            return ViewModelProviders.of(activity).get(CountriesViewModel::class.java)
        }

        @Provides
        fun provideLikedCountriesViewModel(activity: FragmentActivity): LikedCountriesViewModel {
            return ViewModelProviders.of(activity).get(LikedCountriesViewModel::class.java)
        }
    }
}