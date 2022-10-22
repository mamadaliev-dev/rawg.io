package uz.mamadalievdev.rawg.ui.game_details

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
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

        viewModel.getGameScreenshots(ID)

        viewModel.getGames(ID)

        viewModel.gamesLiveData.observe(viewLifecycleOwner) { result ->
            binding.apply {
                content.text = result.description_raw
                name.text = result.name
                ratingNumber.text = "${result.rating}\uD83D\uDD25"
                firstAirDate.text = result.released.formatDate()
                systemRequirementsRecommended.text = result.platforms[0].requirements.recommended
                systemRequirementsMinimum.text = result.platforms[0].requirements.minimum
                updated.text = result.updated.formatDate()
                playtime.text = "${result.playtime}"
                publisher.text = result.publishers[0].name
                developer.text = result.developers[0].name

//                Glide.with(binding.root.context)
//                    .load(result.background_image)
//                    .into(binding.poster)

                Glide.with(binding.root.context)
                    .load(result.background_image_additional)
                    .into(binding.image)

                if (result.movies_count == 0) {
                    binding.videosText.text = "No videos"
                } else {
                    binding.videosText.text = "${result.movies_count} - videos"
                    binding.btnVideo.setOnClickListener {
                        val bundle = bundleOf("ID" to ID)
                        navController.navigate(R.id.action_gameDetailsFragment_to_videosFragment,
                            bundle)
                    }
                }
            }
        }

        viewModel.gameScreenshotsLiveData.observe(this) { result ->
            val imageList = ArrayList<SlideModel>() // Create image list
            val positions = ArrayList<Long>() // Create image list
            result?.forEach { data ->
                positions.add(data.id.toLong())
                imageList.add(
                    SlideModel(
                        data.image)
                )
            }


            binding.imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP)
            binding.imageSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    val bundle = bundleOf("IMAGE_URL" to imageList[position].imageUrl)
                    navController.navigate(R.id.action_gameDetailsFragment_to_imageViewerFragment,
                        bundle)
                }
            })
        }
    }
}