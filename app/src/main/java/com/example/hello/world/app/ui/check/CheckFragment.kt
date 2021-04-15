package com.example.hello.world.app.ui.check

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hello.world.app.R
import com.example.hello.world.app.databinding.FragmentCheckBinding

class CheckFragment : Fragment() {

    companion object {
        const val SEEKBAR_PROGRESS_VAL_1 = 1
        const val SEEKBAR_PROGRESS_VAL_2 = 2
        const val SEEKBAR_PROGRESS_VAL_3 = 3
    }

    private lateinit var _binding: FragmentCheckBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolBar()
        checkBoxGroupController()
        radioGroupController()
        seekController()
        disableAll()
    }

    private fun setupToolBar() {
        with(binding.checkToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = getText(R.string.buttons_check_title)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }
    }

    private fun onAccessToolStateChanged(compoundButton: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            enableTools()
        } else {
            disableAll()
        }
    }

    private fun onAccessRadioStateChanged(compoundButton: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            enableRadioGroup()
        } else {
            resetRadioGroup()
            disableRadioGroup()
        }
    }

    private fun onAccessSeekStateChanged(compoundButton: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            binding.checkSeekBar.isEnabled = true
        } else {
            binding.checkSeekBar.isEnabled = false
            resetSeek()
        }
    }

    private fun checkBoxGroupController() {
        with(binding) {
            checkCheckBoxTools.setOnCheckedChangeListener(::onAccessToolStateChanged)
            checkCheckBoxRadio.setOnCheckedChangeListener(::onAccessRadioStateChanged)
            checkCheckBoxSeek.setOnCheckedChangeListener(::onAccessSeekStateChanged)
        }
    }

    private fun radioGroupController() {
        with(binding) {
            checkRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                if (checkSeekBar.isEnabled) {
                    when (checkedId) {
                        checkRadioButton1.id -> checkSeekBar.setProgress(
                            SEEKBAR_PROGRESS_VAL_1,
                            true
                        )
                        checkRadioButton2.id -> checkSeekBar.setProgress(
                            SEEKBAR_PROGRESS_VAL_2,
                            true
                        )
                        checkRadioButton3.id -> checkSeekBar.setProgress(
                            SEEKBAR_PROGRESS_VAL_3,
                            true
                        )
                        else -> checkSeekBar.progress = 0
                    }
                }
            }
        }
    }

    private fun seekController() {
        binding.checkSeekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                if (isEnabledRadioGroup()) {
                    with(binding) {
                        when (seek.progress) {
                            SEEKBAR_PROGRESS_VAL_1 -> checkRadioButton1.isChecked = true
                            SEEKBAR_PROGRESS_VAL_2 -> checkRadioButton2.isChecked = true
                            SEEKBAR_PROGRESS_VAL_3 -> checkRadioButton3.isChecked = true
                            else -> {
                                checkRadioGroup.clearCheck()
                                resetRadioGroup()
                            }
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (isEnabledRadioGroup()) {
                    resetRadioGroup()
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (isEnabledRadioGroup()) {
                    if (seekBar?.progress == 0) {
                        resetRadioGroup()
                        binding.checkRadioGroup.clearCheck()
                    }
                }
            }
        })
    }

    private fun resetRadioGroup() {
        for (i in 0 until binding.checkRadioGroup.childCount) {
            val radioButton = binding.checkRadioGroup.getChildAt(i) as RadioButton
            radioButton.isChecked = false
        }
    }

    private fun resetSeek() {
        binding.checkSeekBar.progress = 0
    }

    private fun enableTools() {
        with(binding) {
            checkCheckBoxRadio.isEnabled = true
            checkCheckBoxSeek.isEnabled = true
        }
    }

    private fun isEnabledRadioGroup(): Boolean {
        if (!binding.checkRadioGroup.getChildAt(0).isEnabled) return false
        return true
    }

    private fun enableRadioGroup() {
        for (i in 0 until binding.checkRadioGroup.childCount) {
            binding.checkRadioGroup.getChildAt(i).isEnabled = true
        }
    }

    private fun disableRadioGroup() {
        for (i in 0 until binding.checkRadioGroup.childCount) {
            binding.checkRadioGroup.getChildAt(i).isEnabled = false
        }
    }

    private fun disableAll() = with(binding) {
        disableRadioGroup()
        checkCheckBoxRadio.isEnabled = false
        checkCheckBoxRadio.isChecked = false
        checkCheckBoxSeek.isEnabled = false
        checkCheckBoxSeek.isChecked = false
        checkSeekBar.isEnabled = false
    }
}