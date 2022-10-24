package uz.mamadalievdev.rawg.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.mamadalievdev.rawg.R
import uz.mamadalievdev.rawg.data.base.model.platforms.PlatformResult
import uz.mamadalievdev.rawg.data.home.models.Platform
import uz.mamadalievdev.rawg.data.home.models.Response
import uz.mamadalievdev.rawg.databinding.ItemGameBinding
import uz.mamadalievdev.rawg.databinding.ItemPlatformBinding


class PlatformsAdapter : RecyclerView.Adapter<PlatformsAdapter.HomeMovieViewHolder>() {
    var _data = mutableListOf<PlatformResult>()

    private var itemClickListener: ((id: Long) -> Unit)? = null

    fun setItemClickListener(f: (id: Long) -> Unit) {
        itemClickListener = f
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setGames(newData: List<PlatformResult>) {
        _data.clear()
        _data.addAll(newData)
        notifyDataSetChanged()
    }

    inner class HomeMovieViewHolder(private val binding: ItemPlatformBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(data: PlatformResult) {
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
            ItemPlatformBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount() = _data.size

    override fun onBindViewHolder(holder: HomeMovieViewHolder, position: Int) =
        holder.bindView(_data[position])
}