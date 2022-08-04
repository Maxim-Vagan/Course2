package ru.maxvagan.course2.storeclasses;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Objects;

public class Question {
    private String question;
    private String answer;


    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question1 = (Question) o;
        return Objects.equals(getQuestion(), question1.getQuestion()) && Objects.equals(getAnswer(), question1.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuestion(), getAnswer());
    }

    @Override
    public String toString() {
        return "Question: {" +
                "question=\"" + question + "\"" +
                ", answer=\"" + answer + "\"" +
                "}";
    }
}
