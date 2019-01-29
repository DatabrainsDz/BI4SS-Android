package com.databrains.bi4ss.models

import com.google.gson.annotations.SerializedName

class Gender (@SerializedName ("M")val male : AdmittedAdjourned,
              @SerializedName ("F") val female : AdmittedAdjourned)