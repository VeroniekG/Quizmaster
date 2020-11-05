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

    @Override
    public Question getOneById(int id) {
        String sql = "SELECT * FROM course WHERE idQuestion = ?";
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

    //HL - This method retrieves an arraylist of questions that are part of a certain quiz

    public ArrayList<Question> getQuestionsByQuizId (int quizId){
        String sql = "SELECT * FROM Question WHERE idQuiz = ?;";
        ArrayList<Question> result = new ArrayList<>();
        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, quizId);
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
                question = new Question(idQuestion, description, answerRight, answerWrong1,
                        answerWrong2, answerWrong3, idQuiz);
                result.add(question);
            }
        } catch (SQLException exception)
        { System.out.println("SQL error " + exception.getMessage());
            }
            return result;
        }


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

    public void updateQuestion (Question question) {
        String sql = "Update Question Set description = ?, answerRight = ?, answerWrong1 = ?, answerWrong2 = ?, answerWrong3 = ? where idQuestion = ?;";
        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, question.getDescription());
            preparedStatement.setString(2, question.getAnswerRight());
            preparedStatement.setString(3, question.getAnswerWrong1());
            preparedStatement.setString(4, question.getAnswerWrong2());
            preparedStatement.setString(5, question.getAnswerWrong3());
//            preparedStatement.setInt(6, question.getIdQuiz());
            preparedStatement.setInt(6, question.getIdQuestion());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }
    }
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
