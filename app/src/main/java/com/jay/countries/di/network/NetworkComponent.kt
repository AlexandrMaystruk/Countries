package com.jay.countries.di.network

import com.apollographql.apollo.ApolloClient
import dagger.Component

@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    val apolloClient: ApolloClient
}