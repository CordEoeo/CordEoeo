package cord.eoeo.momentwo.ui.model

data class MemberItem(
    val id: Int,
    val nickname: String,
    val profileImage: String,
    val auth: MemberAuth,
)
