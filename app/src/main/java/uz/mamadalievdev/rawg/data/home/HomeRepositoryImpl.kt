package uz.mamadalievdev.rawg.data.home

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.home.models.Games
import uz.mamadalievdev.rawg.domain.home.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val service: HomeService) :
    HomeRepository {
    override suspend fun getGames(): Flow<BaseNetworkResult<Games>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getGames()
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }

    override suspend fun getSearchedGames(search: String): Flow<BaseNetworkResult<Games>> {
        return flow {
            emit(BaseNetworkResult.Loading(true))
            val response = service.getSearchedGames(search)
            emit(BaseNetworkResult.Loading(false))
            if (response.code() == 200) {
                emit(BaseNetworkResult.Success(response.body()!!))
            } else {
                emit(BaseNetworkResult.Error("Xatolik"))
            }
        }
    }
}