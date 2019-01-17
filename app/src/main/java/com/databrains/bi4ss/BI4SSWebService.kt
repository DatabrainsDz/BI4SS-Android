package com.databrains.bi4ss

import com.databrains.bi4ss.models.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BI4SSWebService {

    companion object {
        const val BASE_URL_IP = "http://192.168.1.39/"
        private const val BASE_URL = "$BASE_URL_IP/"

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @GET("studentConnection")
    fun connect(studentId : String , currentYear : String , studyLevel : String) : Call<Response>
}