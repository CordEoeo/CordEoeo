package cord.eoeo.momentwo.data.profile

import cord.eoeo.momentwo.data.model.UserProfile
import cord.eoeo.momentwo.data.profile.local.entity.ProfileEntity

interface ProfileDataSource {
    interface Local {
        suspend fun storeProfile(profile: ProfileEntity): Result<Unit>

        suspend fun getProfile(): Result<ProfileEntity>
    }

    interface Remote {
        suspend fun getProfile(nickname: String): Result<UserProfile>
    }
}
