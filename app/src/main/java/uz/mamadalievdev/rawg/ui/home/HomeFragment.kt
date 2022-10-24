package uz.mamadalievdev.rawg.ui.home

import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentHomeBinding
import uz.mamadalievdev.rawg.ui.adapters.HomeGamesAdapter

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

    private val adapter by lazy {
        HomeGamesAdapter()
    }

    override fun onViewCreate() {
        binding.apply {
            gamesList.layoutManager = GridLayoutManager(requireContext(),
                2, GridLayoutManager.VERTICAL, false)
            gamesList.adapter = adapter
        }

        adapter.setItemClickListener {
            val bundle = bundleOf("ID" to it)
            navController.navigate(R.id.action_navigation_home_to_gameDetailsFragment, bundle)
        }

        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getGames()
        }

        viewModel.getGames()
        viewModel.gamesLiveData.observe(viewLifecycleOwner) {
            adapter.setGames(it.results)
        }

        binding.search.setOnSearchClickListener {
            binding.gamesList.visibility = View.GONE
            binding.searchedGamesList.visibility = View.VISIBLE
        }

        binding.search.setOnCloseListener {
            binding.searchedGamesList.visibility = View.GONE
            binding.gamesList.visibility = View.VISIBLE
            false
        }
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    binding.notFoundLayout.visibility = View.VISIBLE
                    binding.searchedGamesList.visibility = View.GONE
                } else {
                    viewModel.getSearchedGames(search = query)
                    viewModel.searchedGamesLiveData.observe(viewLifecycleOwner) {
                        if (it.results.isEmpty()) {
                            binding.notFoundLayout.visibility = View.VISIBLE
                            binding.searchedGamesList.visibility = View.GONE
                        } else {
                            binding.notFoundLayout.visibility = View.GONE
                            binding.searchedGamesList.visibility = View.VISIBLE
                            adapter.setGames(it.results)
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}