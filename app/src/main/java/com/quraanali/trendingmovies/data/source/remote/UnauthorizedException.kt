package com.quraanali.trendingmovies.data.source.remote

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class UnauthorizedException constructor(private val moshi: Moshi, errSource: String?) :
    Exception() {
    private var validationMessage: String? = null

    init {
        errSource?.let {
            try {
                val jsonAdapter: JsonAdapter<ErrorResponse> =
                    moshi.adapter(ErrorResponse::class.java)

                val response = jsonAdapter.fromJson(errSource)
                validationMessage = response?.meta?.message

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getLocalizedMessage(): String {
        return validationMessage ?: ""
    }
}

