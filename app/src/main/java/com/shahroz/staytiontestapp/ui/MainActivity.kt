package com.shahroz.staytiontestapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.shahroz.staytiontestapp.Request
import com.shahroz.staytiontestapp.ServiceData
import com.shahroz.staytiontestapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.webSocketIncomingData.observe(this) {
            binding.output.text = "All " + it?.type
        }

        viewModel.webSocketIncomingActionResponse.observe(this) {
            it?.let {
                binding.output.text = it.type
            }

            binding.output.text = "Single " + it?.result
        }

        binding.button.setOnClickListener {
            val request = Request(id = "3", type = "get_states")
            viewModel.webSocketSend(request)
        }

        binding.start.setOnClickListener {
            val request = Request(
                id = "19",
                type = "call_service",
                domain = "lock",
                service = "lock",
                service_data = ServiceData(entity_id = "lock.front_door")
            )
            viewModel.webSocketSend(request)
        }

        binding.button2.setOnClickListener {
            val request = Request(
                id = "1",
                type = "subscribe_events",
                event_type = "state_changed"
            )
            viewModel.webSocketSend(request)
        }

    }
}
