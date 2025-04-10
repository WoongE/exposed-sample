package kim.wonung.exposed_example.controller.member

import kim.wonung.exposed_example.domain.member.MemberRole
import kim.wonung.exposed_example.domain.member.SocialProvider

data class SignInWithSocialRequest(
    val role: MemberRole,
    val socialProvider: SocialProvider,
    val socialId: String,
)
