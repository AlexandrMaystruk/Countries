package com.jay.countries.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.countries.repository.database.countries.Country
import com.jay.countries.repository.database.countries.CountriesDao

@Database(entities = [Country::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao
}