package kim.wonung.exposed_example.persistence

import kim.wonung.exposed_example.domain.Sample
import kim.wonung.exposed_example.domain.SampleId
import kim.wonung.exposed_example.domain.SampleRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

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

    private fun ResultRow.toSample() = Sample(
        id = SampleId(this[SamplesTable.id].value),
        name = this[SamplesTable.name],
        description = this[SamplesTable.description],
    )
}
