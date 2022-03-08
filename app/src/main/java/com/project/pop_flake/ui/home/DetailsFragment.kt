package com.project.pop_flake.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.project.pop_flake.bindImage
import com.project.pop_flake.databinding.DetailsFragmentBinding

class DetailsFragment :Fragment() {
    private  var _binding: DetailsFragmentBinding?=null
    private val binding get() = _binding!!
    val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root

        setMovieDetails()

        return view
    }

    private fun setMovieDetails(){
        binding.fullTitle.text=args.movie.fullTitle
        binding.sourceName.text=args.movie.name
        binding.description.text=args.movie.plot
        bindImage(binding.image,args.movie.image)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }