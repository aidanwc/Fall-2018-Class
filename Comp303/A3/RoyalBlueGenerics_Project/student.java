//Aidan Weber-Concannon
//260708481
import java.util.LinkedList;

//E pertains to course
abstract class student<E>{
     private String name;
     private String studentId;
     private LinkedList<E> classHistory;
     private double[] grades;//Course code points to index in array, stores grade there
     private int semestersTaken;//Used to check whether student is valid 

    //Used by student subclasses
    public student(String name, String studentId){}

    //Checks whether student is in correct amount of courses, divides (classHistory/SemestersTaken), should be tested at beggining of each new semester
    public abstract boolean validStudent();

    //checks if valid course and registers for it, adds to history(1)
    public void registerCourse(int courseCode){}

    //goes to course and adds grade(2)
    public void addGrade(int courseCode,double grade){}

    //Returns length of class History, can be used to check if correct kind of student
    public int registeredCourses(){}

    //Adds 1 to semesters taken, used to see if registered for correct amount of courses by dividing classhistory length by semesters  
    public void increaseSemestersTaken(){}

    //Gets semesters taken 
    public int getSemestersTaken(){}

    public String getName(){}

    public String getID(){}

    public LinkedList<E> getClassHistory(){}

    public double getGrade(int courseID)




}
