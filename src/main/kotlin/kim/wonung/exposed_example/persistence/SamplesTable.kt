package kim.wonung.exposed_example.persistence

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.javatime.CurrentTimestampWithTimeZone
import org.jetbrains.exposed.sql.javatime.timestampWithTimeZone
import java.time.OffsetDateTime
import java.util.*

object SamplesTable : UUIDTable(name = "member.samples") {
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val createdAt = timestampWithTimeZone("created_at").defaultExpression(CurrentTimestampWithTimeZone)
    val updatedAt = timestampWithTimeZone("updated_at").defaultExpression(CurrentTimestampWithTimeZone)
    val deletedAt = timestampWithTimeZone("deleted_at").nullable()
}

class SampleEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<SampleEntity>(SamplesTable)

    var name: String by SamplesTable.name
    var description: String by SamplesTable.description
    val createdAt: OffsetDateTime by SamplesTable.createdAt
    var updatedAt: OffsetDateTime by SamplesTable.updatedAt
    var deletedAt: OffsetDateTime? by SamplesTable.deletedAt
}
