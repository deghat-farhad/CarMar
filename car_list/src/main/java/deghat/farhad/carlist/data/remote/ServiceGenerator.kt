package deghat.farhad.carlist.data.remote

import deghat.farhad.carlist.data.remote.service.PlacemarkService
import retrofit2.Retrofit
import javax.inject.Inject

class ServiceGenerator @Inject constructor(private val retrofit: Retrofit) {
    fun placemarkService() = retrofit.create(PlacemarkService::class.java)
}