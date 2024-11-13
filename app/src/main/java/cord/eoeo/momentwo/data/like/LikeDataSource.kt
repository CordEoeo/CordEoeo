package cord.eoeo.momentwo.data.like

import cord.eoeo.momentwo.data.model.LikeCount
import cord.eoeo.momentwo.data.model.LikeRequest

interface LikeDataSource {
    suspend fun requestDoLike(likeRequest: LikeRequest): Result<Unit>

    suspend fun requestUndoLike(likeRequest: LikeRequest): Result<Unit>

    suspend fun getLikeCount(albumId: Int, photoId: Int): Result<LikeCount>
}
