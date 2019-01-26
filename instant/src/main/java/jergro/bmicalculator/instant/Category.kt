package jergro.bmicalculator.instant

class Category(val name: String, val start: Double, val end: Double) {

    fun isInRange(value: Double): Boolean {
        return (start..end).contains(value)
    }

    companion object {
        val categories = arrayOf(
            Category("Very severely underweight", 0.0, 15.0),
            Category("Severely underweight", 15.0, 16.0),
            Category("Underweight", 16.0, 18.5),
            Category("Normal", 18.5, 25.0),
            Category("Overweight", 25.0, 30.0),
            Category("Obese Class I (Moderately obese)", 30.0, 35.0),
            Category("Obese Class II (Severely obese)", 35.0, 40.0),
            Category("Obese Class III (Very severely obese)", 40.0, 45.0),
            Category("Obese Class IV (Morbidly Obese)", 45.0, 50.0),
            Category("Obese Class V (Super Obese)", 50.0, 60.0),
            Category("Obese Class VI (Hyper Obese)", 60.0, 100000000.0)
        )

        fun getCategoryByBMI(bmi: Double) : Category {
            categories.forEach {
                if (it.isInRange(bmi))
                    return it
            }
            return categories.last()
        }
    }
}