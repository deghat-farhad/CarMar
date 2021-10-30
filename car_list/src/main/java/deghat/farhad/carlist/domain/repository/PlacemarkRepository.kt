package deghat.farhad.carlist.domain.repository

import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.common.domain.usecase.base.ModelWrapper

interface PlacemarkRepository {
    suspend fun getPlacemarks(): ModelWrapper<List<Placemark>>
}