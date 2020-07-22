package com.jay.countries

import android.app.Application
import androidx.room.Room
import com.jay.countries.di.base.BaseComponent
import com.jay.countries.di.base.BaseModule
import com.jay.countries.di.base.DaggerBaseComponent
import com.jay.countries.di.network.DaggerNetworkComponent
import com.jay.countries.di.network.NetworkComponent
import com.jay.countries.repository.database.Database

class BaseApplication: Application() {

    override fun onCreate() {
        baseComponent = DaggerBaseComponent.builder().baseModule(BaseModule(this)).build()
        networkComponent = DaggerNetworkComponent.create()
        dataBase = Room.databaseBuilder(this, Database::class.java, "Database").build()
        super.onCreate()
    }

    companion object {
        lateinit var baseComponent: BaseComponent
        lateinit var networkComponent: NetworkComponent
        lateinit var dataBase: Database
    }
}