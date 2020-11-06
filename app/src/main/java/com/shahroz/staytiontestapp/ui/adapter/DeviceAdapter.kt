package com.shahroz.staytiontestapp.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shahroz.staytiontestapp.ui.DeviceDomains
import com.shahroz.staytiontestapp.ui.DeviceStates
import com.shahroz.staytiontestapp.ui.DeviceTypes.*
import com.shahroz.staytiontestapp.ui.viewHolders.ClimateViewHolder
import com.shahroz.staytiontestapp.ui.viewHolders.DeviceViewHolder
import com.arbisoft.chatio.ui.viewHolders.LightViewHolder
import com.arbisoft.chatio.ui.viewHolders.LockViewHolder
import com.shahroz.staytiontestapp.R
import com.shahroz.staytiontestapp.model.Device

class DeviceAdapter(private val deviceAdapterListener: DeviceAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var modelList: List<Device> = listOf()

    override fun getItemViewType(position: Int): Int {
        return when (modelList[position].entity_id.split(".")[0]) {
            DeviceDomains.LOCK -> LOCK.ordinal
            DeviceDomains.LIGHT -> LIGHT.ordinal
            DeviceDomains.CLIMATE -> CLIMATE.ordinal
            else -> OTHER.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layout = getLayoutFromViewType(viewType)
        val layoutView = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return getViewHolderFromViewType(viewType, layoutView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            LOCK.ordinal -> initializeLockViewHolder(holder, position)
            LIGHT.ordinal -> initializeLightViewHolder(holder, position)
            CLIMATE.ordinal -> initializeClimateViewHolder(holder, position)
            else -> initializeViewHolder(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    private fun getLayoutFromViewType(viewType: Int): Int {
        return when (viewType) {
            LOCK.ordinal -> R.layout.lock_device_item
            LIGHT.ordinal -> R.layout.light_device_item
            CLIMATE.ordinal -> R.layout.climate_device_item
            else -> R.layout.device_item
        }
    }

    private fun getViewHolderFromViewType(
        viewType: Int,
        layoutView: View
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            LOCK.ordinal -> LockViewHolder(layoutView, deviceAdapterListener)
            LIGHT.ordinal -> LightViewHolder(layoutView, deviceAdapterListener)
            CLIMATE.ordinal -> ClimateViewHolder(layoutView, deviceAdapterListener)
            else -> DeviceViewHolder(layoutView)
        }
    }

    private fun initializeViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val vholder = holder as DeviceViewHolder
        vholder.label.text = modelList[position].attributes.friendly_name
        vholder.amount.text = modelList[position].entity_id
    }

    @SuppressLint("NewApi")
    private fun initializeClimateViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val vholder = holder as ClimateViewHolder
        vholder.label.text = modelList[position].attributes.friendly_name
        vholder.amount.text = modelList[position].entity_id
        vholder.switch.text = modelList[position].state.capitalize()
        vholder.switch.isChecked = modelList[position].state == DeviceStates.CLIMATE_STATE_ON
        vholder.progress.isEnabled = vholder.switch.isChecked
        vholder.progress.valueFrom = modelList[position].attributes.min_temp ?: 0F
        vholder.progress.valueTo = modelList[position].attributes.max_temp ?: 0F
        vholder.progress.value = modelList[position].attributes.temperature ?: 0F
    }

    @SuppressLint("NewApi")
    private fun initializeLightViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val vholder = holder as LightViewHolder
        vholder.label.text = modelList[position].attributes.friendly_name
        vholder.amount.text = modelList[position].entity_id
        vholder.switch.text = modelList[position].state.capitalize()
        vholder.switch.isChecked = modelList[position].state == DeviceStates.LIGHT_STATE_ON
        vholder.progress.isEnabled = vholder.switch.isChecked
        vholder.progress.valueFrom = 0F
        vholder.progress.valueTo = 255F

        vholder.progress.value = modelList[position].attributes.brightness?.toFloat() ?: 1F
    }

    private fun initializeLockViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        val vholder = holder as LockViewHolder
        vholder.label.text = modelList[position].attributes.friendly_name
        vholder.amount.text = modelList[position].entity_id
        vholder.switch.isChecked = modelList[position].state == DeviceStates.LOCK_STATE_LOCK
        vholder.switch.text = modelList[position].state.capitalize()
    }

    fun setDeviceModelList(modelList: List<Device>) {
        this.modelList = modelList
        notifyDataSetChanged()
    }

    fun resetModelList() {
        this.modelList = listOf()
        notifyDataSetChanged()
    }
}
