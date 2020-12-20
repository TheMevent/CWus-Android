package com.yoof.yoof.Common

import com.yoof.yoof.Interface.RetrofitServices
import com.yoof.yoof.Retrofit.RetrofitClient

object Common {
    private const val BASE_URL = "http://192.168.0.106:9000/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}