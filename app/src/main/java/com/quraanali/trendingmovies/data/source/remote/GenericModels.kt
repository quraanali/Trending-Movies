package com.quraanali.trendingmovies.data.source.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MetaCode(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "error_message")
    val message: String?,
    @Json(name = "errors") val errors: Any?
)

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "meta") val meta: MetaCode?
)


@JsonClass(generateAdapter = true)
data class GenericResponse<T>(
    @Json(name = "data")
    val data: T,
    @Json(name = "meta")
    val meta : Meta? = null
){
    @JsonClass(generateAdapter = true)
    data class Meta(
        @Json(name = "last_page")
        val lastPage : Int,
        @Json(name = "current_page")
        val currentPage : Int,
    )
}
