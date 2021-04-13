package com.example.hello.world.app.ui.buttons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.ConfigurationCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.world.app.R
import com.example.hello.world.app.databinding.FragmentButtonsBinding
import com.example.hello.world.app.ui.buttons.adapter.ButtonAdapter
import com.example.hello.world.app.ui.buttons.model.ButtonModel
import com.ibm.icu.text.RuleBasedNumberFormat
import java.util.*


class ButtonsFragment : Fragment() {

    private var listOfButtons = ArrayList<ButtonModel>()
    private lateinit var binding: FragmentButtonsBinding
    private lateinit var adapter: ButtonAdapter
    private var lastContactId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolBar()
        setScrollableFeature()
        buildButtonsRV()
        binding.buttonsActionButton.setOnClickListener {
            addButton()
            adapter.notifyDataSetChanged()
        }
    }

    private fun setScrollableFeature() {
        binding.buttonsRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 || dy < 0 && binding.buttonsActionButton.isShown) binding.buttonsActionButton.hide()
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) binding.buttonsActionButton.show()
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun setupToolBar() {
        with(binding.buttonsToolbar) {
            setNavigationIcon(R.drawable.toolbar_back_button_arrow)
            title = getText(R.string.buttons_fragment_title)
            setNavigationOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun buildButtonsRV() {
        addButton()
        adapter = ButtonAdapter(listOfButtons)
        with(binding) {
            buttonsRecycleView.adapter = adapter
            buttonsRecycleView.layoutManager = LinearLayoutManager(context)
        }
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