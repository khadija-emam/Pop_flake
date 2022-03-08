package com.project.pop_flake.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.project.pop_flake.R
import com.project.pop_flake.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment:Fragment() {
    private  var _binding: HomeFragmentBinding?=null
    private val binding get() = _binding!!

    private lateinit var boxOfficeAdapter: BoxOfficeAdapter
    private lateinit var comingSoonAdapter: MoviesAdapter
    private lateinit var inTheaterAdapter: MoviesAdapter
    private lateinit var topRatedAdapter: MoviesAdapter

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater,container, false)
        val view=binding.root

        initMoviesAdapter()
        Handler().postDelayed({
            getComingSoon()
            getBoxOffice()
            getInTheater()
            getTopRated()
        }, 1000)

        navigateToDetails()
        swipeRefresh()

        return view
    }
    private fun initMoviesAdapter(){
        comingSoonAdapter= MoviesAdapter(MovieClickListener{viewModel.onItemClicked(it)})
        binding.comingSoonRv.adapter=comingSoonAdapter
        inTheaterAdapter=MoviesAdapter(MovieClickListener{viewModel.onItemClicked(it)})
        binding.inTheaterRv.adapter=inTheaterAdapter
        topRatedAdapter=MoviesAdapter(MovieClickListener{viewModel.onItemClicked(it)})
        binding.topRatedRv.adapter=topRatedAdapter
        boxOfficeAdapter=BoxOfficeAdapter(MovieClickListener{viewModel.onItemClicked(it)})
        binding.topTenRv.adapter=boxOfficeAdapter

    }

    private fun getComingSoon(){
       val result= viewModel.getComingSoon()
        result.observe(viewLifecycleOwner, Observer {
          comingSoonAdapter.submitList(it)
            binding.swiperefresh.isRefreshing=false

        })
    }
    private fun getInTheater(){
        val result= viewModel.getInTheater()
        result.observe(viewLifecycleOwner, Observer {
            inTheaterAdapter.submitList(it)
            binding.swiperefresh.isRefreshing=false

        })
    }
    private fun getTopRated(){
        val result= viewModel.getTopRated()
        result.observe(viewLifecycleOwner, Observer {
            topRatedAdapter.submitList(it)
            binding.swiperefresh.isRefreshing=false

        })
    }
    private fun getBoxOffice(){
        val result= viewModel.getBoxOffice()
        result.observe(viewLifecycleOwner, Observer {
            boxOfficeAdapter.submitList(it)
            binding.swiperefresh.isRefreshing=false

        })
    }

    private fun navigateToDetails(){
        viewModel.navigate.observe(viewLifecycleOwner, Observer {
            if (it!=null) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        it
                    )
                )
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun myUpdateOperation(){
        viewModel.getMoviesDataFromRemoteDataSource()

            getComingSoon()
            getInTheater()
            getTopRated()
            getBoxOffice()



    }
    private fun swipeRefresh(){
        binding.swiperefresh.setOnRefreshListener {
            viewModel.getMoviesDataFromRemoteDataSource()
           myUpdateOperation()
        }
    }


}
