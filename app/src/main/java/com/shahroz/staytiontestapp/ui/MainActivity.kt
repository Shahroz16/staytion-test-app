package com.shahroz.staytiontestapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.slider.Slider
import com.shahroz.staytiontestapp.databinding.ActivityMainBinding
import com.shahroz.staytiontestapp.model.Request
import com.shahroz.staytiontestapp.model.ServiceData
import com.shahroz.staytiontestapp.ui.DeviceStates.CLIMATE_SERVICE_STATE_OFF
import com.shahroz.staytiontestapp.ui.DeviceStates.CLIMATE_SERVICE_STATE_ON
import com.shahroz.staytiontestapp.ui.DeviceStates.CLIMATE_STATE_ON
import com.shahroz.staytiontestapp.ui.DeviceStates.LIGHT_SERVICE_STATE_OFF
import com.shahroz.staytiontestapp.ui.DeviceStates.LIGHT_SERVICE_STATE_ON
import com.shahroz.staytiontestapp.ui.DeviceStates.LIGHT_STATE_ON
import com.shahroz.staytiontestapp.ui.DeviceStates.LOCK_SERVICE_STATE_LOCK
import com.shahroz.staytiontestapp.ui.DeviceStates.LOCK_SERVICE_STATE_UNLOCK
import com.shahroz.staytiontestapp.ui.DeviceStates.LOCK_STATE_LOCK
import com.shahroz.staytiontestapp.ui.adapter.DeviceAdapter
import com.shahroz.staytiontestapp.ui.adapter.DeviceAdapterListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.readDevicesData()

        val deviceAdapter = DeviceAdapter(
            object : DeviceAdapterListener {
                override fun onSwitchLightClick(view: View, position: Int) {

                    val eID = viewModel.allDevices[position].entity_id
                    val state = viewModel.allDevices[position].state

                    val request = Request(
                        id = "19",
                        type = "call_service",
                        domain = "light",
                        service = if (state == LIGHT_STATE_ON) LIGHT_SERVICE_STATE_OFF else LIGHT_SERVICE_STATE_ON,
                        service_data = ServiceData(entity_id = eID)
                    )
                    viewModel.webSocketSend(request)
                }

                override fun onLightSliderProgress(slider: Slider, position: Int) {
                    val eID = viewModel.allDevices[position].entity_id
                    val state = viewModel.allDevices[position].state

                    val request = Request(
                        id = "19",
                        type = "call_service",
                        domain = "light",
                        service = "turn_on",
                        service_data = ServiceData(
                            entity_id = eID,
                            brightness = "${slider.value.toInt()}"
                        )
                    )

                    viewModel.webSocketSend(request)

                }

                override fun onClimateSliderProgress(slider: Slider, position: Int) {
                    val eID = viewModel.allDevices[position].entity_id

                    val request = Request(
                        id = "19",
                        type = "call_service",
                        domain = "climate",
                        service = "set_temperature",
                        service_data = ServiceData(entity_id = eID, temperature = "${slider.value}")
                    )

                    viewModel.webSocketSend(request)

                }

                override fun onSwitchClimateClick(view: View, position: Int) {
                    val eID = viewModel.allDevices[position].entity_id
                    val state = viewModel.allDevices[position].state

                    val request = Request(
                        id = "19",
                        type = "call_service",
                        domain = "climate",
                        service = if (state == CLIMATE_STATE_ON) CLIMATE_SERVICE_STATE_OFF else CLIMATE_SERVICE_STATE_ON,
                        service_data = ServiceData(entity_id = eID)
                    )
                    viewModel.webSocketSend(request)

                }


                override fun onSwitchLockClick(view: View, position: Int) {

                    val eID = viewModel.allDevices[position].entity_id
                    val state = viewModel.allDevices[position].state

                    val request = Request(
                        id = "19",
                        type = "call_service",
                        domain = "lock",
                        service = if (state == LOCK_STATE_LOCK) LOCK_SERVICE_STATE_UNLOCK else LOCK_SERVICE_STATE_LOCK,
                        service_data = ServiceData(entity_id = eID)
                    )
                    viewModel.webSocketSend(request)
                }
            }
        )

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = deviceAdapter

        viewModel.webSocketIncomingData.observe(this) {
            it?.let {
                viewModel.allDevices = it.result.filter { device ->
                    device.entity_id != "persistent_notification.http_login"
                }.shuffled()
                deviceAdapter.setDeviceModelList(viewModel.allDevices)
            }
        }

        viewModel.webSocketIncomingStateResponse.observe(this) {
            it?.let { stateResponse ->
                viewModel.updateDevice(stateResponse)
                deviceAdapter.setDeviceModelList(viewModel.allDevices)
            }
        }

        viewModel.setStateChangeListener()
    }
}
