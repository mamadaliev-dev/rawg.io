package uz.mamadalievdev.rawg.data.game_details.model.screnshots

data class Screenshots(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<ScreenshotsResult>
)