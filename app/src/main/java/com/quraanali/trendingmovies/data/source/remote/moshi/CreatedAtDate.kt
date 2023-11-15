package com.quraanali.trendingmovies.data.source.remote.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class CreatedAtDate


class CreatedAtDateAdapter {
    private val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)

    @ToJson
    fun toJson(@CreatedAtDate date: Date?): String? {
        return try {
            date?.let {
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                sdf.format(it)
            }
        } catch (_: Exception) {
            null
        }
    }

    @FromJson
    @CreatedAtDate
    fun fromJson(json: String?): Date? {
        return try {
            json?.let {
                sdf.timeZone = TimeZone.getTimeZone("UTC")
                sdf.parse(it)
            }
        } catch (_: Exception) {
            null
        }
    }
}