package uz.mamadalievdev.rawg.domain.game_details

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.game_details.model.GameDetails
import uz.mamadalievdev.rawg.data.game_details.model.screnshots.Screenshots
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.data.home.models.Games

interface GameDetailsRepository {
    suspend fun getGameDetails(id : Long): Flow<BaseNetworkResult<GameDetails>>
    suspend fun getGameVideos(id : Long): Flow<BaseNetworkResult<Videos>>
    suspend fun getGameScreenshots(id : Long): Flow<BaseNetworkResult<Screenshots>>
}