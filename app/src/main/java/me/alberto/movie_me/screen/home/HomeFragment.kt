package me.alberto.movie_me.screen.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.*
import me.alberto.movie_me.databinding.FragmentHomeBinding
import me.alberto.movie_me.sources.RemoteDataSource


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter: GridAdapter
    private val uiScope = CoroutineScope(Dispatchers.Main + Job())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val remoteDataSource = RemoteDataSource
        val context = requireNotNull(this.requireActivity()).applicationContext
        val viewModelFactory = HomeViewModelFactory(remoteDataSource, context)
        viewModel =
            ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        adapter = GridAdapter(GridAdapter.MovieClickListener{ movie, position ->
            uiScope.launch{
                viewModel.toggleFav(movie, position)
            }
        })


        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        binding.recyclerView.adapter = adapter

        observe()
        return binding.root
    }

    private fun observe() {
        viewModel.clickedItem.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.notifyItemChanged(it)
            }
        })
    }

}
