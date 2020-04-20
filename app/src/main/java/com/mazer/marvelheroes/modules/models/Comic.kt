package com.mazer.marvelheroes.modules.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Comic (

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("pageCount")
    @Expose
    val pageCount: Int,

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String,

    @SerializedName("urls")
    @Expose
    val urls: List<Urls>,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Thumbnail

)

data class Urls (

    @SerializedName("type") val type : String,
    @SerializedName("url") val url : String
)

