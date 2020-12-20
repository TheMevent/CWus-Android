package com.yoof.yoof.Interface

import com.yoof.yoof.Model.TokenResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface RetrofitServices {
    @FormUrlEncoded
    @POST("/auth/login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password:String
    ):Call<TokenResponse>

    @FormUrlEncoded
    @POST("/auth/reg")
    fun regUser(
        @Field("email") email: String,
        @Field("username") username: String,
        @Field("password") password:String
    ):Call<TokenResponse>
}