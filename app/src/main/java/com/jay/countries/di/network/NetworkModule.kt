package com.jay.countries.di.network

import com.apollographql.apollo.ApolloClient
import com.jay.countries.network.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module
class NetworkModule {

    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(NetworkService.BASE_URL)
            .okHttpClient(OkHttpClient.Builder().build())
            .build()
    }
}