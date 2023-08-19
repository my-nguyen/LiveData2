package com.nguyen.livedata2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.nguyen.livedata2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            if (binding.input.text.isEmpty() || binding.input.text.length < 4)
                Toast.makeText(this, "Invalid number", Toast.LENGTH_LONG).show()
            else {
                viewModel.timerValue.value = binding.input.text.toString().toLong()
                viewModel.startTimer()
            }
        }
        binding.stop.setOnClickListener {
            binding.count.text = "0"
            viewModel.stopTimer()
        }

        viewModel.seconds.observe(this) { seconds ->
            binding.count.text = seconds.toString()
        }
        viewModel.finished.observe(this) { finished ->
            if (finished)
                Toast.makeText(this, "Finished", Toast.LENGTH_LONG).show()
        }
    }
}