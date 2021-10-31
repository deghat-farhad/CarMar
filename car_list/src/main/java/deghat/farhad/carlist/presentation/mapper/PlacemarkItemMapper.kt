package deghat.farhad.carlist.presentation.mapper

import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.carlist.presentation.item.CoordinateItm
import deghat.farhad.carlist.presentation.item.MapItmPlacemark
import deghat.farhad.carlist.presentation.item.RecItmPlacemark
import javax.inject.Inject

class PlacemarkItemMapper @Inject constructor() {
    fun mapToRecItmPlacemark(from: List<Placemark>) = from.map { mapPlacemarkItem(it) }
    private fun mapPlacemarkItem(from: Placemark) = RecItmPlacemark.Placemark(
        from.address,
        CoordinateItm(from.coordinates.latitude, from.coordinates.longitude),
        from.engineType,
        from.exterior,
        from.fuel,
        from.interior,
        from.name,
        from.vin
    )

    fun mapToMapItmPlacemark(from: List<Placemark>) = from.map {
        MapItmPlacemark(
            CoordinateItm(it.coordinates.latitude, it.coordinates.longitude),
            it.name
        )
    }
}