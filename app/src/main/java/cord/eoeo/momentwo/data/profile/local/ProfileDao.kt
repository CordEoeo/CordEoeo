package cord.eoeo.momentwo.data.profile.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cord.eoeo.momentwo.data.profile.local.entity.ProfileEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile LIMIT 1")
    suspend fun getProfile(): ProfileEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(profile: ProfileEntity)
}
