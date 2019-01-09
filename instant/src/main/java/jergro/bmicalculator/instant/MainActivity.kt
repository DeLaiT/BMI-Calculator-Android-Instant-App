package jergro.bmicalculator.instant

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.widget.EditText
import android.widget.TextView

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    private lateinit var weightEditText: EditText
    private lateinit var weightUnitButton: TextView
    private lateinit var heightEditText: EditText
    private lateinit var heightUnitButton: TextView
    private lateinit var submitButton: View

    private var weightUnit = WeightUnit.KG
    private var heightUnit = HeightUnit.CM
    private var weight: Double = 0.0
    private var height: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        setupOnClicks()
        setupEditTextListeners()
    }

    private fun findViews(){
        weightEditText = findViewById(R.id.weight_edit_text)
        weightUnitButton = findViewById(R.id.weight_unit_button)
        heightEditText = findViewById(R.id.height_edit_text)
        heightUnitButton = findViewById(R.id.height_unit_button)
        submitButton = findViewById(R.id.submit_button)
    }

    private fun setupOnClicks(){
        weightUnitButton.setOnClickListener {
            if(weightUnit == WeightUnit.KG){
                weightUnit = WeightUnit.LB
                weightUnitButton.text = "lb"
            } else {
                weightUnit = WeightUnit.KG
                weightUnitButton.text = "kg"
            }
            onTextOrUnitChanged()
        }

        heightUnitButton.setOnClickListener {
            if(heightUnit == HeightUnit.CM){
                heightUnit = HeightUnit.IN
                heightUnitButton.text = "in"
            } else {
                heightUnit = HeightUnit.CM
                heightUnitButton.text = "cm"
            }
            onTextOrUnitChanged()
        }

        submitButton.setOnClickListener {
            val intent =  Intent(this, ResultActivity::class.java)
            intent.putExtra("weight", weight)
            intent.putExtra("height", height)
            startActivity(intent)
        }
    }

    private fun setupEditTextListeners() {
        weightEditText.onTextChanged { onTextOrUnitChanged() }
        heightEditText.onTextChanged { onTextOrUnitChanged() }
    }

    private fun onTextOrUnitChanged(){
        if(areInputsValid()){
            submitButton.visibility = VISIBLE
            saveInput()
        } else
            submitButton.visibility = INVISIBLE
    }

    private fun saveInput() {
        weight = weightEditText.text.toString().toDouble()
        height = heightEditText.text.toString().toDouble()

        if(weightUnit == WeightUnit.LB)
            weight *= 0.45359237
        if(heightUnit == HeightUnit.IN)
            height *= 2.54
    }

    private fun areInputsValid(): Boolean{
        return !weightEditText.text.isNullOrEmpty() && !heightEditText.text.isNullOrEmpty()
    }
}
