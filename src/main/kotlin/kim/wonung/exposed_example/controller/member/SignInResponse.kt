package kim.wonung.exposed_example.controller.member

import java.time.LocalDateTime

data class SignInResponse(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: LocalDateTime,
)
