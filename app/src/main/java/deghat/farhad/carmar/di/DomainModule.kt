package deghat.farhad.album.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import deghat.farhad.carlist.domain.repository.PlacemarkRepository
import deghat.farhad.carlist.domain.usecase.GetPlacemarks

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    fun getPlacemarks(
        placemarkRepository: PlacemarkRepository
    ) = GetPlacemarks(
        placemarkRepository
    )
}