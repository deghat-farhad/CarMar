package deghat.farhad.carlist.data.remote.entitiy

data class PlacemarkListEntity(
    val placemarks: List<PlacemarkEntity>
)

data class PlacemarkEntity(
    val address: String,
    val coordinates: List<Double>,
    val engineType: String,
    val exterior: String,
    val fuel: Int,
    val interior: String,
    val name: String,
    val vin: String
)
