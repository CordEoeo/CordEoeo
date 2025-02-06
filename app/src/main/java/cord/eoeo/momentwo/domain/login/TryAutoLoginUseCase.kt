package cord.eoeo.momentwo.domain.login

import cord.eoeo.momentwo.data.authentication.PreferenceRepository

class TryAutoLoginUseCase(
    private val preferenceRepository: PreferenceRepository,
) {
    suspend operator fun invoke(): Result<Unit> =
        runCatching {
            val accessToken = preferenceRepository.getAccessToken().getOrThrow()

            if (accessToken.isEmpty()) throw Exception("AutoLogin: No login history")

            Result.success(Unit)
        }
}
