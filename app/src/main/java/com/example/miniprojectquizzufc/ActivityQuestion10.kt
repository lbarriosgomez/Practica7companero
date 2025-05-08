package com.example.miniprojectquizzufc

class ActivityQuestion10: BaseQuestionActivity() {
    override fun getLayoutResource(): Int = R.layout.activity_question10
    override fun getNextActivityClass(): Class<*> = FinalActivity::class.java
    override fun getCorrectAnswers(): List<String> {
        return listOf("Jon Jones", "St-Pierre", "Khabib", "MCgregor")
    }
}