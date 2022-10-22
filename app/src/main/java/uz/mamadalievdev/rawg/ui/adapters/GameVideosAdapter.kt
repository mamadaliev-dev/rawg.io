package uz.mamadalievdev.rawg.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Video
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.data.home.models.Response
import uz.mamadalievdev.rawg.databinding.ItemGameBinding
import uz.mamadalievdev.rawg.databinding.ItemVideoBinding


class GameVideosAdapter : RecyclerView.Adapter<GameVideosAdapter.HomeMovieViewHolder>() {
    var _data = mutableListOf<Video>()

    private var itemClickListener: ((id: String) -> Unit)? = null

    fun setItemClickListener(f: (id: String) -> Unit) {
        itemClickListener = f
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setVideos(newData: List<Video>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HomeMovieViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(data: Video) {
            binding.apply {
                name.text = data.name
                Glide.with(binding.root.context)
                    .load(data.preview)
                    .into(binding.image)

                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.data.`480`)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeMovieViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) =
        holder.bindView(_data[position])
}