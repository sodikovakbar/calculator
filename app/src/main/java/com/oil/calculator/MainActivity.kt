package com.oil.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.component1
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //numbers
        one.setOnClickListener { appendonexpression("1", true) }
        two.setOnClickListener { appendonexpression("2", true) }
        three.setOnClickListener { appendonexpression("3", true) }
        four.setOnClickListener { appendonexpression("4", true) }
        five.setOnClickListener { appendonexpression("5", true) }
        six.setOnClickListener { appendonexpression("6", true) }
        seven.setOnClickListener { appendonexpression("7", true) }
        eight.setOnClickListener { appendonexpression("8", true) }
        nine.setOnClickListener { appendonexpression("9", true) }
        zero.setOnClickListener { appendonexpression("0", true) }
        dot.setOnClickListener { appendonexpression(".", true) }

        //operators
        plus.setOnClickListener { appendonexpression("+", false) }
        minus.setOnClickListener { appendonexpression("-", false) }
        mul.setOnClickListener { appendonexpression("*", false) }
        divide.setOnClickListener { appendonexpression("/", false) }
        open.setOnClickListener { appendonexpression("(", false) }
        close.setOnClickListener { appendonexpression(")", false) }

        clear.setOnClickListener {
            tvexpression.text = ""
            tvresult.text = ""
        }

        back.setOnClickListener {
            val string = tvexpression.text.toString()
            if (string.isNotEmpty()) {
                tvexpression.text = string.substring(0, string.length - 1)
            }
            tvresult.text = ""
        }
        equals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvexpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult= result.toLong()
                if (result == longResult.toDouble())
                    tvresult.text = longResult.toString()

                else
                    tvresult.text=result.toString()

            } catch (e: Exception) {
                Log.d("Exception", "message: " + e.message)
            }
        }
    }

    fun appendonexpression(string: String, canClear: Boolean) {
        if (canClear) {
            tvresult.text = " "
            tvexpression.append(string)
        } else {
            if (tvexpression.text.toString().last() != string[0]) {
                tvexpression.append(tvresult.text)
                tvexpression.append(string)
                tvresult.text = ""
            }

        }

    }
}