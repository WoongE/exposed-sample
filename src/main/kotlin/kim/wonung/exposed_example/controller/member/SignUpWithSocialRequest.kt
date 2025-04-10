package kim.wonung.exposed_example.controller.member

import kim.wonung.exposed_example.application.member.SignUpWithSocialDto
import kim.wonung.exposed_example.domain.member.MemberRole
import kim.wonung.exposed_example.domain.member.SocialProvider

data class SignUpWithSocialRequest(
    val role: MemberRole,
    val socialProvider: SocialProvider,
    val socialId: String,
) {
    fun toDto() = SignUpWithSocialDto(
        role = role,
        socialProvider = socialProvider,
        socialId = socialId,
    )
}
