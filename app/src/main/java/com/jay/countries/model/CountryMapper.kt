package com.jay.countries.model

import com.jay.countries.repository.database.countries.Country

class CountryMapper {

    fun getCountryEntity(obj: FindCountriesOfAContinentQuery.Country): Country {
        return Country().apply {
            name = obj.name()
            currency = obj.currency()
            native = obj.currency()
            phone = obj.currency()
        }
    }
}