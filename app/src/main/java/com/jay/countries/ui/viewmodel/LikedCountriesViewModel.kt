package com.jay.countries.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.jay.countries.BaseApplication
import com.jay.countries.model.ResponseWrapper
import com.jay.countries.repository.database.countries.Country
import com.jay.countries.repository.database.countries.CountriesDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LikedCountriesViewModel : BaseViewModel() {

    val listIsEmptyHintVisibility = ObservableInt()
    val likedCountryObserver: MutableLiveData<ResponseWrapper<List<Country>>> = MutableLiveData()

    private val database: CountriesDao = BaseApplication.dataBase.countriesDao()

    fun fetchLikedCountries() {
        val subscribe: Disposable = database.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { countryList: List<Country> ->
                    likedCountryObserver.postValue(ResponseWrapper(countryList))
                },

                { error: Throwable -> likedCountryObserver.postValue(ResponseWrapper(error = error)) },

                { listIsEmptyHintVisibility.set(View.GONE) })

        disposable.add(subscribe)
    }
}