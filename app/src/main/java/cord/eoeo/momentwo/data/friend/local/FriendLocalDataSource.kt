package cord.eoeo.momentwo.data.friend.local

import androidx.paging.PagingSource
import cord.eoeo.momentwo.data.friend.FriendDataSource
import cord.eoeo.momentwo.data.friend.local.entity.FriendEntity
import cord.eoeo.momentwo.data.friend.local.entity.FriendRemoteKeyEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FriendLocalDataSource(
    private val friendDao: FriendDao,
    private val friendRemoteKeyDao: FriendRemoteKeyDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FriendDataSource.Local {
    override fun getPhotoPagingSource(): PagingSource<Int, FriendEntity> =
        friendDao.getFriendPagingSource()

    override suspend fun getFriendList(): Result<List<FriendEntity>> =
        runCatching {
            withContext(dispatcher) {
                friendDao.getFriendList()
            }
        }

    override suspend fun insertFriends(friends: List<FriendEntity>) {
        withContext(dispatcher) {
            friendDao.insertAll(*friends.toTypedArray())
        }
    }

    override suspend fun deleteByNicknames(friendNicknames: List<String>) {
        withContext(dispatcher) {
            friendDao.deleteByNicknames(friendNicknames)
        }
    }

    override suspend fun deleteAll() {
        withContext(dispatcher) {
            friendDao.deleteAll()
        }
    }

    override suspend fun getLastKey(): FriendRemoteKeyEntity? =
        withContext(dispatcher) {
            friendRemoteKeyDao.getLastKey()
        }

    override suspend fun insertKey(key: FriendRemoteKeyEntity) {
        withContext(dispatcher) {
            friendRemoteKeyDao.insertKey(key)
        }
    }

    override suspend fun clearKeys() {
        withContext(dispatcher) {
            friendRemoteKeyDao.clearKeys()
        }
    }
}
