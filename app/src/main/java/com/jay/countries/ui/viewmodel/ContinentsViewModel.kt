package com.jay.countries.ui.viewmodel

import GetContinentsQuery
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import com.apollographql.apollo.api.Response
import com.jay.countries.model.ResponseWrapper
import com.jay.countries.network.NetworkService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class ContinentsViewModel : BaseViewModel() {

    val continentsObserver = MutableLiveData<ResponseWrapper<Response<GetContinentsQuery.Data>>>()
    val progressbarVisibility = ObservableInt()

    init {
        progressbarVisibility.set(View.GONE)
    }

    fun fetchContinentsList() {
        progressbarVisibility.set(View.VISIBLE)

        val subscribe: Disposable = networkService.getContinentsQuery()
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response: Response<GetContinentsQuery.Data> ->
                    continentsObserver.setValue(ResponseWrapper(response))
                },

                { error: Throwable ->
                    continentsObserver.setValue(ResponseWrapper(error = error))
                },

                {
                    progressbarVisibility.set(View.GONE)
                })

        disposable.add(subscribe)
    }
}