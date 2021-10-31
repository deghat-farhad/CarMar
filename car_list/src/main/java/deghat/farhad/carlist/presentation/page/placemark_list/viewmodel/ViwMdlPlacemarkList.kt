package deghat.farhad.carlist.presentation.page.placemark_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deghat.farhad.carlist.R
import deghat.farhad.carlist.domain.usecase.GetPlacemarks
import deghat.farhad.carlist.presentation.item.RecItmPlacemark
import deghat.farhad.carlist.presentation.mapper.PlacemarkItemMapper
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import deghat.farhad.common.presentation.UiState
import deghat.farhad.common.presentation.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViwMdlPlacemarkList @Inject constructor(
    private val getPlacemarks: GetPlacemarks,
    private val placemarkItemMapper: PlacemarkItemMapper
) : ViewModel() {
    private val mState = MutableLiveData<UiState<List<RecItmPlacemark>>>()
    val state: LiveData<UiState<List<RecItmPlacemark>>> = mState
    val navigateToPlacemarkMap by lazy { SingleLiveEvent<Unit>() }

    init {
        getPlacemarks()
    }

    private fun getPlacemarks() {
        viewModelScope.launch {
            mState.postValue(UiState.Loading())
            getPlacemarks.execute {
                when (it) {
                    is ModelWrapper.NetworkError -> mState.postValue(
                        UiState.Error(
                            R.string.network_error_message,
                            ::retry
                        )
                    )
                    is ModelWrapper.ServerError -> mState.postValue(
                        UiState.Error(
                            R.string.server_error_message,
                            ::retry
                        )
                    )
                    is ModelWrapper.Success -> {
                        mState.postValue(
                            if (it.model.isNotEmpty())
                                UiState.HasData(placemarkItemMapper.mapToPresentation(it.model))
                            else
                                UiState.NoData(::refresh)
                        )
                    }
                    is ModelWrapper.UnknownError -> mState.postValue(
                        UiState.Error(
                            R.string.unknown_error_message,
                            ::retry
                        )
                    )
                }
            }
        }
    }

    fun refresh() {
        getPlacemarks()
    }

    fun retry() {
        getPlacemarks()
    }

    fun navigateToPlacemarkMap() {
        navigateToPlacemarkMap.postValue(Unit)
    }
}