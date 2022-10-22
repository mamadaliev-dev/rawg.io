package uz.mamadalievdev.rawg.domain.game_details

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.game_details.model.GameDetails
import uz.mamadalievdev.rawg.data.game_details.model.screnshots.Screenshots
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.data.home.models.Games
import javax.inject.Inject

class GameDetailsUseCase @Inject constructor(private val homeRepository: GameDetailsRepository) {
    suspend fun getGameDetails(id : Long): Flow<BaseNetworkResult<GameDetails>> {
        return homeRepository.getGameDetails(id)
    }

    suspend fun getGameVideos(id : Long): Flow<BaseNetworkResult<Videos>> {
        return homeRepository.getGameVideos(id)
    }

    suspend fun getGameScreenshots(id : Long): Flow<BaseNetworkResult<Screenshots>> {
        return homeRepository.getGameScreenshots(id)
    }
}