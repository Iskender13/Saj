package com.example.saj.ui.characters

import android.content.Intent
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.saj.data.Resource
import com.example.saj.databinding.ActivityMainBinding
import com.example.saj.recycler.RikAdapter
import com.example.saj.ui.Base.BaseActivity
import com.example.saj.ui.characterDetails.DetailActivity
import com.example.saj.ui.utils.RikKeys
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private val rikAdapter by lazy { RikAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupCharactersRecycler()

        viewModel.getCharacters().stateHandler(
            success = {
                rikAdapter.submitList(it)
            },
            state = { state ->
                binding.progressBar.isVisible=state is Resource.Loading
            }
        )
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