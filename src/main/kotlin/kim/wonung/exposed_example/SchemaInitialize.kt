package kim.wonung.exposed_example

import kim.wonung.exposed_example.persistence.SamplesTable
import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Transactional
@Component
class SchemaInitialize : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        SchemaUtils.create(SamplesTable) // create table 헐 테이블이 없으면 만들어주네?
    }
}
