package deghat.farhad.album.presentation.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import deghat.farhad.carlist.data.repository.PlacemarkRepositoryImpl
import deghat.farhad.carlist.domain.repository.PlacemarkRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun placemarkRepositoryImpl(placemarkRepositoryImpl: PlacemarkRepositoryImpl): PlacemarkRepository
}