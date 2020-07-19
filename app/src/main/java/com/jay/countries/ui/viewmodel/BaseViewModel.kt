package com.jay.countries.ui.viewmodel

import GetContinentsQuery
import android.content.Context
import androidx.lifecycle.*
import com.jay.countries.BaseApplication
import com.jay.countries.network.NetworkService
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel(), LifecycleObserver {

    val dataObserver: MutableLiveData<GetContinentsQuery.Continent> = MutableLiveData()

    protected val disposable = CompositeDisposable()
    protected val context: Context = BaseApplication.baseComponent.application.applicationContext
    protected val networkService = NetworkService()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable.clear()
    }
}