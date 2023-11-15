package com.quraanali.trendingmovies.data.source.remote

import com.squareup.moshi.Moshi
import retrofit2.Response

sealed class DefaultResponse<T> {

    data class Fail<T>(val error: Throwable) : DefaultResponse<T>()
    data class Success<T>(val body: T) : DefaultResponse<T>()

    companion object {
        fun <T> create(moshi: Moshi, response: Response<T>): DefaultResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                Success(body ?: throw EmptyResponseException())

            } else
                when (response.code()) {
                    401 -> {
                        try {
                            val err = response.errorBody()
                            Fail(UnauthorizedException(moshi, err?.string()))
                        } catch (ex: Exception) {
                            Fail(Exception())
                        }
                    }

                    in 400..499 -> {
                        try {
                            val err = response.errorBody()
                            Fail(ValidationException(moshi, err?.string()))
                        } catch (ex: Exception) {
                            Fail(Exception())
                        }
                    }

                    500 -> {
                        Fail(Exception())
                    }

                    else -> {
                        Fail(Exception())
                    }
                }
        }

        fun <T> create(error: Throwable): Fail<T> {
            return Fail(error)
        }

    }

}

