package com.jay.countries.model

class ResponseWrapper<T>(var response: T? = null, var error: Throwable? = null)