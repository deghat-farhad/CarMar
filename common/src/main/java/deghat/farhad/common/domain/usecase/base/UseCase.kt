package deghat.farhad.common.domain.usecase.base

abstract class UseCase<T>() {
    abstract suspend fun buildUseCase(): ModelWrapper<T>

    suspend fun execute(onResult: (ModelWrapper<T>) -> Unit = {}) {
        onResult(buildUseCase())
    }
}