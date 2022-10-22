package uz.mamadalievdev.rawg.data.base.model

data class PublisherResult(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)