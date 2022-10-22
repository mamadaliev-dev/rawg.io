package uz.mamadalievdev.rawg.data.base.model.publishers_games

import uz.mamadalievdev.rawg.data.home.models.Response

data class PublishersGame(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PublisherGameResult>,
    val user_platforms: Boolean
)