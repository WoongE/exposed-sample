package kim.wonung.exposed_example.domain.member

import java.util.*

interface MemberRepository {
    fun findByRoleAndEmail(role: MemberRole, email: String): Member?
    fun findByRoleAndSocialProviderAndSocialId(
        role: MemberRole,
        socialProvider: SocialProvider,
        socialId: String,
    ): Member?

    fun addSocialLoginById(id: UUID, socialLogin: Member.SocialLogin)
    fun insert(member: Member): Member
}
