package com.jay.countries.di.base

import android.app.Application
import dagger.Component

@Component(modules = [BaseModule::class])
interface BaseComponent {

    val application: Application
}