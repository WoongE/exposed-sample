package kim.wonung.exposed_example.persistence

import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.timestampWithTimeZone

object SamplesTable : UUIDTable(name = "member.samples") {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val createdAt = timestampWithTimeZone("created_at")
    val updatedAt = timestampWithTimeZone("updated_at")
    val deletedAt = timestampWithTimeZone("deleted_at").nullable()
}
