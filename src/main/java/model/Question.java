package model;

public class Question {

    private int idQuestion;
    private String description;
    private String answerRight;
    private String answerWrong1;
    private String answerWrong2;
    private String answerWrong3;
    private int idQuiz;

    //constructors

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Question(String description, String answerRight, String answerWrong1, String answerWrong2, String answerWrong3) {
        this.description = description;
        this.answerRight = answerRight;
        this.answerWrong1 = answerWrong1;
        this.answerWrong2 = answerWrong2;
        this.answerWrong3 = answerWrong3;
    }

    public Question(int idQuestion, String description, String answerRight, String answerWrong1, String answerWrong2, String answerWrong3, int idQuiz) {
        this.idQuestion = idQuestion;
        this.description = description;
        this.answerRight = answerRight;
        this.answerWrong1 = answerWrong1;
        this.answerWrong2 = answerWrong2;
        this.answerWrong3 = answerWrong3;
        this.idQuiz = idQuiz;
    }

    //getters and setters

    public int getIdQuiz() {
        return idQuiz;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getDescription() {
        return description;
    }


    public String getAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(String answerRight) {
        this.answerRight = answerRight;
    }

    public String getAnswerWrong1() {
        return answerWrong1;
    }

    public String getAnswerWrong2() {
        return answerWrong2;
    }

    public String getAnswerWrong3() {
        return answerWrong3;
    }

    //HL - toString

    @Override
    public String toString() {

        return description;
    }
}


