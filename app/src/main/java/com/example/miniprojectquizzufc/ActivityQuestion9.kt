package com.example.miniprojectquizzufc


class ActivityQuestion9: BaseQuestionActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_question9
    override fun getNextActivityClass(): Class<*> = ActivityQuestion10::class.java
    override fun getCorrectAnswers(): List<String> {
        return listOf("1")
    }
}