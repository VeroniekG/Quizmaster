package model;

import java.time.LocalDateTime;

public class QuizResult {
//@AuthorVG - the Quizresult consists of: name of user(student), name of quiz, list of correct answers,
// list of given answers, dateTime when quiz was completed, how many times same quiz completed.

    private User user;
    private Quiz quiz;
    private Question correctAnswers;
    private String givenAnswersStudent;
    LocalDateTime dateTime;
    private int timesCompleted;

    @Override
    public String toString() {
        StringBuilder resultString = new StringBuilder();
        resultString.append("Quiz result:\n");
        resultString.append(String.format("Name student: %s %s\n", user.getFirstName(), user.getLastName()));
        resultString.append(String.format("Quiz name: %s\n", quiz.getQuizName()));
        resultString.append(String.format("Quiz completed on: %s\n", dateTime));
        resultString.append(String.format("Your answers: \n", givenAnswersStudent));
        resultString.append(String.format("Correct answers: \n", correctAnswers));
        return resultString.toString();
    }

    public String getGivenAnswersStudent() {
        return givenAnswersStudent;
    }

    public void setGivenAnswersStudent(String givenAnswersStudent) {
        this.givenAnswersStudent = givenAnswersStudent;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getTimesCompleted() {
        return timesCompleted;
    }

    public void setTimesCompleted(int timesCompleted) {
        this.timesCompleted = timesCompleted;
    }
}
