package uz.mamadalievdev.rawg.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.model.PublisherResult
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Video
import uz.mamadalievdev.rawg.data.game_details.model.trailers.Videos
import uz.mamadalievdev.rawg.data.home.models.Response
import uz.mamadalievdev.rawg.databinding.ItemGameBinding
import uz.mamadalievdev.rawg.databinding.ItemPublisherBinding
import uz.mamadalievdev.rawg.databinding.ItemVideoBinding


class PublisherAdapter : RecyclerView.Adapter<PublisherAdapter.HomeMovieViewHolder>() {
    var _data = mutableListOf<PublisherResult>()

    private var itemClickListener: ((id: Long) -> Unit)? = null

    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPublishers(newData: List<PublisherResult>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HomeMovieViewHolder(private val binding: ItemPublisherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(data: PublisherResult) {
            binding.apply {
                name.text = data.name
                Glide.with(binding.root.context)
                    .load(data.image_background)
                    .into(binding.image)

                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.id.toLong())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeMovieViewHolder(
            ItemPublisherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) =
        holder.bindView(_data[position])
}