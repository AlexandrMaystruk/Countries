package com.jay.countries.ui.viewmodel

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.jay.countries.BaseApplication
import com.jay.countries.network.NetworkService
import io.reactivex.disposables.CompositeDisposable


open class BaseViewModel : ViewModel(), LifecycleObserver {

    protected val disposable = CompositeDisposable()
    protected val context: Context = BaseApplication.baseComponent.application.applicationContext
    protected val networkService = NetworkService()

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        disposable.clear()
    }
}