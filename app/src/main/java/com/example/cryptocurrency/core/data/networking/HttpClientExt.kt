package com.example.cryptocurrency.core.data.networking

import com.example.cryptocurrency.core.domain.util.DataError
import com.example.cryptocurrency.core.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> safeGet(
    client: HttpClient,
    url: String,
    block: io.ktor.client.request.HttpRequestBuilder.() -> Unit = {}
): Result<T, DataError.Network> {
    return safeCall {
        client.get(url, block)
    }
}

fun HttpResponse.toResult(): Result<Nothing, DataError.Network> {
    return when (status.value) {
        in 200..299 -> Result.Error(DataError.Network.UNKNOWN) // البيانات الناجحة يتم تمريرها داخل safeCall
        401 -> Result.Error(DataError.Network.UNAUTHORIZED)
        408 -> Result.Error(DataError.Network.REQUEST_TIMEOUT)
        409 -> Result.Error(DataError.Network.CONFLICT)
        413 -> Result.Error(DataError.Network.PAYLOAD_TOO_LARGE)
        429 -> Result.Error(DataError.Network.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Network.SERVER_ERROR)
        else -> Result.Error(DataError.Network.UNKNOWN)
    }
}