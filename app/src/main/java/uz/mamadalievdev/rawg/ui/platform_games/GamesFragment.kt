package uz.mamadalievdev.rawg.ui.platform_games

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentGamesBinding
import uz.mamadalievdev.rawg.ui.adapters.HomeGamesAdapter
import uz.mamadalievdev.rawg.ui.home.HomeViewModel

@AndroidEntryPoint
class GamesFragment : BaseFragment<FragmentGamesBinding>(FragmentGamesBinding::inflate) {
    private val viewModel: GamesViewModel by viewModels()

    private val adapter by lazy {
        HomeGamesAdapter()
    }
    override fun onViewCreate() {
        val ID = requireArguments().getLong("ID", 0)

        binding.apply {
            platformGamesList.layoutManager = GridLayoutManager(requireContext(),
                2, GridLayoutManager.VERTICAL, false)
            platformGamesList.adapter = adapter
        }


        adapter.setItemClickListener {
            val bundle = bundleOf("ID" to it)
            navController.navigate(R.id.action_gamesFragment_to_gameDetailsFragment, bundle)
        }


        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPlatformGames(ID)
        }

        viewModel.getPlatformGames(ID)
        viewModel.platformGamesLiveData.observe(viewLifecycleOwner) {
            adapter.setGames(it)
        }
    }
}