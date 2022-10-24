package uz.mamadalievdev.rawg.ui.platforms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import uz.mamadalievdev.rawg.data.base.BaseNetworkResult
import uz.mamadalievdev.rawg.data.base.model.platforms.PlatformResult
import uz.mamadalievdev.rawg.domain.base.BaseUseCase
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel  @Inject constructor(
    private val mainUseCase: BaseUseCase,
) : ViewModel() {
    private val platforms = MutableLiveData<List<PlatformResult>>()
    val platformsLiveData: LiveData<List<PlatformResult>> get() = platforms

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> get() = _isLoadingLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun getPlatforms() {
        viewModelScope.launch {
            mainUseCase.getPlatforms().catch {
                Log.d("DDDD", "getServicesResponse: $this")
            }.collect { result ->
                when (result) {
                    is BaseNetworkResult.Success -> {
                        result.data?.let { item ->
                            platforms.value = item.results
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