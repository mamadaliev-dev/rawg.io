package uz.mamadalievdev.rawg.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.base.model.PublisherDetails
import uz.mamadalievdev.rawg.data.base.model.PublisherResult
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublisherGameResult
import uz.mamadalievdev.rawg.data.home.models.Response
import uz.mamadalievdev.rawg.domain.base.BaseUseCase
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val mainUseCase: BaseUseCase,
) : ViewModel() {
    private val publishers = MutableLiveData<List<PublisherResult>>()
    val publishersLiveData: LiveData<List<PublisherResult>> get() = publishers

    private val publishersDetails = MutableLiveData<PublisherDetails>()
    val publishersDetailsLiveData: LiveData<PublisherDetails> get() = publishersDetails

    private val publishersGames = MutableLiveData<List<PublisherGameResult>>()
    val publishersGamesLiveData: LiveData<List<PublisherGameResult>> get() = publishersGames


    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getPublishers() {
        viewModelScope.launch {
            mainUseCase.getPublishers().catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            publishers.value = item.results
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

    fun getPublisherDetails(id: Long) {
        viewModelScope.launch {
            mainUseCase.getPublisherDetails(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            publishersDetails.value = item
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

    fun getPublisherGames(id: Long) {
        viewModelScope.launch {
            mainUseCase.getPublisherGames(id).catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            publishersGames.value = item.results
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