package com.example.android.thequizapp;

/**
 * Created by Mrvrctt on 10.01.2018.
 */
// Model class for our questions.
public class Question<T> {

    //Constats for detecting view type of view.
    public static final int RADIO = 0;
    public static final int CHECKBOX = 1;
    public static final int EDIT_TEXT = 2;

    private String question, answer1, answer2, answer3;
    //View type of our question.
    private int type;

    //Our correct answer or answers it is T.
    private T correctAnswer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(T correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Question(String question, String answer1, String answer2, String answer3, int type) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.type = type;
    }

    public Question(String question, String answer1, String answer2, String answer3, int type, T correctAnswer) {
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.type = type;
        this.correctAnswer = correctAnswer;
    }

    public Question(){}
}
