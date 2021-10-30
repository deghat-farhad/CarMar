package deghat.farhad.carlist.data.repository

import deghat.farhad.carlist.data.remote.Remote
import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.carlist.domain.repository.PlacemarkRepository
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import javax.inject.Inject

class PlacemarkRepositoryImpl @Inject constructor(
    private val remote: Remote
): PlacemarkRepository {
    override suspend fun getPlacemarks(): ModelWrapper<List<Placemark>> {
        return remote.getPlacemarks()
    }
}