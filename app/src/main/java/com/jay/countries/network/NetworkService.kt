package com.jay.countries.network

import FindCountriesOfAContinentQuery
import GetContinentsQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.rxQuery
import com.jay.countries.BaseApplication
import io.reactivex.Observable

class NetworkService {

    private val apolloClient: ApolloClient = BaseApplication.networkComponent.apolloClient

    fun getContinentsQuery(): Observable<Response<GetContinentsQuery.Data>> {
        return apolloClient.rxQuery(GetContinentsQuery.builder().build())
    }

    fun getCountriesQuery(continentCode: String): Observable<Response<FindCountriesOfAContinentQuery.Data>> {
        return apolloClient.rxQuery(FindCountriesOfAContinentQuery.builder().code(continentCode).build())
    }

    companion object {
        const val BASE_URL = "https://countries.trevorblades.com"
    }
}