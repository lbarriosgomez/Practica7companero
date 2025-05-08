package com.example.miniprojectquizzufc

class ActivityQuestion1 : BaseQuestionActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_question1
    override fun getNextActivityClass(): Class<*> = ActivityQuestion2::class.java
    override fun getCorrectAnswers(): List<String> = listOf("Khabib", "Khabib Nurmagomedov")

}