package com.jay.countries.repository.database.countries

import androidx.lifecycle.LifecycleObserver
import com.jay.countries.BaseApplication
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CountriesDatabaseManager private constructor() : LifecycleObserver {

    private val disposable = CompositeDisposable()

    fun saveToDatabase(country: Country) {
//        val countryEntity: Countries = Countries().apply {
//            name = country.name()
//            currency = country.currency()
//            native = country.native_()
//            phone = country.phone()
//        }

        val subscribe: Disposable = Completable.fromAction { database.insert(country) }
            .subscribeOn(Schedulers.io())
            .subscribe()

        disposable.add(subscribe)
    }

    fun removeFromDatabaseByName(countryName: String) {
        val subscribe: Disposable = Completable.fromAction { database.delete(countryName) }
            .subscribeOn(Schedulers.io())
            .subscribe()

        disposable.add(subscribe)
    }

    fun clearResources() {
        disposable.clear()
    }

    companion object {
        private val database: CountriesDao = BaseApplication.dataBase.countriesDao()
        val instance = CountriesDatabaseManager()
    }
}