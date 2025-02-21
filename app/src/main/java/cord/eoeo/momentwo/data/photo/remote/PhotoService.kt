package cord.eoeo.momentwo.data.photo.remote

import cord.eoeo.momentwo.data.MomentwoApi
import cord.eoeo.momentwo.data.model.LikedPhotoList
import cord.eoeo.momentwo.data.model.PhotoPage
import cord.eoeo.momentwo.data.model.PresignedRequest
import cord.eoeo.momentwo.data.model.PresignedUrl
import cord.eoeo.momentwo.data.model.UploadPhoto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {
    @Headers("content-type: application/json")
    @POST(MomentwoApi.POST_PHOTO_UPLOAD)
    suspend fun postPhotoUpload(
        @Body uploadPhoto: UploadPhoto,
    )

    @DELETE(MomentwoApi.DELETE_PHOTOS)
    suspend fun deletePhotos(
        @Path("albumId") albumId: Int,
        @Path("subAlbumId") subAlbumId: Int,
        @Path("imagesId") imageIds: String,
    )

    @GET(MomentwoApi.GET_PHOTO_PAGE)
    suspend fun getPhotoPage(
        @Path("albumId") albumId: Int,
        @Path("subAlbumId") subAlbumId: Int,
        @Query("size") pageSize: Int,
        @Query("cursor") cursor: Int,
    ): PhotoPage

    @Headers("content-type: application/json")
    @POST(MomentwoApi.POST_PHOTO_PRESIGNED)
    suspend fun postPhotoPresigned(
        @Body presignedRequest: PresignedRequest,
    ): PresignedUrl

    @GET(MomentwoApi.GET_LIKED_PHOTOS)
    suspend fun getLikedPhotos(
        @Query("subAlbumId") subAlbumId: Int,
        @Query("minPid") minPid: Int,
        @Query("maxPid") maxPid: Int,
    ): LikedPhotoList
}
