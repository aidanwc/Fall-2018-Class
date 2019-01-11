//Aidan Weber-Concannon
//260708481
import java.util.List;

//Main method is left out as this is intended as a package
public class organization<member,group> {

    private member [] listOfAllMembers; //Master list of members
    private group [] listOfAllGroups; //Master list of groups for Organization
    private int numberOfGroups;// keeps track of amount of groups, used for group ID's

    //Constructor
    public organization(int sizeMembers,int sizeGroups){}

    //Creates member using constructor and adds them to master list(3)
    public void createMember(String memberName, String memberID){}

    //Creates group and adds it to master list, calls increaseGroupCount(4)
    public void createGroup(String groupName, int codeForGroup){}

    //Increases the number of groups by 1
    public void increaseGroupCount(){}

    //Registers a list of members to a group(
    public void registerMembers(List<member> listMembers, int codeForGroup){}

    //Checks in master list for group, used by create group to see if group already exists
    public boolean groupExists(int codeForGroup){}

    //Returns member from master list
    public member returnMember(String memberID){}

    //Returns group from master list
    public group returnGroup(String groupID){}

    public int getNumberOfGroups(){}
}
