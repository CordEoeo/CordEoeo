package cord.eoeo.momentwo.domain.photo

import android.net.Uri
import androidx.paging.PagingData
import cord.eoeo.momentwo.ui.model.PhotoItem
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotoPagingData(
        pageSize: Int,
        albumId: Int,
        subAlbumId: Int,
    ): Flow<PagingData<PhotoItem>>

    suspend fun requestUploadPhoto(
        albumId: Int,
        subAlbumId: Int,
        image: Uri,
    ): Result<Unit>

    suspend fun deletePhotos(
        albumId: Int,
        subAlbumId: Int,
        photoIds: List<Int>,
    ): Result<Unit>

    suspend fun updateIsLiked(
        photoId: Int,
        isLiked: Boolean,
    )

    suspend fun downloadPhoto(imageUrl: String): Result<Unit>
}
