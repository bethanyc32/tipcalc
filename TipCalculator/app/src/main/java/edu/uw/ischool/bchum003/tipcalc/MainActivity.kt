package edu.uw.ischool.bchum003.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var serviceChargeAmount: EditText
    private lateinit var tipButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceChargeAmount = findViewById(R.id.serviceChargeAmount)
        tipButton = findViewById(R.id.tipButton)

        serviceChargeAmount.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                tipButton.isEnabled = input.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    fun calculateTip(view: View) {
        val input = serviceChargeAmount.text.toString()
        val amount = input.toDoubleOrNull()

        if (amount != null) {
            val tip = amount * 0.15
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            val message = "Tip: $formattedTip"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Invalid input", Toast.LENGTH_LONG).show()
        }
    }
}