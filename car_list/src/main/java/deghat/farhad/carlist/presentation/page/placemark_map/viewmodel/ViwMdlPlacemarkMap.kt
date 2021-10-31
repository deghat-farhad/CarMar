package deghat.farhad.carlist.presentation.page.placemark_map.viewmodel

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
class ViwMdlPlacemarkMap @Inject constructor(
    private val getPlacemarks: GetPlacemarks,
    private val placemarkItemMapper: PlacemarkItemMapper
) : ViewModel() {

    val list = MutableLiveData<List<RecItmPlacemark>>()

    val isPermissionGranted by lazy { MutableLiveData<Boolean>() }
    fun setPermissionGranted(value: Boolean) {
        isPermissionGranted.postValue(value)
    }

    fun getPlacemarks() {
        viewModelScope.launch {
            getPlacemarks.execute {
                when (it) {
                    is ModelWrapper.Success -> list.postValue(
                        placemarkItemMapper.mapToPresentation(
                            it.model
                        )
                    )
                }
            }
        }
    }
}