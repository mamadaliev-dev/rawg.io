package uz.mamadalievdev.rawg.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.model.publishers_games.PublisherGameResult
import uz.mamadalievdev.rawg.data.home.models.Response
import uz.mamadalievdev.rawg.databinding.ItemGameBinding

class PublisherGamesAdapter : RecyclerView.Adapter<PublisherGamesAdapter.HomeMovieViewHolder>() {
    var _data = mutableListOf<PublisherGameResult>()

    private var itemClickListener: ((id: Long) -> Unit)? = null

    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPublisherGames(newData: List<PublisherGameResult>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HomeMovieViewHolder(private val binding: ItemGameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(data: PublisherGameResult) {
            binding.apply {
                name.text = data.name
                Glide.with(binding.root.context)
                    .load(data.background_image)
                    .into(binding.image)

                itemView.setOnClickListener {
                    itemClickListener?.invoke(data.id.toLong())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        HomeMovieViewHolder(
            ItemGameBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) =
        holder.bindView(_data[position])
}