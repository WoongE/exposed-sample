package kim.wonung.exposed_example.application.member

import kim.wonung.exposed_example.domain.member.Member
import kim.wonung.exposed_example.domain.member.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun signInWithEmail(dto: SignInWithEmailDto) {
        require(dto.email.isNotBlank()) { "Email must not be blank." }
        require(dto.password.isNotBlank()) { "Password must not be blank." }
        val member = requireNotNull(memberRepository.findByRoleAndEmail(role = dto.role, email = dto.email)) {
            "Member not found. email: ${dto.email}"
        }
        require(member.checkPassword(dto.password)) { "Password does not match. email: ${dto.email}" }
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }

    fun signInWithSocial(dto: SignInWithSocialDto) {
        val member = requireNotNull(
            memberRepository.findByRoleAndSocialProviderAndSocialId(
                role = dto.role,
                socialProvider = dto.socialProvider,
                socialId = dto.socialId,
            )
        ) { "Member not found. role: ${dto.role}, socialProvider: ${dto.socialProvider}, socialId: ${dto.socialId}" }
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }

    fun signUpWithEmail(dto: SignUpWithEmailDto) {
        require(dto.email.isNotBlank()) { "Email must not be blank." }
        require(dto.password.isNotBlank()) { "Password must not be blank." }
        memberRepository.insert(
            member = Member.createByEmail(
                role = dto.role,
                email = dto.email,
                passwordHash = TODO("패스워드 해시를 만드는 로직을 추가해야한다."),
                passwordSalt = TODO("패스워드 솔트를 만드는 로직을 추가해야한다."),
            ),
        )
    }

    fun signUpWithSocial(dto: SignUpWithSocialDto) {
        require(dto.socialId.isNotBlank()) { "oAuth2Id must not be blank." }
        memberRepository.insert(
            member = Member.createByOAuth2(
                role = dto.role,
                socialProvider = dto.socialProvider,
                socialId = dto.socialId,
            ),
        )
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }
}
