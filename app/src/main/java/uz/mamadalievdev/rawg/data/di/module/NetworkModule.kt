package uz.mamadalievdev.rawg.data.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.mamadalievdev.rawg.BuildConfig
import uz.mamadalievdev.rawg.data.base.BaseService
import uz.mamadalievdev.rawg.data.game_details.GameDetailsService
import uz.mamadalievdev.rawg.data.home.HomeService

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(BuildConfig.BASE_URL)
            client(OkHttpClient.Builder().addNetworkInterceptor(httpLoggingInterceptor).build())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    fun provideGameDetailsService(retrofit: Retrofit): GameDetailsService {
        return retrofit.create(GameDetailsService::class.java)
    }

    @Provides
    fun provideBaseService(retrofit: Retrofit): BaseService {
        return retrofit.create(BaseService::class.java)
    }
}