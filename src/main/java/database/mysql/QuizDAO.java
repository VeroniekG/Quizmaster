package database.mysql;

import model.Course;
import model.Quiz;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO extends AbstractDAO implements GenericDAO<Quiz> {

    public QuizDAO(DBAccess dbAccess) {
        super(dbAccess);
    }


    @Override
    public ArrayList<Quiz> getAll() {

        String sql = "Select * from quiz";
        ArrayList<Quiz> quizlist = new ArrayList<>();
        try{
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Quiz quiz;
            while (resultSet.next()){
                int idQuiz = resultSet.getInt("idQuiz");
                int idCourse = resultSet.getInt("idCourse");
                String quizName = resultSet.getString("quizName");
                quiz = new Quiz(idQuiz, quizName, idCourse);
                quizlist.add(quiz);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return quizlist;
    }

    @Override
    public Quiz getOneById(int id) {

        String sql = "SELECT * FROM quiz WHERE idQuiz = ?";
        Quiz quiz = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String quizName = resultSet.getString("quizName");
                quiz = new Quiz(quizName);
                quiz.setIdQuiz(id);
            } else {
                System.out.println("Quiz met id " + id + " niet gevonden!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return quiz;
    }

    @Override
    public void storeOne(Quiz quiz) {
        String sql = "INSERT INTO quiz(quizName, idCourse)  VALUES(?, ?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, quiz.getQuizName());
            preparedStatement.setInt(2, quiz.getIdCourse());
            int id = executeInsertStatementWithKey();
            quiz.setIdQuiz(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }

    }//delete quiz van database
    public void deleteQuiz(Quiz quiz){
        String sql = "DELETE FROM quiz WHERE idQuiz = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quiz.getIdQuiz());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println("SQL error" + sqlException.getMessage());
        }
    }

    public void updateQuiz(Quiz quiz){
        String sql = "Update quiz Set quizName = ?, idCourse = ? where idQuiz = ?;";
        try{
            setupPreparedStatement(sql);
            preparedStatement.setString(1, quiz.getQuizName());
            preparedStatement.setInt(2, quiz.getIdCourse());
            preparedStatement.setInt(3, quiz.getIdQuiz());
            executeManipulateStatement();
        } catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    public ArrayList<Quiz> getQuizzesForCourse(){
        String sql = "SELECT * FROM quiz WHERE idCourse = ?;";
        ArrayList<Quiz> quizList = new ArrayList<>();
        try{
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Quiz quiz;
            while (resultSet.next()){
                String nameQuiz = resultSet.getString("nameQuiz");
                quiz = new Quiz(nameQuiz);
                quizList.add(quiz);
            }
        }catch (SQLException sqlException){
            System.out.println("SQL error" + sqlException.getMessage());
        }return quizList;
    }

    public List<Quiz> getQuizzesForCourseWithId(int idCourse) {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = String.format("SELECT * FROM Quiz WHERE idCourse=%d", idCourse);
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                int idQuiz = resultSet.getInt("idQuiz");
                Quiz quiz = getOneById(idQuiz);
                quizzes.add(quiz);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL-error " + sqlException.getMessage());
        }
        return quizzes;
    }

        //@TJ, match the idQuiz with idCourse
    public Quiz getIdCourse(int idCourse) {
        String sql = "SELECT * FROM quiz WHERE idCourse = ?";
        Quiz quiz = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, idCourse);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String quizName = resultSet.getString("quizName");
                quiz = new Quiz(quizName);
                quiz.setIdQuiz(idCourse);
            } else {
                System.out.println("Quiz met id " + idCourse + " niet gevonden!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return quiz;
    }
}