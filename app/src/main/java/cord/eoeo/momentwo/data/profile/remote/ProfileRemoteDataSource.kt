package cord.eoeo.momentwo.data.profile.remote

import cord.eoeo.momentwo.data.model.UserProfile
import cord.eoeo.momentwo.data.profile.ProfileDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRemoteDataSource(
    private val profileService: ProfileService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ProfileDataSource.Remote {
    override suspend fun getProfile(nickname: String): Result<UserProfile> =
        runCatching {
            withContext(dispatcher) {
                profileService.getProfile(nickname)
            }
        }
}
