package com.databrains.bi4ss.models

import com.google.gson.annotations.SerializedName

class StatisticsResponse(@SerializedName("title") val title: String,
                         @SerializedName("status") val status: String,
                         @SerializedName("data") val data: DataStats)