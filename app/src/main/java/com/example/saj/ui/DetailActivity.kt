package com.example.saj.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.saj.R
import com.example.saj.data.RikModel
import com.example.saj.databinding.DetailActivityBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding: DetailActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)




        val result: RikModel.Result = intent.getSerializableExtra("EXTRA_CHARACTER") as RikModel.Result

        Glide.with(binding.imageViewDetail.context)
            .load(result.image)
            .into(binding.imageViewDetail)
        binding.nameDetail.text=result.name
        binding.statusDetail.text=result.status
        binding.speciesDetail.text=result.species
        binding.locationDetail.text=result.location.name

    }
}
