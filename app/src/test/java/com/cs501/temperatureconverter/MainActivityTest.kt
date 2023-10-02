package com.cs501.temperatureconverter

import org.junit.Test
import org.junit.Assert.*

class MainActivityTest {

    @Test
    fun testConvertFahrenheitToCelsius() {
        val mainActivity = MainActivity()
        val celsiusValue = mainActivity.convertFahrenheitToCelsius(32)
        assertEquals(0, celsiusValue)
    }
}