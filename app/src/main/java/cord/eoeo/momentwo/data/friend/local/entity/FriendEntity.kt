package cord.eoeo.momentwo.data.friend.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cord.eoeo.momentwo.ui.model.FriendItem

@Entity(tableName = "friend")
data class FriendEntity(
    @PrimaryKey val nickname: String,
    @ColumnInfo(name = "user_profile_image") val userProfileImage: String,
) {
    fun mapToFriendItem(): FriendItem =
        FriendItem(
            nickname = nickname,
            userProfileImage = userProfileImage,
        )
}
