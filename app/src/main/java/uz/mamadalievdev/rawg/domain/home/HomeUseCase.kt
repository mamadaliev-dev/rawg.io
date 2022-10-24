package uz.mamadalievdev.rawg.domain.home

import kotlinx.coroutines.flow.Flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.home.models.Games
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val homeRepository: HomeRepository) {
    suspend fun getGames(): Flow<BaseNetworkResult<Games>> {
        return homeRepository.getGames()
    }

    suspend fun getSearchedGames(search : String): Flow<BaseNetworkResult<Games>> {
        return homeRepository.getSearchedGames(search)
    }
}