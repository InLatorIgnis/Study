package com.example.study;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question {
    private final String questionText;
    private final List<AnswerOption> answerOptionsList;

    /**
     * @param questionText      sets the question text
     * @param answerOptionsList sets the answer options from a list of type AnswerOption
     */
    public Question(String questionText, List<AnswerOption> answerOptionsList) {
        this.questionText = questionText;
        this.answerOptionsList = answerOptionsList;
    }

    /**
     * @return returns the question text of the question object
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * @return retuns a list answer options of the question object
     */
    public List<AnswerOption> getAnswerOptionsList() {
        return answerOptionsList;
    }

    /**
     * @return returns a list answer options of the question object in random order
     */
    public List<AnswerOption> getRandomOrderAnswerOptionsList() {
        // make a copy of answerOptionsList
        List<AnswerOption> randomOrderAnswerOptionsList = new ArrayList<AnswerOption>(answerOptionsList);
        // randomize the order of randomOrderAnswerOptionsList

        // Randomize the order of randomOrderAnswerOptionsList using Fisher-Yates shuffle
        Collections.shuffle(randomOrderAnswerOptionsList);

        return randomOrderAnswerOptionsList;
    }

}