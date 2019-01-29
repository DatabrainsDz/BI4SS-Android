package com.databrains.bi4ss.models;

import com.google.gson.annotations.SerializedName

public class DataAssociationObject
              (@SerializedName("subject")val subject: String, @SerializedName("relatedTo")val relatedTo: Array<String>)