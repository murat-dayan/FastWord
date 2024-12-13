package com.muratdayan.game.domain.model

import android.util.Log
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class UserStatsModel(
    val user_id: String,
    val energy: Int?=10,
    val token: Int?=50,
    val emerald: Int?=50,
    val updated_at: String? = null
){

    fun getUpdatedAtAsInstant(): Instant? {
        return updated_at?.let {
            // Fazla hassasiyet varsa, sadece ilk 23 karakteri al (milisaniye hassasiyetine indir)
            val formattedDate = it.take(23) // "2024-12-10T18:03:01.351"
            try {
                Instant.parse(formattedDate) // ISO 8601 formatına uygun olarak parse et
            } catch (e: Exception) {
                Log.d("UserStatsModel", "getUpdatedAtAsInstant: parse error updatedat is null $e")
                null // Parse işlemi başarısız olursa null döndür
            }
        }
    }
}
