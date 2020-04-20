package com.mazer.marvelheroes.modules.models.pojos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mazer.marvelheroes.modules.models.Comic

class ComicResponse (
    @SerializedName("code")
    @Expose
    val code: Int,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("data")
    @Expose
    val data: DataComic
)

class DataComic(
    @SerializedName("offset")
    @Expose
    val offset: Int,

    @SerializedName("limit")
    @Expose
    val limit: Int,

    @SerializedName("total")
    @Expose
    val total: Int,

    @SerializedName("results")
    @Expose
    val char: List<Comic>
)
