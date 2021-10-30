package deghat.farhad.carlist.presentation.mapper

import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.carlist.presentation.item.CoordinatesItm
import deghat.farhad.carlist.presentation.item.RecItmPlacemark
import javax.inject.Inject

class PlacemarkItemMapper @Inject constructor() {
    fun mapToPresentation(from: List<Placemark>) = from.map { mapPlacemarkItem(it) }
    private fun mapPlacemarkItem(from: Placemark) = RecItmPlacemark.Placemark(
        from.address,
        CoordinatesItm(from.coordinates.latitude, from.coordinates.longitude),
        from.engineType,
        from.exterior,
        from.fuel,
        from.interior,
        from.name,
        from.vin
    )
}