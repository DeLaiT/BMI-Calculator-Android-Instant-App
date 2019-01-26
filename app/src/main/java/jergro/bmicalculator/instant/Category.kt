package jergro.bmicalculator.instant

import android.support.annotation.ColorInt


class Category(val name: String, val start: Double, val end: Double, @ColorInt val color: Int) {

    fun isInRange(value: Double): Boolean {
        return (start..end).contains(value)
    }

    fun getProgress(bmi: Double) : Double {
        return (bmi-start)/(end-start)
    }

    companion object {
        val categories = arrayOf(
            Category("Very severely underweight", 0.0, 15.0, 0xffD50000.toInt()),
            Category("Severely underweight", 15.0, 16.0, 0xffFF6D00.toInt()),
            Category("Underweight", 16.0, 18.5, 0xffFFD600.toInt()),
            Category("Normal", 18.5, 25.0, 0xff00C853.toInt()),
            Category("Overweight", 25.0, 30.0, 0xffFFD600.toInt()),
            Category("Obese Class I\n(Moderately obese)", 30.0, 35.0, 0xffFF6D00.toInt()),
            Category("Obese Class II\n(Severely obese)", 35.0, 40.0, 0xffD50000.toInt()),
            Category("Obese Class III\n(Very severely obese)", 40.0, 45.0, 0xffD50000.toInt()),
            Category("Obese Class IV\n(Morbidly Obese)", 45.0, 50.0, 0xffD50000.toInt()),
            Category("Obese Class V\n(Super Obese)", 50.0, 60.0, 0xffD50000.toInt()),
            Category("Obese Class VI\n(Hyper Obese)", 60.0, 100.0, 0xffD50000.toInt())
        )

        fun getCategoryByBMI(bmi: Double) : Category {
            categories.forEach {
                if (it.isInRange(bmi))
                    return it
            }
            return categories.last()
        }

        fun getArrowProgressByBMI(bmi: Double) : Double {
            for(i in 0 until categories.size) {
                if(categories[i].isInRange(bmi)) {
                    return Math.min((categories[i].getProgress(bmi) + i) / 7, 1.0)
                }
            }
            return 1.0
        }
    }
}