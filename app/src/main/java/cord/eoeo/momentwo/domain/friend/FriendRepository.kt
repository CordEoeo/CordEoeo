package cord.eoeo.momentwo.domain.friend

import androidx.paging.PagingData
import cord.eoeo.momentwo.ui.model.FriendItem
import cord.eoeo.momentwo.ui.model.FriendRequestItem
import cord.eoeo.momentwo.ui.model.UserItem
import kotlinx.coroutines.flow.Flow

interface FriendRepository {
    suspend fun sendFriendRequest(nickname: String): Result<Unit>

    suspend fun responseFriendRequest(
        nickname: String,
        isAccepted: Boolean,
    ): Result<Unit>

    suspend fun cancelFriendRequest(nickname: String): Result<Unit>

    suspend fun getFriendPage(size: Int): Flow<PagingData<FriendItem>>

    suspend fun getFriendList(): Result<List<FriendItem>>

    suspend fun getSentRequests(): Result<List<FriendRequestItem>>

    suspend fun getReceivedRequests(): Result<List<FriendRequestItem>>

    suspend fun getSearchUsers(nickname: String, size: Int): Flow<PagingData<UserItem>>
}
