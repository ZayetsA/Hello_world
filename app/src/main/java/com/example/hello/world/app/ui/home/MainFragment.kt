package com.example.hello.world.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hello.world.app.R
import com.example.hello.world.app.databinding.FragmentMainBinding


class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.parentLayoutButtonOpenRadio.setOnClickListener(this)
        binding.parentLayoutButtonOpenInputs.setOnClickListener(this)
        binding.parentLayoutButtonOpenButtons.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view?.let {
            val action = when (view.id) {
                binding.parentLayoutButtonOpenButtons.id -> R.id.action_mainFragment_to_buttonsFragment
                binding.parentLayoutButtonOpenInputs.id -> R.id.action_mainFragment_to_inputsFragment
                binding.parentLayoutButtonOpenRadio.id -> R.id.action_mainFragment_to_checkFragment
                else -> null
            }
            action?.let { findNavController().navigate(action) }
        }
    }
}