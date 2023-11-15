package com.quraanali.trendingmovies.domain.common

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.Executors
import javax.inject.Inject

class GetTmdbApiKeyFromRemoteConfigUseCase @Inject constructor(
) {
    operator fun invoke(): Flow<String> {
        return callbackFlow {
            val executor = Executors.newSingleThreadExecutor()

            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            }
            remoteConfig.setConfigSettingsAsync(configSettings)

            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(executor) { task ->
                    if (task.isSuccessful) {
                        trySend(remoteConfig.getString("TMDB_API_READ_ACCESS_TOKEN"))
                    } else {
                        trySend("")
                    }
                }
            awaitClose()
        }
    }
}