package uz.mamadalievdev.rawg.data.base.model.platforms

data class PlatformResult(
    val games: List<Game>,
    val games_count: Int,
    val id: Int,
    val image: Any,
    val image_background: String,
    val name: String,
    val slug: String,
    val year_end: Any,
    val year_start: Int
)