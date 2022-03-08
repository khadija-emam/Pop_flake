package com.project.pop_flake.ui.complaints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.project.pop_flake.R
import com.project.pop_flake.databinding.ComplaintsFragmentBinding
import com.project.pop_flake.databinding.DetailsFragmentBinding

class ComplaintsDialog :DialogFragment(){
    private  var _binding: ComplaintsFragmentBinding?=null
    private val binding get() = _binding!!
     private var title=""
    private var message=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ComplaintsFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        send()

        return view
    }

    private fun getTheComplaints(){
        title= binding.titleEd.text.toString()
         message= binding.messageEd.text.toString()
    }
    private fun send(){
        getTheComplaints()
        binding.sendBtn.setOnClickListener{
            showToast()
            binding.titleEd.text?.clear()
            binding.messageEd.text?.clear()
            title=""
            message=""

        }
    }
    private fun showToast(){
        Toast.makeText(context,getString(R.string.message_sent),Toast.LENGTH_SHORT).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}