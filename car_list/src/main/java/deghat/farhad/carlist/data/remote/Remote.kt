package deghat.farhad.carlist.data.remote

import deghat.farhad.carlist.data.remote.entitiy.PlacemarkListEntity
import deghat.farhad.carlist.data.remote.mapper.PlacemarkListEntityMapper
import deghat.farhad.carlist.domain.model.Placemark
import deghat.farhad.common.data.mapper.NetworkResponseMapper
import deghat.farhad.common.domain.usecase.base.ModelWrapper
import javax.inject.Inject

class Remote @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val placemarkListEntityMapper: PlacemarkListEntityMapper,
    private val placemarkNetworkResponseMapper: NetworkResponseMapper<PlacemarkListEntity, List<Placemark>>
){
    suspend fun getPlacemarks(): ModelWrapper<List<Placemark>>{
        return placemarkNetworkResponseMapper.mapToDomain(
            serviceGenerator.placemarkService().getPlacemarks(),
            placemarkListEntityMapper
        )
    }
}