package deghat.farhad.carlist.domain.model

data class Placemark(
    val address: String,
    val coordinates: Coordinates,
    val engineType: String,
    val exterior: String,
    val fuel: Int,
    val interior: String,
    val name: String,
    val vin: String
)

data class Coordinates(
    val latitude: Double,
    val longitude: Double
)