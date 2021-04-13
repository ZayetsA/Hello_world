package com.example.hello.world.app.ui.buttons.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hello.world.app.databinding.ItemButtonBinding
import com.example.hello.world.app.ui.buttons.model.ButtonModel

class ButtonAdapter(private val itemsList: List<ButtonModel>) :
    RecyclerView.Adapter<ButtonAdapter.ButtonItemHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ButtonItemHolder {
        val itemBinding =
            ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonItemHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ButtonItemHolder, position: Int) {
        val paymentBean: ButtonModel = itemsList[position]
        holder.bind(paymentBean)
    }

    override fun getItemCount(): Int = itemsList.size

    class ButtonItemHolder(private val itemBinding: ItemButtonBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ButtonModel) {
            itemBinding.itemFragment.text = item.name
        }
    }
}