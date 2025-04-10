package kim.wonung.exposed_example.domain.member

import java.util.*

/**
 * UK: email
 */
data class Member(
    val id: UUID,
    val role: MemberRole,
    /**
     * 모든 로그인 방식에서 공통으로 사용될 수 있는 이메일 주소
     */
    val email: String?,
    val passwordHash: String?,
    val passwordSalt: String?,
    val socialLogins: List<SocialLogin>,
) {
    data class SocialLogin(
        val socialProvider: SocialProvider,
        val socialId: String,
    )

    fun checkPassword(password: String): Boolean {
        require(password.isNotBlank()) { "Password must not be blank." }
        require(passwordHash != null && passwordSalt != null) { "패스워드를 등록한 적이 없습니다." }
        TODO()
    }

    fun addSocialLogin(
        socialProvider: SocialProvider,
        socialId: String,
        memberRepository: MemberRepository,
    ) {
        require(socialId.isNotBlank()) { "Provider user id must not be blank." }
        require(socialLogins.none { it.socialProvider == socialProvider }) {
            "Already registered social login. socialProvider: $socialProvider, memberId: $id"
        }
        memberRepository.addSocialLoginById(
            id = id,
            socialLogin = SocialLogin(
                socialProvider = socialProvider,
                socialId = socialId,
            ),
        )
    }

    companion object {
        fun createByEmail(
            role: MemberRole,
            email: String,
            passwordHash: String,
            passwordSalt: String,
        ): Member {
            require(email.isNotBlank()) { "Email must not be blank" }
            return Member(
                id = UUID.randomUUID(),
                role = role,
                email = email,
                passwordHash = passwordHash,
                passwordSalt = passwordSalt,
                socialLogins = emptyList(),
            )
        }

        fun createByOAuth2(
            role: MemberRole,
            socialProvider: SocialProvider,
            socialId: String,
        ) = Member(
            id = UUID.randomUUID(),
            role = role,
            email = null,
            passwordHash = null,
            passwordSalt = null,
            socialLogins = listOf(
                SocialLogin(
                    socialProvider = socialProvider,
                    socialId = socialId,
                )
            ),
        )
    }
}
