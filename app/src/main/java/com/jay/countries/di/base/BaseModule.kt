package com.jay.countries.di.base

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class BaseModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application {
        return application
    }

}