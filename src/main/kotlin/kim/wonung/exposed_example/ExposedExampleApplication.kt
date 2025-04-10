package kim.wonung.exposed_example

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ExposedExampleApplication {
    @PostConstruct
    fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ExposedExampleApplication>(*args)
        }
    }
}
