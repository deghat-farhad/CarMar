package deghat.farhad.carlist.presentation.page.placemark_map.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deghat.farhad.carlist.R
import deghat.farhad.carlist.domain.usecase.GetPlacemarks
import deghat.farhad.carlist.presentation.item.MapItmPlacemark
import deghat.farhad.carlist.presentation.mapper.PlacemarkItemMapper
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import deghat.farhad.common.presentation.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViwMdlPlacemarkMap @Inject constructor(
    private val getPlacemarks: GetPlacemarks,
    private val placemarkItemMapper: PlacemarkItemMapper
) : ViewModel() {

    private val mState = MutableLiveData<UiState<List<MapItmPlacemark>>>()
    val state: LiveData<UiState<List<MapItmPlacemark>>> = mState

    val isPermissionGranted by lazy { MutableLiveData<Boolean>() }
    fun setPermissionGranted(value: Boolean) {
        isPermissionGranted.postValue(value)
    }

    fun getPlacemarks() {
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
                                UiState.HasData(placemarkItemMapper.mapToMapItmPlacemark(it.model))
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
}