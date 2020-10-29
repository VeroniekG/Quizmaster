package model;

public class Quiz {

    private int idQuiz;
    private String quizName;

    public Quiz(int idQuiz, String quizName) {
        this.idQuiz = idQuiz;
        this.quizName = quizName;
    }

    public Quiz(String quizName) {
        this.quizName = quizName;
    }

    @Override
    public String toString() {
        return "" + quizName + " ";
    }

    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }
}
