package uz.mamadalievdev.rawg.data.game_details

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.game_details.model.GameDetails
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.data.home.models.Games
import uz.mamadalievdev.rawg.domain.game_details.GameDetailsRepository
import uz.mamadalievdev.rawg.domain.home.HomeRepository
import javax.inject.Inject

class GameDetailsRepositoryImpl @Inject constructor(private val service: GameDetailsService) :
    GameDetailsRepository {
    override suspend fun getGameDetails(id:Long): Flow<BaseNetworkResult<GameDetails>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getGameDetails(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getGameVideos(id: Long): Flow<BaseNetworkResult<Videos>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getGameVideos(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}