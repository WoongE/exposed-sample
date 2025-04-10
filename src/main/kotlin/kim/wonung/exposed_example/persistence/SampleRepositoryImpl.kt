package kim.wonung.exposed_example.persistence

import kim.wonung.exposed_example.domain.Sample
import kim.wonung.exposed_example.domain.SampleId
import kim.wonung.exposed_example.domain.SampleRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Transactional(readOnly = true)
@Repository
class SampleRepositoryImpl : SampleRepository {
    override fun findAll(): List<Sample> =
        SamplesTable.selectAll()
            .where { SamplesTable.deletedAt.isNull() }
            .map { it.toSample() }

    override fun findById(id: SampleId): Sample? =
        SamplesTable.selectAll()
            .where { SamplesTable.id eq id.value }
            .andWhere { SamplesTable.deletedAt.isNull() }
            .firstOrNull()
            ?.toSample()

    @Transactional
    override fun create(sample: Sample): Sample =
        SamplesTable.insertReturning {
            it[name] = sample.name
            it[description] = sample.description
        }.single().toSample()

    @Transactional
    override fun update(sample: Sample): Sample? =
        SamplesTable.updateReturning(where = { SamplesTable.id eq sample.id.value }) {
            it[name] = sample.name
            it[description] = sample.description
        }.singleOrNull()?.toSample()

    @Transactional
    override fun delete(id: SampleId): Boolean {
        val deletedCount = SamplesTable.deleteWhere { SamplesTable.id eq id.value }
        return deletedCount > 0
    }

    override fun findAll2(): List<Sample> =
        SampleEntity.find { SamplesTable.deletedAt.isNull() }
            .map { it.toSample() }

    override fun findById2(id: SampleId): Sample? =
        SampleEntity.find { (SamplesTable.id eq id.value) and (SamplesTable.deletedAt.isNull()) }
            .firstOrNull()
            ?.toSample()

    @Transactional
    override fun create2(sample: Sample): Sample =
        SampleEntity.new(sample.id.value) {
            name = sample.name
            description = sample.description
        }.toSample()

    @Transactional
    override fun update2(sample: Sample): Sample? =
        SampleEntity.findByIdAndUpdate(sample.id.value) { entity ->
            entity.name = sample.name
            entity.description = sample.description
            entity.updatedAt = OffsetDateTime.now()
        }?.toSample()

    @Transactional
    override fun delete2(id: SampleId): Boolean {
        val sampleEntity = SampleEntity.findById(id.value) ?: return false
        sampleEntity.delete()
        return true
    }

    private fun ResultRow.toSample() = Sample(
        id = SampleId(this[SamplesTable.id].value),
        name = this[SamplesTable.name],
        description = this[SamplesTable.description],
    )

    private fun SampleEntity.toSample() = Sample(
        id = SampleId(this.id.value),
        name = this.name,
        description = this.description,
    )
}
