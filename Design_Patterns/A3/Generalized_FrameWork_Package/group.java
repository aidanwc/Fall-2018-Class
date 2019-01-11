//Aidan Weber-Concannon
//260708481
import java.util.ArrayList;


public class group<member>{
    private String groupName;
    private int groupID;//Codes start at one and go up in ascending order, assigned by createGroup
    private ArrayList<member> listOfMembers; //Holds list of everyone in group

    //Used by createGroup method(constructor)
    public group(String groupName, int groupID){}

    //Keeps track of who is in group
    public void addMemberToGroup(member toBeAdded){}

    //Returns list of members registered for group
    public ArrayList<member> whoseInGroup(){}

    public String getName(){}

    public int getID(){}
    

}
