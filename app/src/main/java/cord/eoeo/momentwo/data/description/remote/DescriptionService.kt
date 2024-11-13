package cord.eoeo.momentwo.data.description.remote

import cord.eoeo.momentwo.data.MomentwoApi
import cord.eoeo.momentwo.data.model.CreateDescription
import cord.eoeo.momentwo.data.model.DeleteDescription
import cord.eoeo.momentwo.data.model.Description
import cord.eoeo.momentwo.data.model.EditDescription
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface DescriptionService {
    @Headers("content-type: application/json")
    @POST(MomentwoApi.POST_CREATE_DESCRIPTION)
    suspend fun postCreateDescription(
        @Body createDescription: CreateDescription
    )

    @Headers("content-type: application/json")
    @PUT(MomentwoApi.PUT_EDIT_DESCRIPTION)
    suspend fun putEditDescription(
        @Body editDescription: EditDescription
    )

    @Headers("content-type: application/json")
    @HTTP(method = "DELETE", path = MomentwoApi.DELETE_DESCRIPTION, hasBody = true)
    suspend fun deleteDescription(
        @Body deleteDescription: DeleteDescription
    )

    @GET(MomentwoApi.GET_DESCRIPTION)
    suspend fun getDescription(
        @Path("albumId") albumId: Int,
        @Path("photoId") photoId: Int,
    ): Description
}
