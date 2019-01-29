package com.databrains.bi4ss.models

import com.google.gson.annotations.SerializedName

class DataStats (@SerializedName("byGender") val gender : Gender,
                 @SerializedName("byNationality") val nationality : Nationality )