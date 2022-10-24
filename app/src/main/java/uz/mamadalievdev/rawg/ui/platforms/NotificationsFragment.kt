package uz.mamadalievdev.rawg.ui.platforms

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentNotificationsBinding
import uz.mamadalievdev.rawg.ui.adapters.PlatformsAdapter

@AndroidEntryPoint
class NotificationsFragment :
    BaseFragment<FragmentNotificationsBinding>(FragmentNotificationsBinding::inflate) {
    private val viewModel: NotificationsViewModel by viewModels()

    private val adapter by lazy {
        PlatformsAdapter()
    }

    override fun onViewCreate() {
        binding.apply {
            platformsList.layoutManager = GridLayoutManager(requireContext(),
                1, GridLayoutManager.VERTICAL, false)
            platformsList.adapter = adapter
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        adapter.setItemClickListener {
            val bundle = bundleOf("ID" to it)
            navController.navigate(R.id.action_navigation_notifications_to_gamesFragment, bundle)
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.getPlatforms()
        viewModel.platformsLiveData.observe(viewLifecycleOwner) {
            adapter.setGames(it)
        }

    }
}