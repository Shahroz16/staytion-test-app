package com.arbisoft.chatio.ui.viewHolders

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahroz.staytiontestapp.ui.adapter.DeviceAdapterListener
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.shahroz.staytiontestapp.R

class LightViewHolder(itemView: View, deviceAdapterListener: DeviceAdapterListener) :
    RecyclerView.ViewHolder(itemView) {
    var label: TextView = itemView.findViewById(R.id.light_label)
    var amount: TextView = itemView.findViewById(R.id.light_amount)
    var switch: SwitchMaterial = itemView.findViewById(R.id.light_switch2)
    var progress: Slider = itemView.findViewById(R.id.light_progressBar)

    init {
        itemView.setBackgroundColor(Color.parseColor("#d4e157"))
        switch.setOnClickListener {
            deviceAdapterListener.onSwitchLightClick(it, adapterPosition)
        }
        progress.addOnSliderTouchListener(
            object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    deviceAdapterListener.onLightSliderProgress(slider, adapterPosition)
                }
            }
        )
    }
}
