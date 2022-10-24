package uz.mamadalievdev.rawg.domain.base

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.base.model.PublisherDetails
import uz.mamadalievdev.rawg.data.base.model.Publishers
import uz.mamadalievdev.rawg.data.base.model.platforms.PlatformsResponse
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublishersGame
import uz.mamadalievdev.rawg.data.home.models.Games
import javax.inject.Inject

class BaseUseCase @Inject constructor(private val homeRepository: BaseRepository) {
    suspend fun getPublishers(): Flow<BaseNetworkResult<Publishers>> {
        return homeRepository.getPublishers()
    }

    suspend fun getPublisherDetails(id: Long): Flow<BaseNetworkResult<PublisherDetails>> {
        return homeRepository.getPublisherDetails(id)
    }

    suspend fun getPublisherGames(id: Long): Flow<BaseNetworkResult<PublishersGame>> {
        return homeRepository.getPublisherGames(id)
    }

    suspend fun getPlatforms(): Flow<BaseNetworkResult<PlatformsResponse>> {
        return homeRepository.getPlatforms()
    }

    suspend fun getPlatformGames(id : Long): Flow<BaseNetworkResult<Games>> {
        return homeRepository.getPlatformGames(id)
    }
}