package cord.eoeo.momentwo.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UploadPhoto(
    val albumId: Int,
    val subAlbumId: Int,
    val filename: String,
)

@JsonClass(generateAdapter = true)
data class DeletePhotos(
    val albumId: Int,
    val subAlbumId: Int,
    val imagesId: List<Int>,
    val imagesUrl: List<String>,
)

@JsonClass(generateAdapter = true)
data class PhotoPage(
    val images: List<PhotoInfo>,
    val nextCursor: Int?,
)

@JsonClass(generateAdapter = true)
data class PhotoInfo(
    val id: Int,
    val imageUrl: String
)
