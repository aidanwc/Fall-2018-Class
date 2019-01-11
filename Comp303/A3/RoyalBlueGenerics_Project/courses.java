//Aidan Weber-Concannon
//260708481
import java.util.ArrayList;

//E pertains to student in this case
public class courses <E>{
    private String courseName;
    private int courseCode;//Codes start at one and go up in ascending order, assigned by CreateCourse
    private ArrayList<E> classList; //Holds list of everyone in class

    //Used by createCourse method(constructor)
    public courses(String courseName, int courseCode){}

    //Keeps track of who is in course
    public void addStudentToClassList(E toBeAdded){}

    //Returns list of people registered for class
    public ArrayList<E> whoseInClass(){}
    
    public String getName(){}

    public int getCode(){}

   

}
