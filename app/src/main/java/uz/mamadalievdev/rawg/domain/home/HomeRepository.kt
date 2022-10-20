package uz.mamadalievdev.rawg.domain.home

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.home.models.Games

interface HomeRepository {
    suspend fun getGames(): Flow<BaseNetworkResult<Games>>
}