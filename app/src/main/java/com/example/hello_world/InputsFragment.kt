package com.example.hello_world

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hello_world.databinding.FragmentInputsBinding


class InputsFragment : Fragment() {
    private lateinit var _binding: FragmentInputsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInputsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonCloseKeyboard.setOnClickListener {
            activity?.let { it1 ->
                hideSoftKeyboard(it1)
            }
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }
}