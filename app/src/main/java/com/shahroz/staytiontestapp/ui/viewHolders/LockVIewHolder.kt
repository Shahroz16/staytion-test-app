package com.arbisoft.chatio.ui.viewHolders

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahroz.staytiontestapp.ui.adapter.DeviceAdapterListener
import com.google.android.material.switchmaterial.SwitchMaterial
import com.shahroz.staytiontestapp.R

class LockViewHolder(itemView: View, deviceAdapterListener: DeviceAdapterListener) :
    RecyclerView.ViewHolder(itemView) {
    var label: TextView = itemView.findViewById(R.id.lock_label)
    var amount: TextView = itemView.findViewById(R.id.lock_amount)
    var switch: SwitchMaterial = itemView.findViewById(R.id.lock_switch2)

    init {
        switch.setOnClickListener {
            deviceAdapterListener.onSwitchLockClick(it, adapterPosition)
        }
        itemView.setBackgroundColor(Color.parseColor("#FF80DFB0"))
    }
}
