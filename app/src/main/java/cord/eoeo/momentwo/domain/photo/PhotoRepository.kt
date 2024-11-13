package cord.eoeo.momentwo.domain.photo

import android.net.Uri
import androidx.paging.PagingData
import cord.eoeo.momentwo.ui.model.ImageItem
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotoPagingData(pageSize: Int, albumId: Int, subAlbumId: Int): Flow<PagingData<ImageItem>>

    suspend fun requestUploadPhoto(albumId: Int, subAlbumId: Int, image: Uri): Result<Unit>

    suspend fun deletePhotos(albumId: Int, subAlbumId: Int, imageIds: List<Int>, imageUrls: List<String>): Result<Unit>

    suspend fun downloadPhoto(imageUrl: String): Result<Unit>
}
