package com.jay.countries.repository.database.countries

import androidx.room.*
import io.reactivex.Flowable

@Dao
interface CountriesDao {

    @Query("SELECT * FROM Country")
    fun getAll(): Flowable<List<Country>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(all: List<Country>)

    @Query("DELETE FROM Country WHERE name = :countryName")
    fun delete(countryName: String)
}