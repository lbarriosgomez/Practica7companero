package com.example.miniprojectquizzufc

class ActivityQuestion4 : BaseQuestionActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_question4
    override fun getNextActivityClass(): Class<*> = ActivityQuestion5::class.java
    override fun getCorrectAnswers(): List<String> = listOf("2020")

}