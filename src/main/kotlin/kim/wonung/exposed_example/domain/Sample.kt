package kim.wonung.exposed_example.domain

import java.util.*

data class Sample(
    val id: SampleId,
    val name: String,
    val description: String,
)

@JvmInline
value class SampleId(val value: UUID)

interface SampleRepository {
    fun findAll(): List<Sample>
    fun findById(id: SampleId): Sample?
    fun create(sample: Sample): Sample
}
