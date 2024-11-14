package cord.eoeo.momentwo.domain.photo

class UpdateIsLikedUseCase(private val photoRepository: PhotoRepository) {
    suspend operator fun invoke(photoId: Int, isLiked: Boolean) =
        photoRepository.updateIsLiked(photoId, isLiked)
}
