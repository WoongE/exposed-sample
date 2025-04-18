package kim.wonung.exposed_example.application.member

import kim.wonung.exposed_example.domain.member.MemberRole

data class SignInWithEmailDto(
    val role: MemberRole,
    val email: String,
    val password: String,
)
