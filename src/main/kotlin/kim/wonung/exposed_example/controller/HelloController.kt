package kim.wonung.exposed_example.controller

import kim.wonung.exposed_example.domain.Sample
import kim.wonung.exposed_example.domain.SampleId
import kim.wonung.exposed_example.domain.SampleRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HelloController(
    private val sampleRepository: SampleRepository,
) {
    @GetMapping("/hello")
    fun hello(): String = "Hello, World!"

    @GetMapping("/samples")
    fun getSamples(): List<Sample> = sampleRepository.findAll()

    @GetMapping("/samples/{id}")
    fun getSampleById(@PathVariable id: String): Sample =
        sampleRepository.findById(SampleId(UUID.fromString(id)))
            ?: throw IllegalArgumentException("Sample not found with id: $id")

    @GetMapping("/create-samples")
    fun createSample(
        @RequestParam name: String,
        @RequestParam description: String,
    ): Sample {
        val sample = Sample(
            id = SampleId(UUID.randomUUID()),
            name = name,
            description = description,
        )
        return sampleRepository.create(sample)
    }

    @GetMapping("/update-samples/{id}")
    fun updateSample(
        @PathVariable id: String,
        @RequestParam name: String,
        @RequestParam description: String,
    ): Sample? {
        val sample = sampleRepository.findById(SampleId(UUID.fromString(id)))
            ?: throw IllegalArgumentException("Sample not found with id: $id")
        sample.name = name
        sample.description = description
        return sampleRepository.update(sample)
    }

    @GetMapping("/delete-samples/{id}")
    fun deleteSample(@PathVariable id: String): Boolean =
        sampleRepository.delete(SampleId(UUID.fromString(id)))
}
