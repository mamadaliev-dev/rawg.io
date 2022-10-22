package uz.mamadalievdev.rawg.data.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.base.BaseService
import uz.mamadalievdev.rawg.data.base.model.PublisherDetails
import uz.mamadalievdev.rawg.data.base.model.Publishers
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublishersGame
import uz.mamadalievdev.rawg.data.game_details.model.GameDetails
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.domain.base.BaseRepository
import uz.mamadalievdev.rawg.domain.game_details.GameDetailsRepository
import javax.inject.Inject

class BaseRepositoryImpl @Inject constructor(private val service: BaseService) : BaseRepository {
    override suspend fun getPublishers(): Flow<BaseNetworkResult<Publishers>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getPublishers()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getPublisherDetails(id : Long): Flow<BaseNetworkResult<PublisherDetails>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getPublisherDetails(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getPublisherGames(id: Long): Flow<BaseNetworkResult<PublishersGame>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getPublisherGames(id)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}