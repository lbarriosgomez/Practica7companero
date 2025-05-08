package com.example.miniprojectquizzufc

class ActivityQuestion6 : BaseQuestionActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_question6
    override fun getNextActivityClass(): Class<*> = ActivityQuestion7::class.java
    override fun getCorrectAnswers(): List<String> {
        return listOf("16")
    }
}