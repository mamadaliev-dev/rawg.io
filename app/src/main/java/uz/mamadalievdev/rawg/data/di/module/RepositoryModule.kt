package uz.mamadalievdev.rawg.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.mamadalievdev.rawg.data.base.BaseRepositoryImpl
import uz.mamadalievdev.rawg.data.base.BaseService
import uz.mamadalievdev.rawg.data.game_details.GameDetailsRepositoryImpl
import uz.mamadalievdev.rawg.data.game_details.GameDetailsService
import uz.mamadalievdev.rawg.data.home.HomeRepositoryImpl
import uz.mamadalievdev.rawg.data.home.HomeService
import uz.mamadalievdev.rawg.domain.base.BaseRepository
import uz.mamadalievdev.rawg.domain.game_details.GameDetailsRepository
import uz.mamadalievdev.rawg.domain.home.HomeRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideHomeRepository(mainService: HomeService): HomeRepository {
        return HomeRepositoryImpl(mainService)
    }

    @Provides
    fun provideGameDetailsRepository(mainService: GameDetailsService): GameDetailsRepository {
        return GameDetailsRepositoryImpl(mainService)
    }

    @Provides
    fun provideBaseRepository(mainService: BaseService): BaseRepository {
        return BaseRepositoryImpl(mainService)
    }
}