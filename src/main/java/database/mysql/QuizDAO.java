package database.mysql;

import model.Course;
import model.Quiz;
import model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                String quizName = resultSet.getString("quizName");
                quiz = new Quiz(quizName);
                quiz.setIdQuiz(resultSet.getInt("idQuiz"));
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
    public void storeOne(Quiz type) {
        String sql = "INSERT INTO quiz(quizName) VALUES(?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, type.getQuizName());
            int id = executeInsertStatementWithKey();
            type.setIdQuiz(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }

    }
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
    // TJ methode togevoegd om quizzen op te slaan in db
    public void updateQuiz(Quiz quiz){
        String sql = "Update quiz Set quizName = ? where idQuiz = ?;";
        try{
            setupPreparedStatement(sql);
            preparedStatement.setString(1, quiz.getQuizName());
            preparedStatement.setInt(2, quiz.getIdQuiz());
            executeManipulateStatement();
        } catch (SQLException sqlException){
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    public ArrayList<Quiz> getQuizesForCourse(){
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
}