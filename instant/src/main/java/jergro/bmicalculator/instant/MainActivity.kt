package jergro.bmicalculator.instant

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var weightEditText: EditText
    lateinit var weightUnitButton: TextView
    lateinit var heightEditText: EditText
    lateinit var heightUnitButton: TextView

    var weightUnit = WeightUnit.KG
    var heightUnit = HeightUnit.M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        setupOnClicks()
    }

    private fun findViews(){
        weightEditText = findViewById(R.id.weight_edit_text)
        weightUnitButton = findViewById(R.id.weight_unit_button)
        heightEditText = findViewById(R.id.height_edit_text)
        heightUnitButton = findViewById(R.id.height_unit_button)
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
        }

        heightUnitButton.setOnClickListener {
            if(heightUnit == HeightUnit.M){
                heightUnit = HeightUnit.IN
                heightUnitButton.text = "in"
            } else {
                heightUnit = HeightUnit.M
                heightUnitButton.text = "m"
            }
        }
    }

}
