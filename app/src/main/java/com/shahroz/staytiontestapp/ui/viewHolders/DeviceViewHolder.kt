package com.shahroz.staytiontestapp.ui.viewHolders

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahroz.staytiontestapp.R

class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var label: TextView = itemView.findViewById(R.id.label)
    var amount: TextView = itemView.findViewById(R.id.amount)

    init {
        itemView.setBackgroundColor(Color.parseColor("#60c4ff"))
    }
}
