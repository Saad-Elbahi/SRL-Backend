package ma.srmanager.coreapi.jwt

data class AppUserResponseDTO(

    var id: Long? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val fullName: String? = null,
    val username: String? = null,
    val email: String? = null,

    )

data class UserPointageDTO(
    var username: String = "",
    var password: String = "",
)

data class CreateUserDTO(
    var username: String = "",
    var password: String = "",
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = null,
    var avatar: String? = "unknown.png",
    var roles: List<String> = listOf<String>(),
    var token: String = "",

    )

data class UpdateInfosUserDTO(
    var username: String = "",
    var avatar: String? = "unknown.png",
    var firstName: String? = "",
    var lastName: String? = "",
    var email: String? = null,
    var roles: List<String> = listOf<String>(),
    var accountNonLocked: Boolean = false,
    var accountNonExpired: Boolean = false,
    var credentialsNonExpired: Boolean = false,

    )

data class UpdatePwdUserRequestDTO(
    var username: String = "",
    var oldPassword: String = "",
    var newPassword: String? = "",
)

data class UpdatePwdUserResponseDTO(
    var result: Boolean = false,
    var message: String? = "",
)

data class RoleUserForm(
    var username: String = "",
    var roleName: String = "",

    )

data class TurnOffOrOnUserDTO(
    var username: String = "",
    var accountNonLockedStatus: Boolean = false,
)
