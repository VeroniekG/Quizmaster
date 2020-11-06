package database.mysql;

import model.Question;
import model.Quiz;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO extends AbstractDAO implements GenericDAO<Question> {

    public QuestionDAO(DBAccess dbAccess) {
        super(dbAccess);
    }


    //HL - This method retrieves all  questions from the database

    @Override
    public ArrayList<Question> getAll() {
        String sql = "Select * From Question";
        ArrayList<Question> questionsList = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            Question question;
            while (resultSet.next()) {
                int idQuestion = resultSet.getInt("idQuestion");
                String description = resultSet.getString("description");
                String answerRight = resultSet.getString("answerRight");
                String answerWrong1 = resultSet.getString("answerWrong1");
                String answerWrong2 = resultSet.getString("answerWrong2");
                String answerWrong3 = resultSet.getString("answerWrong3");
                int idQuiz = resultSet.getInt("idQuiz");
                question = new Question(idQuestion,description,answerRight,answerWrong1,answerWrong2,answerWrong3,idQuiz);
                questionsList.add(question);
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return questionsList;
    }

    //HL - This method retrieves a question from the database based on the id number of the question


    @Override
    public Question getOneById(int id) {
        String sql = "SELECT * FROM question WHERE idQuestion = ?";
        Question question = null;
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = executeSelectStatement();
            if (resultSet.next()) {
                String description = resultSet.getString("description");
                question = new Question(description);
                question.setIdQuestion(id);
            } else {
                System.out.println("Vraag met id " + id + " niet gevonden!");
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
        return question;

    }

    //HL - This method stores a question in the database


    @Override
    public void storeOne(Question question) {
        String sql = "INSERT INTO Question(description, answerRight, answerWrong1, answerWrong2, answerWrong3) VALUES(?,?,?,?,?) ;";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, question.getDescription());
            preparedStatement.setString(2, question.getAnswerRight());
            preparedStatement.setString(3, question.getAnswerWrong1());
            preparedStatement.setString(4, question.getAnswerWrong2());
            preparedStatement.setString(5, question.getAnswerWrong3());
//            preparedStatement.setInt(6, question.getIdQuiz());
            int id = executeInsertStatementWithKey();
            question.setIdQuestion(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }

    }

    //HL - This method updates a question with input from user


    public void updateQuestion (Question question) {
        String sql = "Update Question Set description = ?, answerRight = ?, answerWrong1 = ?, answerWrong2 = ?, answerWrong3 = ? where idQuestion = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, question.getDescription());
            preparedStatement.setString(2, question.getAnswerRight());
            preparedStatement.setString(3, question.getAnswerWrong1());
            preparedStatement.setString(4, question.getAnswerWrong2());
            preparedStatement.setString(5, question.getAnswerWrong3());
            preparedStatement.setInt(6, question.getIdQuestion());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }

    //HL - This method retrieves an arraylist of questions that are part of a certain quiz


    public List<Question> getQuestionsForQuizWithId(int idQuiz) {
            List<Question> questions = new ArrayList<>();
            String sql = String.format("SELECT * FROM Question WHERE idQuiz=%d", idQuiz);
            try {
                setupPreparedStatement(sql);
                ResultSet resultSet = executeSelectStatement();
                while (resultSet.next()) {
                    int idQuestion = resultSet.getInt("idQuestion");
                    Question question = getOneById(idQuestion);
                    questions.add(question);
                }
            } catch (SQLException sqlException) {
                System.out.println("SQL-error " + sqlException.getMessage());
            }
            return questions;
        }

    //HL - This method deletes a question from the database


    public void deleteQuestion(Question question){
        String sql = "DELETE FROM question WHERE idQuestion = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, question.getIdQuestion());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException){
            System.out.println("SQL error" + sqlException.getMessage());
        }
    }

}
