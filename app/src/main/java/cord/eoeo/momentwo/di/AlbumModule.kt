package cord.eoeo.momentwo.di

import android.content.Context
import cord.eoeo.momentwo.data.album.AlbumDataSource
import cord.eoeo.momentwo.data.album.AlbumRepositoryImpl
import cord.eoeo.momentwo.data.album.remote.AlbumRemoteDataSource
import cord.eoeo.momentwo.data.album.remote.AlbumService
import cord.eoeo.momentwo.data.presigned.PresignedDataSource
import cord.eoeo.momentwo.domain.album.AlbumRepository
import cord.eoeo.momentwo.domain.album.GetAlbumListUseCase
import cord.eoeo.momentwo.domain.album.RequestCreateAlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {
    @Provides
    @Singleton
    fun provideAlbumService(retrofit: Retrofit): AlbumService = retrofit.create(AlbumService::class.java)

    @Provides
    @Singleton
    fun provideAlbumRemoteDataSource(albumService: AlbumService): AlbumDataSource = AlbumRemoteDataSource(albumService)

    @Provides
    @Singleton
    fun provideAlbumRepository(
        albumRemoteDataSource: AlbumDataSource,
        presignedRemoteDataSource: PresignedDataSource,
        @ApplicationContext applicationContext: Context,
    ): AlbumRepository = AlbumRepositoryImpl(albumRemoteDataSource, presignedRemoteDataSource, applicationContext)

    @Provides
    @Singleton
    fun provideRequestCreateAlbumUseCase(albumRepository: AlbumRepository): RequestCreateAlbumUseCase =
        RequestCreateAlbumUseCase(albumRepository)

    @Provides
    @Singleton
    fun provideGetAlbumListUseCase(albumRepository: AlbumRepository): GetAlbumListUseCase =
        GetAlbumListUseCase(albumRepository)
}
