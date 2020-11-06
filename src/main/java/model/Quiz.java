package model;

public class Quiz {

    private int idQuiz;
    private String quizName;
    protected int idCourse;

    public Quiz(int idQuiz, String quizName, int idCourse) {
        this(quizName, idCourse);
        this.idQuiz = idQuiz;
    }

    public Quiz(String quizName, int idCourse) {
        this.quizName = quizName;
        this.idCourse = idCourse;
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

    public int getIdCourse() {
        return idCourse;
    }
}
