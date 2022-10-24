package uz.mamadalievdev.rawg.domain.base

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.base.model.PublisherDetails
import uz.mamadalievdev.rawg.data.base.model.Publishers
import uz.mamadalievdev.rawg.data.base.model.platforms.PlatformsResponse
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublishersGame
import uz.mamadalievdev.rawg.data.home.models.Games

interface BaseRepository {
    suspend fun getPublishers(): Flow<BaseNetworkResult<Publishers>>
    suspend fun getPublisherDetails(id: Long): Flow<BaseNetworkResult<PublisherDetails>>
    suspend fun getPublisherGames(id: Long): Flow<BaseNetworkResult<PublishersGame>>
    suspend fun getPlatforms(): Flow<BaseNetworkResult<PlatformsResponse>>
    suspend fun getPlatformGames(id: Long): Flow<BaseNetworkResult<Games>>

}