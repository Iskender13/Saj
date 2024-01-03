package com.example.saj.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.saj.data.RikModel
import com.example.saj.databinding.ItemRikBinding

class RikAdapter : RecyclerView.Adapter<RikAdapter.CharacterViewHolder>() {
    private var onItemClickListener: ((RikModel.Result) -> Unit)? = null
    private var models: List<RikModel.Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemRikBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding, onItemClickListener)
    }

    fun setOnItemClickListener(listener: (RikModel.Result) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount() = models.size

    fun setCharacters(models: List<RikModel.Result>) {
        this.models = models
        notifyDataSetChanged()
    }

    class CharacterViewHolder(
        private val binding: ItemRikBinding,
        private val clickListener: ((RikModel.Result) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(model: RikModel.Result) = with(binding) {
            Glide.with(binding.imageView.context)
                .load(model.image)
                .into(binding.imageView)
            name.text = model.name
            status.text = model.status
            species.text = model.species
            location.text = model.location.name
            itemView.setOnClickListener {
                clickListener?.invoke(model)
            }

        }
    }
}


