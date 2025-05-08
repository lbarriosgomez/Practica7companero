package com.example.miniprojectquizzufc

class ActivityQuestion2 : BaseQuestionActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_question2
    override fun getNextActivityClass(): Class<*> = ActivityQuestion3::class.java
    override fun getCorrectAnswers(): List<String> = listOf("The Machine")

}