package com.quraanali.trendingmovies.data.source.remote.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson
import java.util.*

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class CreatedAtTimestamp


class CreatedAtTimestampAdapter {

    @ToJson
    fun toJson(@CreatedAtTimestamp date: Date?): Long? {
        return try {
            val timeZone = TimeZone.getTimeZone("UTC")
            val calendar = Calendar.getInstance(timeZone)
            calendar.time = date

            (calendar.timeInMillis / 1000L)
        } catch (_: Exception) {
            null
        }
    }

    @FromJson
    @CreatedAtTimestamp
    fun fromJson(date: Long?): Date? {
        return try {
            date?.let {
                Date(it * 1000)
            }
        } catch (_: Exception) {
            null
        }
    }
}