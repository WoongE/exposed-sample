package kim.wonung.exposed_example.domain

import java.util.*

data class Sample(
    val id: SampleId,
    var name: String,
    var description: String,
) {
    companion object {
        fun create(
            name: String,
            description: String,
        ): Sample = Sample(
            id = SampleId(UUID.randomUUID()),
            name = name,
            description = description,
        )
    }
}

@JvmInline
value class SampleId(val value: UUID)

interface SampleRepository {
    fun findAll(): List<Sample>
    fun findById(id: SampleId): Sample?
    fun create(sample: Sample): Sample
    fun update(sample: Sample): Sample?
    fun delete(id: SampleId): Boolean

    fun findAll2(): List<Sample>
    fun findById2(id: SampleId): Sample?
    fun create2(sample: Sample): Sample
    fun update2(sample: Sample): Sample?
    fun delete2(id: SampleId): Boolean
}
