package jergro.bmicalculator.instant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class ResultActivity : AppCompatActivity() {
    var height: Double = 0.0
    var weight: Double = 0.0
    var bmi: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        getIntentData()
        calculateBMI()
    }

    private fun getIntentData(){
        height = intent.getDoubleExtra("height", 0.0)
        weight = intent.getDoubleExtra("weight", 0.0)
    }

    private fun calculateBMI(){
        bmi = weight / (height*height)
        Log.d("XD", ""+bmi)
    }
}
