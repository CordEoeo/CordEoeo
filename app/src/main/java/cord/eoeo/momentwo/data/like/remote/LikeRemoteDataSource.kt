package cord.eoeo.momentwo.data.like.remote

import cord.eoeo.momentwo.data.like.LikeDataSource
import cord.eoeo.momentwo.data.model.LikeCount
import cord.eoeo.momentwo.data.model.LikeRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LikeRemoteDataSource(
    private val likeService: LikeService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : LikeDataSource {
    override suspend fun requestDoLike(likeRequest: LikeRequest): Result<Unit> =
        runCatching {
            withContext(dispatcher) {
                likeService.postDoLike(likeRequest)
            }
        }

    override suspend fun requestUndoLike(likeRequest: LikeRequest): Result<Unit> =
        runCatching {
            withContext(dispatcher) {
                likeService.postUndoLike(likeRequest)
            }
        }

    override suspend fun getLikeCount(albumId: Int, photoId: Int): Result<LikeCount> =
        runCatching {
            withContext(dispatcher) {
                likeService.getLikeCount(albumId, photoId)
            }
        }
}
