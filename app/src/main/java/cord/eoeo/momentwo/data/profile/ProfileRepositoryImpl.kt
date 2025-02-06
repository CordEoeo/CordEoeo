package cord.eoeo.momentwo.data.profile

import cord.eoeo.momentwo.domain.mapper.ProfileMapper
import cord.eoeo.momentwo.domain.model.Profile
import cord.eoeo.momentwo.domain.profile.ProfileRepository

class ProfileRepositoryImpl(
    private val profileLocalDataSource: ProfileDataSource.Local,
    private val profileRemoteDataSource: ProfileDataSource.Remote,
    private val profileMapper: ProfileMapper,
) : ProfileRepository {
    override suspend fun storeProfile(profile: Profile): Result<Unit> =
        profileLocalDataSource.storeProfile(profileMapper.domainToEntity(profile))

    override suspend fun getProfile(nickname: String?): Result<Profile> =
        if (nickname.isNullOrEmpty()) {
            profileLocalDataSource.getProfile().map { profileEntity ->
                profileMapper.entityToDomain(profileEntity)
            }
        } else {
            profileRemoteDataSource.getProfile(nickname).map { userProfile ->
                profileMapper.dataToDomain(userProfile)
            }
        }
}
