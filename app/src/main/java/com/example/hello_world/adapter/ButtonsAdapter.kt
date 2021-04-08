package com.example.hello_world.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hello_world.R
import com.example.hello_world.model.ButtonModel

class ButtonsAdapter(private val mButtons: List<ButtonModel>) :
    RecyclerView.Adapter<ButtonsAdapter.ViewHolder>() {
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameButton: TextView = itemView.findViewById(R.id.one_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val buttonView = inflater.inflate(R.layout.item_button, parent, false)
        return ViewHolder(buttonView)
    }

    override fun getItemCount(): Int {
        return mButtons.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val button: ButtonModel = mButtons[position]
        val curButton = holder.nameButton
        curButton.text = button.name
    }
}