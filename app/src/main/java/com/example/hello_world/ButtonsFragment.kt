package com.example.hello_world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hello_world.adapter.ButtonsAdapter
import com.example.hello_world.databinding.FragmentButtonsBinding
import com.example.hello_world.model.ButtonModel

class ButtonsFragment : Fragment() {
    private lateinit var buttons: ArrayList<ButtonModel>
    private var _binding: FragmentButtonsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ButtonsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentButtonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        builtButtonsRV()
        binding.addButton.setOnClickListener {
            addNewButton()
        }
    }

    private fun builtButtonsRV() {
        val vButtons = binding.rvButtons
        buttons = ButtonModel.addButton()
        adapter = ButtonsAdapter(buttons)
        vButtons.adapter = adapter
        vButtons.layoutManager = LinearLayoutManager(this.context)
    }

    private fun addNewButton() {
        buttons = ButtonModel.addButton()
        adapter.notifyDataSetChanged()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}