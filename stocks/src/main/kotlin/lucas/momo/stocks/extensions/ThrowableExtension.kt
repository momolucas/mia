package lucas.momo.stocks.extensions

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException

fun Throwable.toMessageApiError(): String {
    return when (this) {
        is IOException -> {
            if (this is HttpRequestTimeoutException) {
                "Tempo de resposta excedido"
            } else {
                "Erro de conexão, verifique sua internet"
            }
        }
        is ClientRequestException -> "Erro HTTP ${response.status.value}"
        is ServerResponseException -> "Erro HTTP ${response.status.value}"
        else -> "Erro desconhecido"
    }
}
