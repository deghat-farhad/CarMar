package deghat.farhad.common.domain.usecase.base

abstract class UseCase<T, P>() {
    abstract suspend fun buildUseCase(params: P): ModelWrapper<T>

    suspend fun execute(params: P, onResult: (ModelWrapper<T>) -> Unit = {}) {
        onResult(buildUseCase(params))
    }
}