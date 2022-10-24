package uz.mamadalievdev.rawg.data.base

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.mamadalievdev.rawg.BuildConfig
import uz.mamadalievdev.rawg.data.base.model.PublisherDetails
import uz.mamadalievdev.rawg.data.base.model.Publishers
import uz.mamadalievdev.rawg.data.base.model.platforms.PlatformsResponse
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublishersGame
import uz.mamadalievdev.rawg.data.home.models.Games

interface BaseService {
    @GET("publishers")
    suspend fun getPublishers(
        @Query("page_size") page_size: Int = 100,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<Publishers>

    @GET("publishers/{id}")
    suspend fun getPublisherDetails(
        @Path("id") id: Long,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<PublisherDetails>

    @GET("games")
    suspend fun getPublisherGames(
        @Query("publishers") publishers: Long,
        @Query("page_size") page_size: Int = 100,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<PublishersGame>

    @GET("platforms")
    suspend fun getPlatforms(
        @Query("page_size") page_size: Int = 100,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<PlatformsResponse>

    @GET("games")
    suspend fun getPlatformGames(
        @Query("platforms") platforms: Long,
        @Query("page_size") page_size: Int = 100,
        @Query("key") closeReason: String = BuildConfig.TOKEN,
    ): Response<Games>
}