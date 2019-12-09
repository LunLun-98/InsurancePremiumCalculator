package com.example.insurancepremiumcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var myData: PremiumModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myData = ViewModelProviders.of(this).get(PremiumModel::class.java)

        display()

        buttonCal.setOnClickListener(){

            myData.premiumAmount = getPremium()
            display()
        }

        buttonReset.setOnClickListener() {
            spinnerAge.setSelection(0)
            radioGroup.clearCheck()
            checkSmoker.setChecked(false)
            myData.premiumAmount = 0.0
            textTotalPremium.text = 0.0.toString()
        }
    }

    fun getPremium():Double {

        return when(spinnerAge.selectedItemPosition){
            0 -> 60.00
            1 -> 90.00 +
                    (if (radioMale.isChecked) 50.00 else 0.0) + (if (checkSmoker.isChecked) 100.00 else 0.0)
            2 -> 90.00 +
                    (if (radioMale.isChecked) 100.00 else 0.0) + (if (checkSmoker.isChecked) 150.00 else 0.0)
            3 -> 120.00 +
                    (if (radioMale.isChecked) 150.00 else 0.0) + (if (checkSmoker.isChecked) 200.00 else 0.0)
            4 -> 150.00 +
                    (if (radioMale.isChecked) 200.00 else 0.0) + (if (checkSmoker.isChecked) 250.00 else 0.0)
            else -> 150.00 +
                    (if (radioMale.isChecked) 200.00 else 0.0) + (if (checkSmoker.isChecked) 300.00 else 0.0)
        }
    }

    fun display() {
        if (myData.premiumAmount != 0.0)
        textTotalPremium.text = myData.premiumAmount.toString()
    }
}
