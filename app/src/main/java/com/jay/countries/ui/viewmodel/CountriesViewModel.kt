package com.jay.countries.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.jay.countries.model.ResponseWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class CountriesViewModel : BaseViewModel() {

    val countriesObserver =
        MutableLiveData<ResponseWrapper<Response<FindCountriesOfAContinentQuery.Data>>>()

    val progressbarVisibility = ObservableInt()

    init {
        progressbarVisibility.set(View.GONE)
    }

    fun fetchCountriesList(continentCode: String) {
        progressbarVisibility.set(View.VISIBLE)

        val subscribe: Disposable = networkService.getCountriesQuery(continentCode)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response: Response<FindCountriesOfAContinentQuery.Data> ->
                    countriesObserver.setValue(ResponseWrapper(response))
                },

                { error: Throwable -> countriesObserver.setValue(ResponseWrapper(error = error)) },

                { progressbarVisibility.set(View.GONE)})

        disposable.add(subscribe)
    }
}