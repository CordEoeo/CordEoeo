package cord.eoeo.momentwo.domain.photo

class DownloadPhotoUseCase(private val photoRepository: PhotoRepository) {
    suspend operator fun invoke(imageUrl: String): Result<Unit> =
        photoRepository.downloadPhoto(imageUrl)
}
