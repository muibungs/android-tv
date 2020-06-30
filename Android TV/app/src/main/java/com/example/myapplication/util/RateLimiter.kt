package com.example.myapplication.util

import android.os.SystemClock
import androidx.collection.ArrayMap
import com.amazonaws.mobileconnectors.appsync.fetcher.AppSyncResponseFetchers
import com.apollographql.apollo.fetcher.ResponseFetcher
import java.util.concurrent.TimeUnit

class RateLimiter<in KEY>(timeout: Int, timeUnit: TimeUnit) {
    private val timestamps = ArrayMap<KEY, Long>()
    private val timeout = timeUnit.toMillis(timeout.toLong())

    @Synchronized
    fun shouldFetch(key: KEY): Mode {
        val lastFetched = timestamps[key]
        val now = now()
        if (lastFetched == null) {
            timestamps[key] = now
            return Mode.INIT
        }
        if (now - lastFetched > timeout) {
            timestamps[key] = now
            return Mode.NETWORK
        }
        return Mode.CACHE
    }

    private fun now() = SystemClock.uptimeMillis()

    @Synchronized
    fun reset(key: KEY) {
        timestamps.remove(key)
    }

    companion object {
        enum class Mode {
            INIT, CACHE, NETWORK
        }

        fun awsFetcher(isForceFetch: Boolean, shouldFetch: Mode): ResponseFetcher =
            when (isForceFetch) {
                true -> AppSyncResponseFetchers.NETWORK_FIRST
                else -> {
                    when (shouldFetch) {
                        Mode.INIT -> AppSyncResponseFetchers.NETWORK_FIRST
                        Mode.CACHE -> AppSyncResponseFetchers.CACHE_FIRST
                        Mode.NETWORK -> AppSyncResponseFetchers.NETWORK_FIRST
                    }
                }
            }
    }
}
