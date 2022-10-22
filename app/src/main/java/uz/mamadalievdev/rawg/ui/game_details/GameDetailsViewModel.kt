package uz.mamadalievdev.rawg.ui.game_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.game_details.model.GameDetails
import uz.mamadalievdev.rawg.data.game_details.model.screnshots.ScreenshotsResult
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.domain.game_details.GameDetailsUseCase
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    private val mainUseCase: GameDetailsUseCase,
) : ViewModel() {
    private val games = MutableLiveData<GameDetails>()
    val gamesLiveData: LiveData<GameDetails> get() = games

    private val gameVideos = MutableLiveData<Videos>()
    val gameVideosLiveData: LiveData<Videos> get() = gameVideos

    private val gameScreenshots = MutableLiveData<List<ScreenshotsResult>>()
    val gameScreenshotsLiveData: LiveData<List<ScreenshotsResult>> get() = gameScreenshots

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getGames(id : Long) {
        viewModelScope.launch {
            mainUseCase.getGameDetails(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            games.value = item
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = it
                        }
                    }
                    is BaseNetworkResult.Loading -> {
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }

    fun getGameVideos(id : Long) {
        viewModelScope.launch {
            mainUseCase.getGameVideos(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            gameVideos.value = item
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = it
                        }
                    }
                    is BaseNetworkResult.Loading -> {
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }

    fun getGameScreenshots(id : Long) {
        viewModelScope.launch {
            mainUseCase.getGameScreenshots(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            gameScreenshots.value = item.results
                        }
                    }
                    is BaseNetworkResult.Error -> {
                        result.message.let {
                            _errorLiveData.value = it
                        }
                    }
                    is BaseNetworkResult.Loading -> {
                        result.isLoading?.let {
                            _isLoadingLiveData.value = it
                        }
                    }
                }
            }
        }
    }
}