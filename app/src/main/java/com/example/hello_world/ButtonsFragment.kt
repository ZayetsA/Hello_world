package com.example.hello_world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hello_world.adapter.ButtonAdapter
import com.example.hello_world.databinding.FragmentButtonsBinding
import com.example.hello_world.model.ButtonModel
import com.ibm.icu.text.RuleBasedNumberFormat
import java.util.*

class ButtonsFragment : Fragment() {
    private var listOfButtons = ArrayList<ButtonModel>()
    private lateinit var _binding: FragmentButtonsBinding
    private val binding get() = _binding
    private lateinit var adapter: ButtonAdapter
    private var lastContactId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        buildButtonsRV()
        binding.addButton.setOnClickListener {
            addButton()
            adapter.notifyDataSetChanged()
        }
    }

    private fun buildButtonsRV() {
        addButton()
        adapter = ButtonAdapter(listOfButtons)
        binding.rvButtons.adapter = adapter
        binding.rvButtons.layoutManager = LinearLayoutManager(context)
    }

    private fun addButton() {
        listOfButtons.add(
            ButtonModel(
                getString(R.string.rv_item_name) + " " + convertIntoWords(lastContactId.toDouble())
            )
        )
        ++lastContactId
    }

    private fun convertIntoWords(str: Double): String {
        val ruleBasedNumberFormat =
            RuleBasedNumberFormat(getCurrentLocale(), RuleBasedNumberFormat.SPELLOUT)
        return ruleBasedNumberFormat.format(str)
    }

    private fun getCurrentLocale(): Locale =
        ConfigurationCompat.getLocales(resources.configuration)[0]
}