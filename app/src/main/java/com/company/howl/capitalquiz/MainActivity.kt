package com.company.howl.capitalquiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val quizzes = arrayOf(
        "캐나다의 수도는 어디인가?",
        "호주의 수도는 어디인가?",
        "케냐의 수도는 어디인가?",
        "스웨덴의 수도는 어디인가?",
        "독일의 수도는 어디인가?"
    )
    private val answers = arrayOf(
        "오타와",
        "캔버라",
        "나이로비",
        "스톡홀름",
        "베를린"
    )

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oxStack = findViewById<TextView>(R.id.main_textView_OXStack)
        val typeAnswer = findViewById<TextView>(R.id.main_editText_typeAnswer)
        val submitAnswer = findViewById<TextView>(R.id.main_button_submitAnswer)
        val start = findViewById<TextView>(R.id.main_button_start)
        val next = findViewById<TextView>(R.id.main_button_next)

        var quizCount = 0
        loadQuiz(quizCount)

        submitAnswer.setOnClickListener {
            if (typeAnswer.text.toString() == answers[quizCount]) {
                Toast.makeText(this, "정답입니다!", Toast.LENGTH_SHORT).show()
                oxStack.text = "${oxStack.text}O"
            } else {
                Toast.makeText(this, "틀렸습니다. 정답: ${answers[quizCount]}", Toast.LENGTH_SHORT).show()
                oxStack.text = "${oxStack.text}X"
            }
            loadQuiz(++quizCount)
            typeAnswer.text = ""
        }

        start.setOnClickListener {
            oxStack.text = ""
            quizCount = 0
            loadQuiz(quizCount)
        }

        next.setOnClickListener {
            loadQuiz(++quizCount)
        }
    }

    private fun loadQuiz(quizCount: Int) {
        if (quizCount >= quizzes.size) {
            Toast.makeText(this, "문제가 더이상 없습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        val quizNum = findViewById<TextView>(R.id.main_textView_quizNum)
        val quizContent = findViewById<TextView>(R.id.main_textView_quizContent)

        quizNum.text = "문제-${quizCount + 1}"
        quizContent.text = quizzes[quizCount]
    }
}
