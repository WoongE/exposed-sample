package kim.wonung.exposed_example.controller.member

import kim.wonung.exposed_example.application.member.SignUpWithEmailDto
import kim.wonung.exposed_example.domain.member.MemberRole

data class SignUpWithEmailRequest(
    val role: MemberRole,
    val email: String,
    val password: String,
) {
    fun toDto() = SignUpWithEmailDto(
        role = role,
        email = email,
        password = password,
    )
}
