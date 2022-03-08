package com.project.pop_flake.ui.settings

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.pop_flake.R
import com.project.pop_flake.databinding.SettingFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment:Fragment() {
    private  var _binding: SettingFragmentBinding?=null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SettingFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        enableDarkMood()
        navigateToSetting()
        return view
    }

    private fun enableDarkMood(){
        binding.darkMoodBtn.setOnClickListener {
            if(resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.darkMoodBtn.text = getString(R.string.enable_dark_mood)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.darkMoodBtn.text =getString(R.string.disable_dark_mood)
            }        }

    }
    private fun navigateToSetting(){
        binding.complaintsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_complaintsDialog)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}