package com.jay.countries.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.jay.countries.model.CountryMapper
import com.jay.countries.model.ResponseWrapper
import com.jay.countries.model.Country
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


open class CountriesViewModel : BaseViewModel() {

    val countriesObserver = MutableLiveData<ResponseWrapper<List<Country>>>()
    val progressbarVisibility = ObservableInt()
    val chooseContinentHintVisibility = ObservableInt()

    private val countryMapper = CountryMapper()

    init {
        progressbarVisibility.set(View.GONE)
    }

    fun fetchCountriesList(continentCode: String) {
        progressbarVisibility.set(View.VISIBLE)
        chooseContinentHintVisibility.set(View.GONE)

        val subscribe: Disposable = networkService.getCountriesQuery(continentCode)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
                //transform Response<FindCountriesOfAContinentQuery.Data> to Country object
            .map { response: Response<FindCountriesOfAContinentQuery.Data> ->
                   response.data?.continent()?.countries()
                       ?.map { country: FindCountriesOfAContinentQuery.Country ->
                           countryMapper.getCountryEntity(country)
                       }
            }
            .subscribe(
                {response: List<Country>? ->
                    countriesObserver.postValue(ResponseWrapper(response))
                },

                { error: Throwable ->
                    countriesObserver.postValue(ResponseWrapper(error = error))
                    progressbarVisibility.set(View.VISIBLE)
                },

                { progressbarVisibility.set(View.GONE) })

        disposable.add(subscribe)
    }
}