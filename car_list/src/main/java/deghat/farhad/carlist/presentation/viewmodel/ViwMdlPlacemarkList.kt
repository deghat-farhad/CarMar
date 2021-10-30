package deghat.farhad.carlist.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import deghat.farhad.carlist.domain.usecase.GetPlacemarks
import deghat.farhad.carlist.presentation.item.RecItmPlacemark
import deghat.farhad.carlist.presentation.mapper.PlacemarkItemMapper
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViwMdlPlacemarkList @Inject constructor(
    private val getPlacemarks: GetPlacemarks,
    private val placemarkItemMapper: PlacemarkItemMapper
) : ViewModel() {
    //private val mPlacemarks =
    val placemarks: MutableLiveData<List<RecItmPlacemark>> =
        MutableLiveData<List<RecItmPlacemark>>()

    init {
        getPlacemarks()
    }

    private fun getPlacemarks() {
        viewModelScope.launch {
            getPlacemarks.execute {
                when (it) {
                    is ModelWrapper.NetworkError -> TODO()
                    is ModelWrapper.ServerError -> TODO()
                    is ModelWrapper.Success -> placemarks.postValue(
                        placemarkItemMapper.mapToPresentation(
                            it.model
                        )
                    )
                    is ModelWrapper.UnknownError -> TODO()
                }
            }
        }
    }
}