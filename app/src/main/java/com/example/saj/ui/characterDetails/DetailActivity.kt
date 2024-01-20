package com.example.saj.ui.characterDetails

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.saj.R
import com.example.saj.data.Character
import com.example.saj.databinding.DetailActivityBinding
import com.example.saj.ui.Indicator
import com.example.saj.ui.utils.RikKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: DetailActivityBinding
    private val viewModel by viewModels<CharacterDetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityId = intent.getIntExtra(RikKeys.CHARACTER_ID_ARG, 0)

        viewModel.getCharacterDetails(activityId).observe(this) {
            setupCharacterData(it)
        }
    }

    private fun setupCharacterData(receiveData: Character) = with(binding) {
        tvCharacterName.text = receiveData.name
        tvStatus.text = receiveData.status
        tvSpecies.text = receiveData.species
        tvLocationInfo.text = receiveData.location.name
        Glide.with(imgCharacter).load(receiveData.image).into(imgCharacter)
        when(tvStatus.text.toString().uppercase()){
            Indicator.ALIVE.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_alive)
            Indicator.UNKNOWN.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_unknown)
            Indicator.DEAD.toString() -> imgIndicator.setBackgroundResource(R.drawable.indicator_dead)
        }
    }
}