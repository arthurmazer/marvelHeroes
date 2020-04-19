package com.mazer.marvelheroes.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CharacterResponse (
    @SerializedName("code")
    @Expose
    val code: Int,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("data")
    @Expose
    val data: Data
)

class Data(
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
    val char: List<Character>
)
