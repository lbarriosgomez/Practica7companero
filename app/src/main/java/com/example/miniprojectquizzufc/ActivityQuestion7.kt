package com.example.miniprojectquizzufc

class ActivityQuestion7: BaseQuestionActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_question7
    override fun getNextActivityClass(): Class<*> = ActivityQuestion8::class.java
    override fun getCorrectAnswers(): List<String> {
        return listOf("1")
    }
}