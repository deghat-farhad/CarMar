package deghat.farhad.carlist.domain.usecase

import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.carlist.domain.repository.PlacemarkRepository
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import deghat.farhad.common.domain.usecase.base.UseCase

class GetPlacemarks(
    private val placemarkRepository: PlacemarkRepository
): UseCase<List<Placemark>>() {
    override suspend fun buildUseCase(): ModelWrapper<List<Placemark>> {
        return placemarkRepository.getPlacemarks()
    }
}