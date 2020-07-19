package com.jay.countries

import android.app.Application
import com.jay.countries.di.base.BaseComponent
import com.jay.countries.di.base.BaseModule
import com.jay.countries.di.base.DaggerBaseComponent
import com.jay.countries.di.network.DaggerNetworkComponent
import com.jay.countries.di.network.NetworkComponent

class BaseApplication: Application() {

    override fun onCreate() {
        baseComponent = DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
        networkComponent = DaggerNetworkComponent.create()
        super.onCreate()
    }

    companion object {
        lateinit var baseComponent: BaseComponent
        lateinit var networkComponent: NetworkComponent
    }
}