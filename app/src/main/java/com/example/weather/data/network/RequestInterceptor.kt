package com.example.weather.data.network

//import okhttp3.Interceptor
//import okhttp3.Response
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class RequestInterceptor @Inject constructor() :
//    Interceptor { // добавить в di в moduleNetwork
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//
//        val request = chain.request()
//            .newBuilder()
//            .addHeader(
//                "APPID",
//                API_KEY
//            )
//            .build()
//        return chain.proceed(request)
//    }
//
//    companion object {
//        const val API_KEY = "cf6776e097a42e7104c009431a5c9ef8"
//
//    }
//}
