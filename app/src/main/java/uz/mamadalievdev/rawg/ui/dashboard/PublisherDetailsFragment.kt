package uz.mamadalievdev.rawg.ui.dashboard

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublisherGameResult
import uz.mamadalievdev.rawg.databinding.FragmentPublisherDetailsBinding
import uz.mamadalievdev.rawg.ui.adapters.HomeGamesAdapter
import uz.mamadalievdev.rawg.ui.adapters.PublisherAdapter
import uz.mamadalievdev.rawg.ui.adapters.PublisherGamesAdapter

@AndroidEntryPoint
class PublisherDetailsFragment :
    BaseFragment<FragmentPublisherDetailsBinding>(FragmentPublisherDetailsBinding::inflate) {
    private val viewModel: DashboardViewModel by viewModels()

    private val adapter by lazy {
        PublisherGamesAdapter()
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreate() {
        val ID = requireArguments().getLong("ID", 0)

        binding.apply {
            publisherGames.layoutManager = GridLayoutManager(requireContext(),
                2, GridLayoutManager.VERTICAL, false)
            publisherGames.adapter = adapter
        }

        adapter.setItemClickListener {
            val bundle = bundleOf("ID" to it)
            navController.navigate(R.id.action_publisherDetailsFragment_to_gameDetailsFragment,
                bundle)
        }

        viewModel.getPublisherGames(ID)
        viewModel.publishersGamesLiveData.observe(viewLifecycleOwner) {
            adapter.setPublisherGames(it)
        }


        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
            if (it == true) {
                binding.scrollView.visibility = View.GONE
            } else {
                binding.scrollView.visibility = View.VISIBLE
            }
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPublisherGames(ID)
            viewModel.getPublisherDetails(ID)
        }

        viewModel.getPublisherDetails(ID)
        viewModel.publishersDetailsLiveData.observe(viewLifecycleOwner) { result ->
            binding.apply {
                content.text = result.description
                name.text = result.name

                Glide.with(binding.root.context)
                    .load(result.image_background)
                    .into(binding.poster)

                Glide.with(binding.root.context)
                    .load(result.image_background)
                    .into(binding.image)
            }
        }
    }
}