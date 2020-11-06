package com.shahroz.staytiontestapp.ui.viewHolders

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shahroz.staytiontestapp.ui.adapter.DeviceAdapterListener
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.shahroz.staytiontestapp.R

class ClimateViewHolder(itemView: View, deviceAdapterListener: DeviceAdapterListener) : RecyclerView.ViewHolder(itemView) {
    var label: TextView = itemView.findViewById(R.id.climate_label)
    var amount: TextView = itemView.findViewById(R.id.climate_amount)
    var switch: SwitchMaterial = itemView.findViewById(R.id.climate_switch2)
    var progress: Slider = itemView.findViewById(R.id.climate_progressBar)

    init {
        itemView.setBackgroundColor(Color.parseColor("#ffb300"))
        switch.setOnClickListener {
            deviceAdapterListener.onSwitchClimateClick(it, adapterPosition)
        }
        progress.addOnSliderTouchListener(
            object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {
                }

                override fun onStopTrackingTouch(slider: Slider) {
                    deviceAdapterListener.onClimateSliderProgress(slider, adapterPosition)
                }
            }
        )
    }
}
