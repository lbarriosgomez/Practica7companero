package com.example.miniprojectquizzufc

class ActivityQuestion8: BaseQuestionActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_question8
    override fun getNextActivityClass(): Class<*> = ActivityQuestion9::class.java
    override fun getCorrectAnswers(): List<String> {
        return listOf("4")
    }
}