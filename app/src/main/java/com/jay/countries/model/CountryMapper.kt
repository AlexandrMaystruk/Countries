package com.jay.countries.model

class CountryMapper {

    fun getCountryEntity(obj: FindCountriesOfAContinentQuery.Country): Country {
        return Country().apply {
            name = obj.name()
            currency = obj.currency()
            native = obj.native_()
            phone = obj.phone()
        }
    }
}