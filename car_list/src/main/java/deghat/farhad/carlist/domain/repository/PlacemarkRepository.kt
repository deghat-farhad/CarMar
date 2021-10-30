package deghat.farhad.carlist.domain.repository

import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.common.domain.usecase.base.ModelWrapper

interface PlacemarkRepository {
    fun getPlacemarks(): ModelWrapper<List<Placemark>>
}