package com.example.saj.ui.characters

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saj.databinding.ActivityMainBinding
import com.example.saj.recycler.RikAdapter
import com.example.saj.ui.characterDetails.DetailActivity
import com.example.saj.ui.utils.RikKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val rikAdapter by lazy { RikAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCharacters().observe(this) {
            rikAdapter.submitList(it)
            setupCharactersRecycler()
        }
    }

    private fun setupCharactersRecycler() = with(binding.recyclerView){
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = rikAdapter
    }

    private fun onClickItem(characterId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(RikKeys.CHARACTER_ID_ARG, characterId)
        startActivity(intent)
    }
}