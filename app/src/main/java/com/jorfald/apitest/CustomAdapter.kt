package com.jorfald.apitest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(
    var dataSet: List<HolderClass> = listOf(),
    val cellClickCallback: (Int) -> Unit,
    val cellSmallClickCallback: (Int) -> Unit,
) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val container: ConstraintLayout = view.findViewById(R.id.container)
        val textView: TextView = view.findViewById(R.id.cell_text)
        var smallTextView: TextView = view.findViewById(R.id.cell_small_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layout =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.custom_cell_item, parent, false)

        return CustomViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val data: HolderClass = dataSet[position]

        holder.textView.text = data.text
        holder.smallTextView.text = data.number.toString()

        holder.container.setOnClickListener {
            cellClickCallback(position)
        }

        holder.smallTextView.setOnClickListener {
            cellSmallClickCallback(position)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
