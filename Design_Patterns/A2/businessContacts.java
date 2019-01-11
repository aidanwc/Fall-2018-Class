//Aidan Weber-Concannon
//260708481

public class businessContacts extends Contacts {

    //Unique attributes to business
    private String address;
    private String businessName;

    //Constructor for Business contacts
    public businessContacts(String name, String phoneNumber, String address, String businessName, char type){
        super(name,phoneNumber,type);
        this.address=address;
        this.businessName=businessName;
    }
    //Converts to string for list all method
    public String toString(){
        return super.typeToString()+" - " + super.getName()+ " - "+ super.getPhoneNumber()+ " - "+this.address+" - "+" N/A "+" - " + this.businessName;
    }

}
