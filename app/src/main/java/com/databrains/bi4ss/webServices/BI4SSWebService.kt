package com.databrains.bi4ss.webServices

import com.databrains.bi4ss.models.AssociationJson
import com.databrains.bi4ss.models.Response
import com.databrains.bi4ss.models.StatisticsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BI4SSWebService {

    companion object {
        const val BASE_URL_IP = "http://192.168.137.224"
        private const val BASE_URL = "$BASE_URL_IP/databrains/"

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @GET("students/auth")
    fun connect(@Query("student_id") studentId: String,
                @Query("current_year") currentYear: String,
                @Query("level") studyLevel: String): Call<Response>

    @GET("admittedadjourned/{yearScholar}")
    fun getStatistics(@Path("yearScholar") yearScholar: String,
                      @Query("level") level: String,
                      @Query("current_year") year: String): Call<StatisticsResponse>

    @GET("/databrains/subjects/association?semester=1")
    fun getAssociations(@Query("current_year") currentYear: String,
                        @Query("level") level: String): Call<AssociationJson>

}