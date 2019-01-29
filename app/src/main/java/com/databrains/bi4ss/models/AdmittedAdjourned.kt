package com.databrains.bi4ss.models

import com.google.gson.annotations.SerializedName

class AdmittedAdjourned(@SerializedName("Admis") val admis: String,
                        @SerializedName("Ajourné") val ajourne: String)