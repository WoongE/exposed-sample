package kim.wonung.exposed_example.application.member

import kim.wonung.exposed_example.domain.member.MemberRole
import kim.wonung.exposed_example.domain.member.SocialProvider

data class SignInWithSocialDto(
    val role: MemberRole,
    val socialProvider: SocialProvider,
    val socialId: String,
)
