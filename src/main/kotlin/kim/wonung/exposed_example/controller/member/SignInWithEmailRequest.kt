package kim.wonung.exposed_example.controller.member

import kim.wonung.exposed_example.domain.member.MemberRole


data class SignInWithEmailRequest(
    val role: MemberRole,
    val email: String,
    val password: String,
)
