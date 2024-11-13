package cord.eoeo.momentwo.data.comment

import cord.eoeo.momentwo.data.model.CommentPage
import cord.eoeo.momentwo.data.model.CreateComment
import cord.eoeo.momentwo.data.model.DeleteComment
import cord.eoeo.momentwo.data.model.EditComment

interface CommentDataSource {
    suspend fun createComment(createComment: CreateComment): Result<Unit>

    suspend fun editComment(editComment: EditComment): Result<Unit>

    suspend fun deleteComment(deleteComment: DeleteComment): Result<Unit>

    suspend fun getCommentPage(albumId: Int, photoId: Int, size: Int, cursor: Int): Result<CommentPage>
}
