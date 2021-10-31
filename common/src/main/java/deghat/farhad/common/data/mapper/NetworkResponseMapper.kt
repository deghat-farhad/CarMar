package deghat.farhad.common.data.mapper

import com.haroldadmin.cnradapter.NetworkResponse
import deghat.farhad.common.data.entity.ErrorResponse
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import javax.inject.Inject

class NetworkResponseMapper<From : Any, To : Any> @Inject constructor() {
    fun mapToDomain(
        networkResponse: NetworkResponse<From, ErrorResponse>,
        mapperToDomain: MapperToDomain<From, To>
    ): ModelWrapper<To> {
        return when (networkResponse) {
            is NetworkResponse.Success<From> -> ModelWrapper.Success(
                mapperToDomain.mapToDomain(
                    networkResponse.body
                )
            )
            is NetworkResponse.ServerError<ErrorResponse> -> ModelWrapper.ServerError(
                networkResponse.body?.message ?: "Unknown Error"
            )
            is NetworkResponse.NetworkError -> ModelWrapper.NetworkError(networkResponse.error)
            is NetworkResponse.UnknownError -> ModelWrapper.UnknownError(networkResponse.error)
        }
    }
}