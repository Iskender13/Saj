package com.example.saj.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.saj.R
import com.example.saj.data.Character
import com.example.saj.databinding.ItemRikBinding
import com.example.saj.ui.Indicator

class RikAdapter(
    private val onClick: (characterId: Int) -> Unit,
) : ListAdapter<Character, RikViewHolder>(
    RikDiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RikViewHolder(
        ItemRikBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), onClick
    )


    override fun onBindViewHolder(holder: RikViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

    class RikViewHolder(
        private val binding: ItemRikBinding,
        private val onClick: (characterId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
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

class RikDiffCallback: DiffUtil.ItemCallback<Character>(){
    override fun areItemsTheSame(oldItem: Character, newItem: Character)=oldItem.id==newItem.id


    override fun areContentsTheSame(oldItem: Character, newItem: Character)=oldItem==newItem

}