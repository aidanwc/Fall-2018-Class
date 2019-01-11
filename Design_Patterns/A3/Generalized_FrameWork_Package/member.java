//Aidan Weber-Concannon
//260708481
import java.util.LinkedList;

//Outline for member object, other features can be added
abstract class member<group>{
     private String name;
     private String memberID;
     private LinkedList<group> groupsTaken;
     private double[] score;//Group ID points to index in array, stores grade there
     private int periodsAsMember;

    //Used by student subclasses
    public member(String name, String memberId){}

    //Checks whether member is in correct amount of groups, divides length of groups taken by periods as member, should be implemented at end of each period
    public abstract boolean validMember();

    //checks if valid group and registers for it, adds to groups taken(1)
    public void registerGroup(int registerGroup){}

    //goes to group and adds score(2)
    public void addScore(int groupID,double score){}

    //Returns length of groupsTaken, can be used to check if correct kind of member
    public int registeredGroups(){}

    //Adds 1 to periods, used to see if registered for correct amount of groups  
    public void increasePeriod(){}

    //Returns periods taken as member
    public int getPeriodsMember(){}

    public String getName(){}

    public String getID(){}

    public LinkedList<group> getGroupsTaken(){}

    public double getScore(int groupID)



}
