package cord.eoeo.momentwo.domain.login

import cord.eoeo.momentwo.domain.model.LoginData

interface LoginRepository {
    suspend fun requestLogin(
        email: String,
        password: String,
    ): Result<LoginData>
}
