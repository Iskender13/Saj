package com.example.saj.ui.characters

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saj.data.Resource
import com.example.saj.databinding.ActivityMainBinding
import com.example.saj.recycler.RikAdapter
import com.example.saj.ui.characterDetails.DetailActivity
import com.example.saj.ui.utils.RikKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val cartoonAdapter by lazy {RikAdapter(this::onClickItem)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getCharacters().observe(this) {result->
            when(result){
                is Resource.Error -> {
                    Toast.makeText(this, result.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.isVisible = false
                }
                is Resource.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Resource.Success -> {
                    cartoonAdapter.submitList(result.data)
                    binding.progressBar.isVisible = false
                }
            }
            setupCharactersRecycler()
        }
    }

    private fun setupCharactersRecycler() = with(binding.recyclerView){
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = cartoonAdapter
    }

    private fun onClickItem(characterId: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(RikKeys.CHARACTER_ID_ARG, characterId)
        startActivity(intent)
    }
}