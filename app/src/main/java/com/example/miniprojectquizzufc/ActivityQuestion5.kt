package com.example.miniprojectquizzufc

class ActivityQuestion5 : BaseQuestionActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_question5
    override fun getNextActivityClass(): Class<*> = ActivityQuestion6::class.java
    override fun getCorrectAnswers(): List<String> {
        return listOf("5") // ✅ Solo "4" es la respuesta correcta
    }

}