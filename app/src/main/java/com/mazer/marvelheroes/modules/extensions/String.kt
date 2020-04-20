package com.mazer.marvelheroes.modules.extensions

import java.security.MessageDigest

fun String.toMD5(): String{
    val md = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return md.joinToString("") { "%02x".format(it) }
}
