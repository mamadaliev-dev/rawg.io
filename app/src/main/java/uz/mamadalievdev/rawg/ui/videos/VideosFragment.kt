package uz.mamadalievdev.rawg.ui.videos

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.databinding.FragmentVideosBinding
import uz.mamadalievdev.rawg.ui.PlayerActivity
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
            val intent = Intent(requireContext(), PlayerActivity::class.java)
            intent.putExtra("VIDEO_ID", it)
            startActivity(intent)
        }

    }
}