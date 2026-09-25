package com.example.cryptocurrency.core.data.networking

import com.example.cryptocurrency.core.domain.util.DataError
import com.example.cryptocurrency.core.domain.util.Result
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.Network> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        android.util.Log.e("MY_ERROR", "🚨🚨🚨 الخلل هنا: ${e.message}", e)
        return Result.Error(DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        e.printStackTrace()
        if (e is CancellationException) throw e
        return Result.Error(DataError.Network.UNKNOWN)
    }

    return if (response.status.value in 200..299) {
        try {
            Result.Success(response.body<T>())
        } catch (e: Exception) {
            android.util.Log.e("MY_ERROR", "🚨🚨🚨 الحقل الناقص هو: ${e.message}", e)
            Result.Error(DataError.Network.SERIALIZATION)
        }
    } else {
        response.toResult()
    }
}