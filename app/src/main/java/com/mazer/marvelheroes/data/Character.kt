package com.mazer.marvelheroes.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Character(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail


)