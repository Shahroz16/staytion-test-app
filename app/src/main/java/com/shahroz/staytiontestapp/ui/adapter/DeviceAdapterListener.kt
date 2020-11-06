package com.shahroz.staytiontestapp.ui.adapter

import android.view.View
import com.google.android.material.slider.Slider

interface DeviceAdapterListener {
    fun onSwitchLockClick(view: View, position: Int)
    fun onSwitchLightClick(view: View, position: Int)
    fun onSwitchClimateClick(view: View, position: Int)
    fun onLightSliderProgress(slider: Slider, position: Int)
    fun onClimateSliderProgress(slider: Slider, position: Int) {
    }
}
