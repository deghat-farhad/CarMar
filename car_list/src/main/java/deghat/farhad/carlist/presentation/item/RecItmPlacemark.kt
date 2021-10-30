package deghat.farhad.carlist.presentation.item

import deghat.farhad.carlist.R
import deghat.farhad.common.presentation.util.recycler_view.Visitable

sealed class RecItmPlacemark : Visitable {
    data class Placemark(
        val address: String,
        val coordinates: CoordinatesItm,
        val engineType: String,
        val exterior: String,
        val fuel: Int,
        val interior: String,
        val name: String,
        val vin: String
    ) : RecItmPlacemark() {
        override fun type(): Int {
            return R.layout.itm_placemark
        }
    }
}

data class CoordinatesItm(
    val latitude: Double,
    val longitude: Double
)
