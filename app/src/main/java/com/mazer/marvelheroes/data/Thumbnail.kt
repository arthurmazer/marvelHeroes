package com.mazer.marvelheroes.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Thumbnail (
    @SerializedName("path")
    @Expose
    val path: String,

    @SerializedName("extension")
    @Expose
    val extension: String

)
