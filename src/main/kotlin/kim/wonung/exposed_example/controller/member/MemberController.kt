package kim.wonung.exposed_example.controller.member

import kim.wonung.exposed_example.application.member.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v1/members")
@RestController
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping("/sign-up-with-email")
    fun signUpWithEmail(@RequestBody request: SignUpWithEmailRequest): SignInResponse {
        memberService.signUpWithEmail(request.toDto())
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }

    @PostMapping("/sign-up-with-social")
    fun signUpWithSocial(@RequestBody request: SignUpWithSocialRequest): SignInResponse {
        memberService.signUpWithSocial(request.toDto())
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }

    @PostMapping("/sign-in-with-email")
    fun signInWithEmail(@RequestBody request: SignInWithEmailRequest): SignInResponse {
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }

    @PostMapping("/sign-in-with-social")
    fun signInWithSocial(@RequestBody request: SignInWithSocialRequest): SignInResponse {
        TODO("인증 토큰을 만드는 로직을 추가해야한다.")
    }
}
