package com.cs501.temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val minCelsius = 0
        val maxCelsius = 100
        val minFahrenheit = 32
        val maxFahrenheit = 212

        val seekBarCelsius = findViewById<SeekBar>(R.id.seekBarCelsius)
        val seekBarFahrenheit = findViewById<SeekBar>(R.id.seekBarFahrenheit)

        seekBarCelsius.min = minCelsius
        seekBarCelsius.max = maxCelsius
        seekBarFahrenheit.min = minFahrenheit
        seekBarFahrenheit.max = maxFahrenheit

        seekBarCelsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val fahrenheit = (progress * 9 / 5) + 32
                seekBarFahrenheit.progress = fahrenheit
                updateTemperatureLabels(progress, true)
                showSnackbar(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarFahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val celsius = ((progress - 32) * 5 / 9)
                if (celsius < minCelsius) {
                    seekBarFahrenheit.progress = 32
                    updateTemperatureLabels(0, false)
                } else {
                    seekBarCelsius.progress = celsius
                    updateTemperatureLabels(progress, false)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun showSnackbar(temperature: Int) {
        val snackbarMessage = if (temperature <= 20) {
            "I wish it were warmer."
        }  else if(temperature >= 80){
            "I wish it were colder."
        } else{
            "Temperature is just right!"
        }

        Snackbar.make(findViewById(android.R.id.content), snackbarMessage, Snackbar.LENGTH_SHORT).show()
    }

    private fun updateTemperatureLabels(value: Int, isCelsius: Boolean) {
        val celsiusLabel = findViewById<TextView>(R.id.celsiusTemperature)
        val fahrenheitLabel = findViewById<TextView>(R.id.fahrenheitTemperature)
        val unitCelsius = "°C"
        val unitFahrenheit = "°F"

        if (isCelsius) {
            celsiusLabel.text = "$value$unitCelsius"
            val fahrenheit = (value * 9 / 5) + 32
            fahrenheitLabel.text = "$fahrenheit$unitFahrenheit"
        } else {
            val celsius = ((value - 32) * 5 / 9)
            celsiusLabel.text = "$celsius$unitCelsius"
            fahrenheitLabel.text = "$value$unitFahrenheit"
        }
    }

    fun convertFahrenheitToCelsius(f: Int): Int {
        return ((f - 32) * 5 / 9)
    }
}