//Aidan Weber-Concannon
//260708481

public class aquaintanceContacts extends Contacts{

    //Constructor for acquaintance
    public aquaintanceContacts(String name, String phoneNumber, char type){
        super(name,phoneNumber,type);
    }

    //Converts to string for list all method
    public String toString(){
        return super.typeToString()+" - " + super.getName()+ " - "+ super.getPhoneNumber()+ " - "+" N/A "+ " - " + " N/A "+ " - "+ " N/A ";
    }

}
