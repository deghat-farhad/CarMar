package deghat.farhad.carlist.data.remote.service

import com.haroldadmin.cnradapter.NetworkResponse
import deghat.farhad.carlist.data.remote.entitiy.PlacemarkEntity
import deghat.farhad.carlist.data.remote.entitiy.PlacemarkListEntity
import deghat.farhad.common.data.entity.ErrorResponse
import retrofit2.http.GET

interface PlacemarkService {
    @GET("/locations.json")
    suspend fun getPlacemarks(): NetworkResponse<PlacemarkListEntity, ErrorResponse>
}