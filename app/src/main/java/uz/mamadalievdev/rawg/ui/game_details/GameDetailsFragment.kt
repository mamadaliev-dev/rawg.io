package uz.mamadalievdev.rawg.ui.game_details

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.BaseFragment
import uz.mamadalievdev.rawg.data.base.formatDate
import uz.mamadalievdev.rawg.databinding.FragmentGameDetailsBinding

@AndroidEntryPoint
class GameDetailsFragment :
    BaseFragment<FragmentGameDetailsBinding>(FragmentGameDetailsBinding::inflate) {
    private val viewModel: GameDetailsViewModel by viewModels()
    @SuppressLint("SetTextI18n")
    override fun onViewCreate() {

        val ID = requireArguments().getLong("ID", 0)

        binding.btnVideo.setOnClickListener {
            val bundle = bundleOf("ID" to ID)
            navController.navigate(R.id.action_gameDetailsFragment_to_videosFragment, bundle)
        }

//        binding.btnCast.setOnClickListener {
//            val bundle = bundleOf("TV_ID" to TV_ID)
//            navController.navigate(R.id.action_TVShowDetailFragment_to_castFragment, bundle)
//        }

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
            viewModel.getGames(ID)
        }

        viewModel.getGames(ID)
        viewModel.gamesLiveData.observe(viewLifecycleOwner) { result ->
            binding.apply {
                content.text = result.description_raw
                name.text = result.name
                ratingNumber.text = "${result.rating}"
                firstAirDate.text = result.released.formatDate()

                Glide.with(binding.root.context)
                    .load(result.background_image)
                    .into(binding.poster)

                Glide.with(binding.root.context)
                    .load(result.background_image_additional)
                    .into(binding.image)
            }
        }
    }
}