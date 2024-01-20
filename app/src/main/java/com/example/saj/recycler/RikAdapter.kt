package com.example.saj.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.saj.R
import com.example.saj.data.Character
import com.example.saj.databinding.ItemRikBinding
import com.example.saj.ui.Indicator

class RikAdapter(
    private val onClick: (characterId: Int) -> Unit,
) : Adapter<RikAdapter.RikViewHolder>() {

    private var list = listOf<Character>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RikViewHolder {
        return RikViewHolder(
            ItemRikBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RikViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun submitList(list: List<Character>) {
        this.list = list
    }

    inner class RikViewHolder(private val binding: ItemRikBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Character) = with(binding) {
            tvCharacterName.text = model.name
            tvExistence.text = model.status
            tvSpecies.text = model.species
            tvLocationInfo.text = model.location.name
            Glide.with(imgCharacter).load(model.image).into(imgCharacter)
            itemView.setOnClickListener { onClick(model.id) }
            when (tvExistence.text.toString().uppercase()) {
                Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
                Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
                Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
            }
        }
    }
}
