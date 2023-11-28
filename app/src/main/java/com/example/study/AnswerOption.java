package com.example.study;

public class AnswerOption {
    private String answerOptionText;
    private boolean isCorrect;

    /**
     *
     * @param answerOptionText answer option text
     * @param isCorrect answer option is correct or not
     */
    public AnswerOption(String answerOptionText, boolean isCorrect) {
        this.answerOptionText = answerOptionText;
        this.isCorrect = isCorrect;
    }

    /**
     *
     * @param answerOptions answer option text
     * Constructor assumes false for correct answer option
     */
    public AnswerOption(String answerOptions){
        this.answerOptionText = answerOptions;
        this.isCorrect = false;
    }

    /**
     *
     * @return returns the option text for the AnswerOption-object
     */
    public String getAnswerOptionText() {
        return answerOptionText;
    }

    /**
     *
     * @return returns the boolean value of the AnswerOption-object
     */
    public boolean getIsCorrect() {
        return isCorrect;
    }
}