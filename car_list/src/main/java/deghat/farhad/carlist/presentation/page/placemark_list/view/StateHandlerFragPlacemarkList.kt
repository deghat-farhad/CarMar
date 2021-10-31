package deghat.farhad.carlist.presentation.page.placemark_list.view

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import deghat.farhad.carlist.R
import deghat.farhad.common.presentation.UiState
import javax.inject.Inject

class StateHandlerFragPlacemarkList @Inject constructor() {
    val statusMessage by lazy { MutableLiveData<Int>() }
    val showStatusMessage by lazy { MutableLiveData<Boolean>() }
    val showActionButton by lazy { MutableLiveData<Boolean>() }
    val isRefreshing by lazy { MutableLiveData<Boolean>() }
    val actionButtonText by lazy { MutableLiveData<Int>() }
    val actionButtonOnClick by lazy { MutableLiveData<() -> Unit>() }
    val showData by lazy { MutableLiveData<Boolean>() }

    fun setUiState(state: UiState<*>) {
        when (state) {
            is UiState.Error -> error(state.messageId, R.string.retry, state.onRetry)
            is UiState.HasData<*> -> data()
            is UiState.Loading -> loading()
            is UiState.NoData -> error(R.string.no_data, R.string.refresh, state.onRefresh)
        }
    }

    private fun loading() {
        showData.postValue(false)
        isRefreshing.postValue(true)
        showStatusMessage.postValue(true)
        showActionButton.postValue(false)
        statusMessage.postValue(R.string.loading)
    }

    private fun error(
        @StringRes messageId: Int,
        @StringRes actionButtonText: Int,
        actionButtonOnClick: (() -> Unit)?
    ) {
        showData.postValue(false)
        isRefreshing.postValue(false)
        statusMessage.postValue(messageId)
        showStatusMessage.postValue(true)
        if (actionButtonOnClick != null) {
            showActionButton.postValue(true)
            this.actionButtonText.postValue(actionButtonText)
            this.actionButtonOnClick.postValue(actionButtonOnClick)
        } else {
            showActionButton.postValue(false)
        }
    }

    private fun data() {
        isRefreshing.postValue(false)
        showStatusMessage.postValue(false)
        showActionButton.postValue(false)
        showData.postValue(true)
    }
}