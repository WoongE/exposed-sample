package kim.wonung.exposed_example.persistence.member

import kim.wonung.exposed_example.domain.member.Member
import kim.wonung.exposed_example.domain.member.MemberRepository
import kim.wonung.exposed_example.domain.member.MemberRole
import kim.wonung.exposed_example.domain.member.SocialProvider
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class MemberRepositoryImpl() : MemberRepository {
    override fun findByRoleAndEmail(role: MemberRole, email: String): Member? {
        TODO()
    }

    override fun findByRoleAndSocialProviderAndSocialId(
        role: MemberRole,
        socialProvider: SocialProvider,
        socialId: String
    ): Member? {
        TODO()
    }

    override fun addSocialLoginById(id: UUID, socialLogin: Member.SocialLogin) {
        TODO()
    }

    override fun insert(member: Member): Member {
        TODO()
    }
}
