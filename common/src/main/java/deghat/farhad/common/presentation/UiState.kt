package deghat.farhad.common.presentation

import androidx.annotation.StringRes

sealed class UiState<T>{
    class Loading<T>: UiState<T>()
    class Error<T>(@StringRes val messageId: Int, val onRetry: (() -> Unit)? = null): UiState<T>()
    class HasData<T>(val content: T): UiState<T>()
    class NoData<T>(val onRefresh: (() -> Unit)? = null): UiState<T>()
}