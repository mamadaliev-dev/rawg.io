package uz.mamadalievdev.rawg.data.game_details

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mamadalievdev.rawg.BuildConfig
import uz.mamadalievdev.rawg.data.game_details.model.GameDetails
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.data.home.models.Games

interface GameDetailsService {
    @GET("games/{id}")
    suspend fun getGameDetails(
        @Path("id") id: Long,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<GameDetails>

    @GET("games/{id}/movies")
    suspend fun getGameVideos(
        @Path("id") id: Long,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<Videos>
}