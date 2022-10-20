package uz.mamadalievdev.rawg.data.home

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.mamadalievdev.rawg.BuildConfig
import uz.mamadalievdev.rawg.data.home.models.Games

interface HomeService {
    @GET("games")
    suspend fun getGames(
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<Games>
}