package com.muratdayan.common

sealed interface DataError : AppError {
    // Uzak sunucuya ait hatalar
    sealed interface Remote : DataError {
        data object RequestTimeout : Remote {
            override val message = "Sunucuya yapılan istek zaman aşımına uğradı."
        }
        data object TooManyRequests : Remote {
            override val message = "Çok fazla istek yapıldı."
        }
        data object NoInternet : Remote {
            override val message = "İnternet bağlantısı yok."
        }
        data object ServerError : Remote {
            override val message = "Sunucuda bir hata oluştu."
        }
        data object SerializationError : Remote {
            override val message = "Veri serileştirilirken bir hata oluştu."
        }
        data object Unknown : Remote {
            override val message = "Bilinmeyen bir uzak hata oluştu."
        }
        data object Unauthorized : Remote {
            override val message = "Yetkisiz erişim."
        }
        data object Forbidden : Remote {
            override val message = "Erişim yasaklandı."
        }
    }

    // Yerel hatalar (disk, cihaz, vs.)
    sealed interface Local : DataError {
        data object DiskFull : Local {
            override val message = "Disk dolu."
        }
        data object Unknown : Local {
            override val message = "Bilinmeyen bir yerel hata oluştu."
        }
        data object CorruptedData : Local {
            override val message = "Veri bozuldu."
        }
        data object PermissionDenied : Local {
            override val message = "İzin reddedildi."
        }
    }
}