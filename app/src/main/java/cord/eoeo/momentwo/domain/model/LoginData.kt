package cord.eoeo.momentwo.domain.model

data class LoginData(
    val accessToken: String,
    val refreshToken: String,
    val profile: Profile,
)
