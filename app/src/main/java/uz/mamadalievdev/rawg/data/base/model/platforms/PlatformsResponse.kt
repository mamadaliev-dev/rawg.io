package uz.mamadalievdev.rawg.data.base.model.platforms

data class PlatformsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PlatformResult>
)