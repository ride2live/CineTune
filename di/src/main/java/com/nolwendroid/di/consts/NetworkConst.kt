package com.nolwendroid.di.consts

object NetworkConst {
    const val LAST_FM_BASE_URL = "https://ws.audioscrobbler.com/2.0/"
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val CALL_TIMEOUT: Long = 10
    const val CONNECT_TIMEOUT: Long = 10
    const val WRITE_TIMEOUT: Long = 30
    const val READ_TIMEOUT: Long = 30
    const val BASIC_AUTH_TYPE = "client_credentials"
    const val BASIC_AUTH_CLIENT_ID = "090f7b43230f44d2afdb4430af79319c"

    //todo use encryption later
    const val BASIC_AUTH_CLIENT_SECRET = "e0b9d6b7b02c4e5998aa27f602496cb7"
}