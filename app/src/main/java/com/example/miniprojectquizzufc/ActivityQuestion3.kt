package com.example.miniprojectquizzufc

class ActivityQuestion3 : BaseQuestionActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_question3
    override fun getNextActivityClass(): Class<*> = ActivityQuestion4::class.java
    override fun getCorrectAnswers(): List<String> = listOf("Chama")

}