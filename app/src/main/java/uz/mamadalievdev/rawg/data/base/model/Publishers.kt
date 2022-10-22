package uz.mamadalievdev.rawg.data.base.model

data class Publishers(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PublisherResult>
)