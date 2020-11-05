package model;


public class Course {

    private int idCourse;
    private String courseName;
    private int coordinatorID;


    public Course(int idCourse, String courseName, int coordinatorID){
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.coordinatorID = coordinatorID;
    }

    public Course(String courseName, int coordinatorID){
        this.courseName = courseName;
        this.coordinatorID = coordinatorID;
    }

    public Course(){
    }

    public Course(String coursename) {
        this.courseName = coursename;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCoordinatorID() {
        return coordinatorID;
    }

    public void setCoordinatorID(int coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "" + courseName;
    }
}
