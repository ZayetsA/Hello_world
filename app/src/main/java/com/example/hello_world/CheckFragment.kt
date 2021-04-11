package com.example.hello_world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hello_world.databinding.FragmentCheckBinding


class CheckFragment : Fragment() {

    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        checkBoxGroupController()
        radioGroupController()
        seekController()
        disableAll()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        super.onViewCreated(view, savedInstanceState)
    }


    private fun checkBoxGroupController() {
        binding.accessToolCheck.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                enableTools()
            } else {
                disableTools()
            }
        }

        binding.accessRadio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) enableRadioGroup()
            else disableRadioGroup()
        }

        binding.accessSeek.setOnCheckedChangeListener { _, isChecked ->
            binding.seekRadioControl.isEnabled = isChecked
        }
    }


    private fun radioGroupController() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (binding.seekRadioControl.isEnabled)
                when (checkedId) {
                    binding.radio1.id -> binding.seekRadioControl.setProgress(1, true)
                    binding.radio2.id -> binding.seekRadioControl.setProgress(2, true)
                    binding.radio3.id -> binding.seekRadioControl.setProgress(3, true)
                }
        }
    }

    private fun seekController() {
        binding.seekRadioControl.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
            } // nothing to write

            override fun onStartTrackingTouch(seekBar: SeekBar?) {} // nothing to write

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (isEnabledRadioGroup()) {
                    when (seekBar?.progress) {
                        1 -> binding.radio1.isChecked = true
                        2 -> binding.radio2.isChecked = true
                        3 -> binding.radio3.isChecked = true
                    }
                }
            }
        })
    }

    private fun enableTools() {
        binding.accessRadio.isEnabled = true
        binding.accessSeek.isEnabled = true
    }

    private fun disableTools() {
        binding.accessRadio.isEnabled = false
        binding.accessSeek.isEnabled = false
    }

    private fun isEnabledRadioGroup(): Boolean {
        if (!binding.radioGroup.getChildAt(0).isEnabled) return false
        return true
    }

    private fun enableRadioGroup() {
        for (i in 0 until binding.radioGroup.childCount) {
            binding.radioGroup.getChildAt(i).isEnabled = true
        }
    }

    private fun disableRadioGroup() {
        for (i in 0 until binding.radioGroup.childCount) {
            binding.radioGroup.getChildAt(i).isEnabled = false
        }
    }

    private fun disableAll() {
        disableRadioGroup()
        binding.accessRadio.isEnabled = false
        binding.accessSeek.isEnabled = false
        binding.seekRadioControl.isEnabled = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}