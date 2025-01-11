package cord.eoeo.momentwo.data.description

import cord.eoeo.momentwo.data.model.CreateDescription
import cord.eoeo.momentwo.data.model.Description
import cord.eoeo.momentwo.data.model.EditDescription

interface DescriptionDataSource {
    suspend fun createDescription(createDescription: CreateDescription): Result<Unit>

    suspend fun editDescription(editDescription: EditDescription): Result<Unit>

    suspend fun deleteDescription(
        albumId: Int,
        photoId: Int,
    ): Result<Unit>

    suspend fun getDescription(
        albumId: Int,
        photoId: Int,
    ): Result<Description>
}
