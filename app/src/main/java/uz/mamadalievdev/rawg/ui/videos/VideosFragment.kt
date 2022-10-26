package uz.mamadalievdev.rawg.ui.videos

import android.content.Intent
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentVideosBinding
import uz.mamadalievdev.rawg.ui.adapters.GameVideosAdapter
import uz.mamadalievdev.rawg.ui.game_details.GameDetailsViewModel

@AndroidEntryPoint
class VideosFragment : BaseFragment<FragmentVideosBinding>(FragmentVideosBinding::inflate) {
    val viewModel: GameDetailsViewModel by viewModels()
    private val adapter by lazy {
        GameVideosAdapter()
    }

    override fun onViewCreate() {
        val id = requireArguments().getLong("ID", 0)

        binding.allTrailersList.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.allTrailersList.adapter = adapter

        viewModel.getGameVideos(id)
        viewModel.gameVideosLiveData.observe(viewLifecycleOwner) {
            it.results.let { item ->
                adapter.setVideos(item)
            }
        }

        adapter.setItemClickListener {
            val bundle = bundleOf("VIDEO_URL" to it)
            navController.navigate(R.id.action_videosFragment_to_playerFragment, bundle)
        }
    }
}