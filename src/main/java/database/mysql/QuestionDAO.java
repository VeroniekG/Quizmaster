package database.mysql;

import model.Question;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                int idAnswer = resultSet.getInt("idAnswer");
                String answerRight = resultSet.getString("answerRight");
                String answerWrong1 = resultSet.getString("answerWrong1");
                String answerWrong2 = resultSet.getString("answerWrong2");
                String answerWrong3 = resultSet.getString("answerWrong3");
                question = new Question(idQuestion,description,idAnswer,answerRight,answerWrong1,answerWrong2,answerWrong3);
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

    @Override
    public void storeOne(Question type) {
        String sql = "INSERT INTO question(description) VALUES(?);";
        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, type.getDescription());
            int id = executeInsertStatementWithKey();
            type.setIdQuestion(id);
        } catch (SQLException sqlException) {
            System.out.println("SQL error " + sqlException.getMessage());
        }

    }

}
