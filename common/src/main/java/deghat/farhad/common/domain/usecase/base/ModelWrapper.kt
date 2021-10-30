package deghat.farhad.common.domain.usecase.base

import java.io.IOException

sealed class ModelWrapper<out T> {
    data class ServerError(val message: List<String>) : ModelWrapper<Nothing>()

    data class NetworkError(val error: IOException) : ModelWrapper<Nothing>()

    data class UnknownError(val error: Throwable) : ModelWrapper<Nothing>()

    data class Success<T : Any>(val model: T) : ModelWrapper<T>()
}
