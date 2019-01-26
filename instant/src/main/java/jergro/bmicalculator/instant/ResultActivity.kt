package jergro.bmicalculator.instant

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlinx.android.synthetic.main.activity_result.*

@SuppressLint("SetTextI18n")
class ResultActivity : AppCompatActivity() {
    var height: Double = 0.0
    var weight: Double = 0.0
    var bmi: Double = 0.0
    var displayedBmi = 0.0
    var arrowHeight = 0
    var rootHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        getIntentData()
        showWeightAndHeight()
        calculateBMI()
        startResultAnimation()
        arrow.post {
            arrowHeight = arrow.height
            rootHeight = (arrow.parent as View).height
        }
    }

    private fun startResultAnimation() {
        val anim: ValueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(4000)
        anim.interpolator = DecelerateInterpolator()
        anim.addUpdateListener {
            val progress = it.animatedFraction
            displayedBmi = bmi * progress
            updateResult()
            updateArrowPosition(progress)
        }
        anim.start()
    }

    private fun updateResult() {
        val category = Category.getCategoryByBMI(displayedBmi)
        bmi_text.text = "%.01f".format(displayedBmi)
        result_description.text = category.name + "\n(%.1f - %.1f)".format(category.start, category.end)
        result_description.setTextColor(category.color)
    }

    private fun updateArrowPosition(progress: Float) {
        val targetProgress = Category.getArrowProgressByBMI(bmi)
        var translation = ((rootHeight * targetProgress * progress) - (arrowHeight / 2)).toFloat()
        translation = Math.min(translation, rootHeight - arrowHeight.toFloat())
        translation = Math.max(translation, 0f)

        arrow.translationY = translation
    }

    private fun getIntentData() {
        height = intent.getDoubleExtra("height", 0.0)
        weight = intent.getDoubleExtra("weight", 0.0)
    }

    private fun showWeightAndHeight() {
        weight_and_height.text = "Weight: %.1fkg\nHeight: %.1fm".format(weight, height)
    }

    private fun calculateBMI() {
        bmi = weight / (height * height / 100 / 100)
    }
}
