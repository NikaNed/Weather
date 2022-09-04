package com.example.weather.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("APPID", API_KEY).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }

    companion object {
        const val API_KEY = "cf6776e097a42e7104c009431a5c9ef8"
    }
}

