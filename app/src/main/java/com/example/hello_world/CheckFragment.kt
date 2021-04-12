package com.example.hello_world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hello_world.databinding.FragmentCheckBinding
import com.example.hello_world.util.Constants


class CheckFragment : Fragment() {

    private lateinit var _binding: FragmentCheckBinding
    private val binding get() = _binding

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
                disableAll()
            }
        }

        binding.accessRadio.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) enableRadioGroup()
            else {
                resetRadioGrout()
                disableRadioGroup()
            }
        }

        binding.accessSeek.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) binding.seekRadioControl.isEnabled = true
            else {
                binding.seekRadioControl.isEnabled = false
                resetSeek()
            }
        }
    }

    private fun radioGroupController() {
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            if (binding.seekRadioControl.isEnabled)
                when (checkedId) {
                    binding.radio1.id -> binding.seekRadioControl.progress =
                        Constants.VALUE_FOR_RB_1
                    binding.radio2.id -> binding.seekRadioControl.progress =
                        Constants.VALUE_FOR_RB_2
                    binding.radio3.id -> binding.seekRadioControl.progress =
                        Constants.VALUE_FOR_RB_3
                    else -> binding.seekRadioControl.progress = 0
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
                if (isEnabledRadioGroup()) {

                    when (seek.progress) {
                        1 -> binding.radio1.isChecked = true
                        2 -> binding.radio2.isChecked = true
                        3 -> binding.radio3.isChecked = true
                        else -> binding.radioGroup.clearCheck()
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (isEnabledRadioGroup()) {
                    resetRadioGrout()
                    binding.radioGroup.clearCheck()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (isEnabledRadioGroup()) {
                    if (seekBar?.progress == 0) {
                        resetRadioGrout()
                        binding.radioGroup.clearCheck()
                    }
                }
            }
        })
    }

    private fun resetRadioGrout() {
        for (i in 0 until binding.radioGroup.childCount) {
            val radioButton = binding.radioGroup.getChildAt(i) as RadioButton
            radioButton.isChecked = false
        }
    }

    private fun resetSeek() {
        binding.seekRadioControl.progress = 0
    }


    private fun enableTools() {
        binding.accessRadio.isEnabled = true
        binding.accessSeek.isEnabled = true
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
        binding.accessRadio.isChecked = false
        binding.accessSeek.isEnabled = false
        binding.accessSeek.isChecked = false
        binding.seekRadioControl.isEnabled = false
    }
}