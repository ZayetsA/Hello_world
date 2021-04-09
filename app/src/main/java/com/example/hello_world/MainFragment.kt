package com.example.hello_world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.hello_world.databinding.FragmentMainBinding


class MainFragment : Fragment(), View.OnClickListener {
    private var navigationController: NavController? = null
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        navigationController = Navigation.findNavController(view)
        binding.openRadio.setOnClickListener(this)
        binding.openInputs.setOnClickListener(this)
        binding.openButtons.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                binding.openButtons.id -> navigationController?.navigate(R.id.action_mainFragment_to_buttonsFragment)
                binding.openRadio.id -> navigationController?.navigate(R.id.action_mainFragment_to_checkFragment)
                binding.openInputs.id -> navigationController?.navigate(R.id.action_mainFragment_to_inputsFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}