package jergro.bmicalculator.instant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    var height: Double = 0.0
    var weight: Double = 0.0
    var bmi: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        getIntentData()
        calculateBMI()
        displayResult()
    }

    private fun displayResult() {
        bmi_text.text = "%.01f".format(bmi)
    }

    private fun getIntentData(){
        height = intent.getDoubleExtra("height", 0.0)
        weight = intent.getDoubleExtra("weight", 0.0)
    }

    private fun calculateBMI(){
        bmi = weight / (height*height/100/100)
        Log.d("BMI", "%.01f".format(bmi))
    }
}
