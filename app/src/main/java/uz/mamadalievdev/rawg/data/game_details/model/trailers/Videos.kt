package uz.mamadalievdev.rawg.data.game_details.model.trailers

data class Videos(
    val count: Int,
    val next: Int,
    val previous: Int,
    val results: List<Video>
)