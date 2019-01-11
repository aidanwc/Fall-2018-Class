//Aidan Weber-Concannon
//260708481

public class friendContacts extends Contacts{

    //Unique attributes to friends
    private String address;
    private String birthday;

    //Constructor for friends contact
    public friendContacts(String name, String phoneNumber, String address, String birthday,char type){
        super(name,phoneNumber,type); //Initilize parent attributes
        this.address=address;
        this.birthday=birthday;
    }
    //Converts to string for list all method
    public String toString(){
        return super.typeToString()+" - " + super.getName()+ " - "+ super.getPhoneNumber()+" - "+this.address+" - "+this.birthday+" - "+ " N/A ";
    }
}
