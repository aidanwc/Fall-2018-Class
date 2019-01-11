//Aidan Weber-Concannon
//260708481
import java.util.List;
//E for students, F for courses
public class royalBlueSchool<E,F> {

    private E [] listOfAllStudents; //Master list of students
    private F [] listOfAllCourses; //Master list of courses for school
    private int numberOfCourses;// keeps track of course numbers, used for course codes

    //Constructor
    public royalBlueSchool(int sizeE,int sizeF){}
    //Creates student using constructor and adds them to master list(3)
    public void createStudent(String name, String ID){}

    //Creates course and adds it to master list, calls increaseCourseCount(4)
    public void createCourse(String courseName, int courseCode){}

    //Not needed, would exit loop with a false statement(5)
    public void quitProgram(){}

    //Increases the number of courses
    public void increaseCourseCount(){}

    //Registers a list of students to a course(
    public void registerStudents(List<E> listStudents, int courseCode){}

    //Checks in master list for course, used by create course to see if it exists already
    public boolean courseExists(int courseCode){}
    
    //Returns student from master list 
    public E returnStudent(String studentID){}

     //Returns course from master list 
    public F returnCourse(String studentID){}

    //Returns amount of courses 
    public int getNumberOfCourses(){}

}
