package com.project.pop_flake.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.project.pop_flake.databinding.DetailsFragmentBinding
import com.project.pop_flake.databinding.SearchFragmentBinding
import com.project.pop_flake.ui.home.BoxOfficeAdapter
import com.project.pop_flake.ui.home.HomeViewModel
import com.project.pop_flake.ui.home.MovieClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment:Fragment() {
    private  var _binding: SearchFragmentBinding?=null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SearchViewModel>()
    private lateinit var adapter: BoxOfficeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        initAdapter()
        search()
        observeForMovies()
        observeForLoading()
        observeForNavigation()
        observeForError()
        return view
    }
    private fun initAdapter(){
        adapter= BoxOfficeAdapter(MovieClickListener { viewModel.onItemClicked(it) })
        binding.searchRv.adapter=adapter
    }
    private fun search(){
        binding.searchButton.setOnClickListener {
            if (binding.searchEd.text.isNotEmpty()) {
                viewModel.search(binding.searchEd.text.toString())
            }
        }
    }
    private fun observeForMovies(){
        viewModel.moviesList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun observeForError(){
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
           Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })
    }
    private fun observeForNavigation(){
        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            it?.let {
               findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(it))
            }

        })

    }
    private fun observeForLoading() {
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.loading.visibility = View.VISIBLE
            } else {
                binding.loading.visibility = View.GONE

            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}