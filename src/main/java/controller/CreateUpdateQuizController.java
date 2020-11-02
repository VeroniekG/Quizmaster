package controller;

import database.mysql.CourseDAO;
import database.mysql.QuizDAO;
import javafx.fxml.FXML;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Course;
import model.Quiz;
import model.Role;
import view.Main;

public class  CreateUpdateQuizController {
    @FXML
    public Button menuButton;
    private QuizDAO quizDAO;
    private CourseDAO courseDAO;
    private DBAccess dbAccess;
    private Quiz quiz;
    @FXML
    private Button composeButton;
    @FXML
    private Label titleLabel;

    @FXML
    private TextField quizIdTextfield;
    @FXML
    private TextField quizNameTextfield;
    @FXML
    ComboBox<Course> comboBoxCourse;



    public CreateUpdateQuizController() {
        quizDAO = new QuizDAO(Main.getDBaccessMySql());
        courseDAO =new CourseDAO(Main.getDBaccessMySql());
        comboBoxCourse = new ComboBox<>();
    }

    public void setup(Quiz quiz) {
        titleLabel.setText("Wijzig Quiz");
        quizIdTextfield.setText(String.valueOf(quiz.getIdQuiz()));
        quizNameTextfield.setText(quizNameTextfield.getSelectedText());
        comboBoxCourse.setValue(comboBoxCourse.getValue());
    }

    //TJ menu knop terug naar menu
    public void doMenu(ActionEvent actionEvent) {
        Main.getSceneManager().showManageQuizScene();
    }

    //TJ toegevoegde methode om quizen toe te voegen
    public void createQuiz() {
        StringBuilder warningtext = new StringBuilder();
        boolean correct = true;
        String quizname = quizNameTextfield.getText();
        if (quizname.isEmpty()) {
            warningtext.append("Graag een quiznaam invoeren\n");
            Alert foutmelding = new Alert(Alert.AlertType.ERROR);
            foutmelding.setContentText(warningtext.toString());
            foutmelding.show();
            quiz = null;
        } else {
            quiz = new Quiz(quizname);
        }
    }
    //TJ Methode aangemaakt om quizzen op te slaan
    public void doStoreQuiz(ActionEvent actionEvent) {
        createQuiz();
        if (quiz != null) {
            if (quizIdTextfield.getText().isEmpty()) {
                quizDAO.storeOne(quiz);
                System.out.println(quiz.getIdQuiz());
                System.out.flush();
                quizIdTextfield.setText(String.valueOf(quiz.getIdQuiz()));
                Alert saved = new Alert(Alert.AlertType.INFORMATION);
                saved.setContentText("Quiz succesvol opgeslagen" + quiz.getIdQuiz());
                saved.show();
            } else {
                int id = Integer.parseInt(quizIdTextfield.getText());
                quiz.setIdQuiz(id);
                quizDAO.updateQuiz(quiz);
                Alert updated = new Alert(Alert.AlertType.INFORMATION);
                updated.setContentText("Quiz is gewijzigd");
                updated.show();
            }
        }
    }



    public void doCreateUpdateQuiz() {}
}
