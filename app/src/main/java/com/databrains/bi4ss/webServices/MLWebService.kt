package com.databrains.bi4ss.webServices

import com.databrains.bi4ss.models.Prediction
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface MLWebService {

    companion object {
        const val BASE_URL_IP = "http://192.168.43.36:5000"
        private const val BASE_URL = "$BASE_URL_IP/"

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @GET("profile/0/{gender}/{nationality}/{city}/{bac}/{age}")
    fun profileCheckSuccess(@Path("gender") gender: String,
                            @Path("nationality") nationality: Int,
                            @Path("city") city: Int,
                            @Path("bac") bac: Double,
                            @Path("age") age: Int): Call<Prediction>

}