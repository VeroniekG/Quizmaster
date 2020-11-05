package controller;

import database.mysql.CourseDAO;
import database.mysql.QuizDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import database.mysql.DBAccess;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import model.Course;
import model.Quiz;
import view.Main;

import java.util.List;

public class  CreateUpdateQuizController {
    @FXML
    public Button menuButton;
    private QuizDAO quizDAO;
    private CourseDAO courseDAO;
    private DBAccess dbAccess;
    private Quiz currentQuiz;
    @FXML
    private Button composeButton;
    @FXML
    private Label titleLabel;

    @FXML
    private TextField quizIdTextfield;
    @FXML
    private TextField quizNameTextfield;
    @FXML
    private ComboBox<Course> comboBoxCourse;


    public CreateUpdateQuizController() {
        quizDAO = new QuizDAO(Main.getDBaccessMySql());
        courseDAO =new CourseDAO(Main.getDBaccessMySql());
        comboBoxCourse = new ComboBox<>();

    }
//    public void fillComboBoxCourse (){
//        List<Course> allCourses = courseDAO.getAll();
//        ObservableList<Course> courseObservableList =
//                FXCollections.observableArrayList(allCourses);
//        comboBoxCourse.setItems(courseObservableList);
//    }
//
//    public void setup(Quiz quiz) {
//        titleLabel.setText("Wijzig Quiz");
//        quizIdTextfield.setText(String.valueOf(quiz.getIdQuiz()));
//        quizNameTextfield.setText(quizNameTextfield.getSelectedText());
//        comboBoxCourse.setValue(comboBoxCourse.getValue());
//        fillComboBoxCourse();
//    }

    public void setup(Quiz quiz) {
        currentQuiz = quiz;
        titleLabel.setText("Wijzig Quiz");
        quizIdTextfield.setText(String.valueOf(quiz.getIdQuiz()));
        quizNameTextfield.setText(quiz.getQuizName());
        setComboBoxCourseForQuiz(quiz);
    }

    public void setComboBoxCourseForQuiz(Quiz quiz) {
        Course quizForCourse = courseDAO.getOneById(quiz.getIdCourse());
        List<Course> allCourses = courseDAO.getAll();
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList(allCourses);
        comboBoxCourse.setItems(courseObservableList);
        comboBoxCourse.getSelectionModel().select(quizForCourse);
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
            currentQuiz = null;
        } else {
            currentQuiz = new Quiz(quizname);
        }
    }
//    //TJ Methode aangemaakt om quizzen op te slaan
//    public void doStoreQuiz(ActionEvent actionEvent) {
//        createQuiz();
//        if (currentQuiz != null) {
//            if (quizIdTextfield.getText().isEmpty()) {
//                quizDAO.storeOne(currentQuiz);
//                System.out.println(currentQuiz.getIdQuiz());
//                System.out.flush();
//                quizIdTextfield.setText(String.valueOf(currentQuiz.getIdQuiz()));
//                Alert saved = new Alert(Alert.AlertType.INFORMATION);
//                saved.setContentText("Quiz succesvol opgeslagen" + currentQuiz.getIdQuiz());
//                saved.show();
//            } else {
//                int id = Integer.parseInt(quizIdTextfield.getText());
//                currentQuiz.setIdQuiz(id);
//                quizDAO.updateQuiz(currentQuiz);
//                Alert updated = new Alert(Alert.AlertType.INFORMATION);
//                updated.setContentText("Quiz is gewijzigd");
//                updated.show();
//            }
//        }
//    }

    public void doStoreQuiz() {
        int idCourse = comboBoxCourse.getSelectionModel().getSelectedItem().getIdCourse();
        if (currentQuiz.getIdQuiz() == 0) {
            String quizName = quizNameTextfield.getText();
            currentQuiz = new Quiz(quizName, idCourse);
            quizDAO.storeOne(currentQuiz);
        } else {
            quizDAO.updateQuiz(currentQuiz);
        }
    }



    public void doCreateUpdateQuiz() {}
}
