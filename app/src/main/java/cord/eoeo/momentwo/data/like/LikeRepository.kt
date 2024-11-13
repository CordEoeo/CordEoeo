package cord.eoeo.momentwo.data.like

interface LikeRepository {
    suspend fun requestDoLike(albumId: Int, photoId: Int): Result<Unit>

    suspend fun requestUndoLike(albumId: Int, photoId: Int): Result<Unit>

    suspend fun getLikeCount(albumId: Int, photoId: Int): Result<Int>
}
