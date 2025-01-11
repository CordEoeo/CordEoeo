package cord.eoeo.momentwo.data.photo.remote

import cord.eoeo.momentwo.data.model.LikedPhotoList
import cord.eoeo.momentwo.data.model.PhotoPage
import cord.eoeo.momentwo.data.model.PresignedRequest
import cord.eoeo.momentwo.data.model.PresignedUrl
import cord.eoeo.momentwo.data.model.UploadPhoto
import cord.eoeo.momentwo.data.photo.PhotoDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotoRemoteDataSource(
    private val photoService: PhotoService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PhotoDataSource.Remote {
    override suspend fun getPhotoPage(
        albumId: Int,
        subAlbumId: Int,
        pageSize: Int,
        cursor: Int,
    ): Result<PhotoPage> =
        runCatching {
            withContext(dispatcher) {
                photoService.getPhotoPage(albumId, subAlbumId, pageSize, cursor)
            }
        }

    override suspend fun requestPresignedUrl(presignedRequest: PresignedRequest): Result<PresignedUrl> =
        runCatching {
            withContext(dispatcher) {
                photoService.postPhotoPresigned(presignedRequest)
            }
        }

    override suspend fun requestUploadPhoto(uploadPhoto: UploadPhoto): Result<Unit> =
        runCatching {
            withContext(dispatcher) {
                photoService.postPhotoUpload(uploadPhoto)
            }
        }

    override suspend fun deletePhotos(
        albumId: Int,
        subAlbumId: Int,
        imageIds: String,
    ): Result<Unit> =
        runCatching {
            withContext(dispatcher) {
                photoService.deletePhotos(albumId, subAlbumId, imageIds)
            }
        }

    override suspend fun getLikedPhoto(
        subAlbumId: Int,
        minPid: Int,
        maxPid: Int,
    ): Result<LikedPhotoList> =
        runCatching {
            withContext(dispatcher) {
                photoService.getLikedPhotos(subAlbumId, minPid, maxPid)
            }
        }
}
