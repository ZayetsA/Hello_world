package com.example.hello_world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hello_world.databinding.FragmentMainBinding


class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        binding.openRadio.setOnClickListener(this)
        binding.openInputs.setOnClickListener(this)
        binding.openButtons.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                binding.openButtons.id -> findNavController().navigate(R.id.action_mainFragment_to_buttonsFragment)
                binding.openInputs.id -> findNavController().navigate(R.id.action_mainFragment_to_inputsFragment)
                binding.openRadio.id -> findNavController().navigate(R.id.action_mainFragment_to_checkFragment)

            }
        }
    }
}