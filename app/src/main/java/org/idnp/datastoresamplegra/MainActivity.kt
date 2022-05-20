package org.idnp.datastoresamplegra

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var setValueBtn: Button
    lateinit var counterManager: CounterDataStoreManager

    lateinit var inputPeriod: AppCompatEditText
    lateinit var inputSchool: AppCompatEditText
    lateinit var inputCodeCourse: AppCompatEditText
    lateinit var inputNameCourse: AppCompatEditText
    lateinit var inputSemestre: AppCompatEditText
    lateinit var inputDurationCourse: AppCompatEditText

    lateinit var inpFontStyle: AppCompatEditText
    lateinit var inpFontSize: AppCompatEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValueBtn = findViewById(R.id.setBtn)

        inputPeriod = findViewById(R.id.editTextPeriod)
        inputSchool = findViewById(R.id.editTextSchool)
        inputCodeCourse = findViewById(R.id.editTextCode)
        inputNameCourse = findViewById(R.id.editTextName)
        inputSemestre = findViewById(R.id.editTextSemestre)
        inputDurationCourse = findViewById(R.id.editTextDuration)

        inpFontStyle = findViewById(R.id.editTextFontStyle)
        inpFontSize = findViewById(R.id.editTextFontSize)

        counterManager = CounterDataStoreManager(this)

        lifecycleScope.launch {
            counterManager.background.collect { value ->
                inpFontStyle.setText(value.toString())
            }
        }
        lifecycleScope.launch {
            counterManager.font.collect { font ->
                inpFontSize.setText(font.toString())
            }
        }

        // Set the current value of the counter
        setValueBtn.setOnClickListener {
            lifecycleScope.launch {
                counterManager.setPrefs(
                    inpFontStyle.text.toString(),
                    inpFontSize.text.toString(),
                )
            }
        }

    }


}