package cord.eoeo.momentwo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import cord.eoeo.momentwo.data.friend.local.FriendDao
import cord.eoeo.momentwo.data.friend.local.FriendRemoteKeyDao
import cord.eoeo.momentwo.data.friend.local.entity.FriendEntity
import cord.eoeo.momentwo.data.friend.local.entity.FriendRemoteKeyEntity
import cord.eoeo.momentwo.data.profile.local.ProfileDao
import cord.eoeo.momentwo.data.profile.local.entity.ProfileEntity
import cord.eoeo.momentwo.data.photo.local.PhotoDao
import cord.eoeo.momentwo.data.photo.local.PhotoRemoteKeyDao
import cord.eoeo.momentwo.data.photo.local.entity.PhotoEntity
import cord.eoeo.momentwo.data.photo.local.entity.PhotoRemoteKeyEntity

@Database(
    entities = [
        PhotoEntity::class,
        PhotoRemoteKeyEntity::class,
        FriendEntity::class,
        FriendRemoteKeyEntity::class,
        ProfileEntity::class,
    ],
    version = 1,
)
abstract class MomentwoDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao

    abstract fun photoRemoteKeyDao(): PhotoRemoteKeyDao

    abstract fun friendDao(): FriendDao

    abstract fun friendRemoteKeyDao(): FriendRemoteKeyDao

    abstract fun profileDao(): ProfileDao
}
