package com.example.hello.world.app.ui.input

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.hello.world.app.R
import com.example.hello.world.app.databinding.FragmentInputsBinding

class InputsFragment : Fragment() {

    private lateinit var binding: FragmentInputsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
        binding.inputsButtonCloseKeyboard.setOnClickListener {
            activity?.let { view ->
                hideSoftKeyboard(view)
            }
        }
    }

    private fun setupToolBar() {
        with(binding.inputsToolBar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = getText(R.string.buttons_input_title)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }
}