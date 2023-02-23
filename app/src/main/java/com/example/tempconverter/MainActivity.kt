package com.example.tempconverter

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner = findViewById<Spinner>(R.id.spinner)

        val btnConvert = findViewById<View>(R.id.button) as Button
        val tempList: Array<String> = resources.getStringArray(R.array.temperatures)
        //val tempList = arrayOf<String>("Fahrenheit", "Kelvin")
        val tempListAdapter = ArrayAdapter(this, R.layout.spinner_item, tempList)
        tempListAdapter.setDropDownViewResource(R.layout.spinner_item)

        with(spinner) {
            adapter = tempListAdapter
        }

        btnConvert.setOnClickListener {
            convertTemp(spinner)
        }

    }

    private fun convertTemp(spinner: Spinner) {
        try {

            val inputTemp = findViewById<EditText>(R.id.input_temp)
            val spinnerValue = spinner.selectedItem.toString()
            val txtResult = findViewById<EditText>(R.id.result)
            val temp: Double = inputTemp.text.toString().trim().toDouble()

            if (inputTemp.text.isNotEmpty()) {

                val result = when (spinnerValue) {
                    "Fahrenheit" -> fahrenheit(temp).toString().trim() + " ° Fahrenheit"
                    "Kelvin" -> kelvin(temp).toString().trim() + " Kelvin"
                    else -> "Enter a valid temperature value"
                }

                txtResult.setText(result)

            } else {
                Toast.makeText(this, "Enter temperature you want to convert", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: java.lang.NumberFormatException) {
            Toast.makeText(this, "Enter a valid temperature value", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fahrenheit(celsius: Double) = celsius * 1.8 + 32
    private fun kelvin(celsius: Double) = celsius + 273.15

}