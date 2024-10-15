package com.mehmetakiftutal.calculatorkotlin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var button_C: Button
    lateinit var button_open: Button
    lateinit var button_close: Button
    lateinit var button_div: Button
    lateinit var button_seven: Button
    lateinit var button_eight: Button
    lateinit var button_nine: Button
    lateinit var button_multiply: Button
    lateinit var button_four: Button
    lateinit var button_five: Button
    lateinit var button_six: Button
    lateinit var button_minus: Button
    lateinit var button_one: Button
    lateinit var button_two: Button
    lateinit var button_three: Button
    lateinit var button_sum: Button
    lateinit var button_ac: Button
    lateinit var button_zero: Button
    lateinit var button_dot: Button
    lateinit var button_equal: Button

    lateinit var textViewResult: TextView
    lateinit var textViewSolution: TextView

    var input = ""
    var operator = ""
    var firstNumber = 0.0
    var secondNumber = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        button_C = findViewById(R.id.button_C)
        button_open = findViewById(R.id.button_open)
        button_close = findViewById(R.id.button_close)
        button_div = findViewById(R.id.button_div)
        button_seven = findViewById(R.id.button_seven)
        button_eight = findViewById(R.id.button_eight)
        button_nine = findViewById(R.id.button_nine)
        button_multiply = findViewById(R.id.button_multiply)
        button_four = findViewById(R.id.button_four)
        button_five = findViewById(R.id.button_five)
        button_six = findViewById(R.id.button_six)
        button_minus = findViewById(R.id.button_minus)
        button_one = findViewById(R.id.button_one)
        button_two = findViewById(R.id.button_two)
        button_three = findViewById(R.id.button_three)
        button_sum = findViewById(R.id.button_sum)
        button_ac = findViewById(R.id.button_ac)
        button_zero = findViewById(R.id.button_zero)
        button_dot = findViewById(R.id.button_dot)
        button_equal = findViewById(R.id.button_equal)
        textViewSolution = findViewById(R.id.solution)
        textViewResult = findViewById(R.id.result)

        fun onOperatorClick(selectedOperator: String) {
            operator = selectedOperator
            firstNumber = input.toDoubleOrNull() ?: 0.0
            input = "" // İkinci sayıyı almak için inputu sıfırla
            textViewSolution.text = "$firstNumber $operator"
        }

        fun calculateResult() {
            secondNumber = input.toDoubleOrNull() ?: 0.0

            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> {
                    if (secondNumber != 0.0) {
                        firstNumber / secondNumber
                    } else {
                        "Error: Division by Zero"
                    }
                }

                else -> null
            }

            if (result != null) {

                textViewResult.text = result.toString() // Sonucu textViewResult'a yazdır
                textViewSolution.text =
                    "$firstNumber $operator $secondNumber =" // Yapılan işlemi textViewSolution'a yazdır
                input = ""
                operator = ""
            } else {
                textViewResult.text = firstNumber.toString()
            }


            button_sum.setOnClickListener {

                onOperatorClick("+")

            }
            fun onNumberClick(number: String) {
                input += number
                textViewSolution.text = input
            }

            button_minus.setOnClickListener {
                onOperatorClick("-")
            }

            button_multiply.setOnClickListener {
                onOperatorClick("*")
            }

            button_div.setOnClickListener {
                onOperatorClick("/")
            }

            button_open.setOnClickListener {
                input += "("
                textViewSolution.text = input
            }

            button_close.setOnClickListener {
                input += ")"
                textViewSolution.text = input
            }

            button_equal.setOnClickListener {
                calculateResult()
            }



            button_one.setOnClickListener { onNumberClick("1") }
            button_two.setOnClickListener { onNumberClick("2") }
            button_three.setOnClickListener { onNumberClick("3") }
            button_four.setOnClickListener { onNumberClick("4") }
            button_five.setOnClickListener { onNumberClick("5") }
            button_six.setOnClickListener { onNumberClick("6") }
            button_seven.setOnClickListener { onNumberClick("7") }
            button_eight.setOnClickListener { onNumberClick("8") }
            button_nine.setOnClickListener { onNumberClick("9") }
            button_zero.setOnClickListener { onNumberClick("0") }
            button_dot.setOnClickListener { onNumberClick(".") }

            button_ac.setOnClickListener {
                if (input.isNotEmpty()) {
                    input = input.dropLast(1) // Son karakteri sil
                    textViewSolution.text = input // Ekranda güncellenen değeri göster
                }
            }

            button_C.setOnClickListener{
                input = "" // Giriş alanını sıfırla
                operator = "" // Operatörü sıfırla
                firstNumber = 0.0 // İlk sayıyı sıfırla
                secondNumber = 0.0 // İkinci sayıyı sıfırla
                textViewSolution.text = "" // Çözüm alanını temizle
                textViewResult.text = ""
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }


        }
    }
}

