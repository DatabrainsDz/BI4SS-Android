package com.databrains.bi4ss.models

import com.google.gson.annotations.SerializedName

class Nationality (@SerializedName("algérienne")val algerienne : AdmittedAdjourned,
                   @SerializedName("etranger") val etranger : AdmittedAdjourned)