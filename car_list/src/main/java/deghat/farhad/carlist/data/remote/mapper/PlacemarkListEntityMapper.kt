package deghat.farhad.carlist.data.remote.mapper

import deghat.farhad.carlist.data.remote.entitiy.PlacemarkEntity
import deghat.farhad.carlist.data.remote.entitiy.PlacemarkListEntity
import deghat.farhad.carlist.domain.model.Coordinates
import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.common.data.mapper.MapperToDomain
import javax.inject.Inject

class PlacemarkListEntityMapper @Inject constructor(): MapperToDomain<PlacemarkListEntity, List<Placemark>> {
    override fun mapToDomain(from: PlacemarkListEntity): List<Placemark> {
        return from.placemarks.map { mapPlacemarkEntityToPlacemark(it) }
    }

    private fun mapPlacemarkEntityToPlacemark(from: PlacemarkEntity): Placemark{
        return Placemark(
            from.address,
            Coordinates(from.coordinates[0], from.coordinates[1]),
            from.engineType,
            from.exterior,
            from.fuel,
            from.interior,
            from.name,
            from.vin
        )
    }
}