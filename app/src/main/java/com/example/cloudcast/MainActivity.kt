package com.example.cloudcast

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.cloudcast.databinding.ActivityMainBinding
import com.example.cloudcast.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val apiKey = "1562e4284c65bf9efcb7b0dd319a9663"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val city = binding.cityEditText.text.toString()
            weatherViewModel.fetchWeather(city, apiKey)
        }

        weatherViewModel.weather.observe(this, Observer { weather ->
            binding.tempTextView.text = "Temperature: ${weather.main.temp}°C"
            binding.descriptionTextView.text = "Condition: ${weather.weather[0].description}"
        })
    }
}
