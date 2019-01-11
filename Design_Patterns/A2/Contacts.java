//Aidan Weber-Concannon
//260708481


public class Contacts{

    //Parent values of contact
   private String name;
   private String phoneNumber;
   private final char category; //classifies the type of child contact, accepts a, f, or b.

    //Constructor for parent class
   public Contacts(String name, String phoneNumber,char category){
       this.name=name;
       this.phoneNumber=phoneNumber;
       this.category=category;
   }
    //Returns name
    public String getName() {
        return name;
    }
    //Returns phone number
    public String getPhoneNumber(){
       return phoneNumber;
    }

    //Returns category
    public char getCategory(){
        return category;
    }

    //Converts encoded char to string for to strings method
    public String typeToString(){
        char c = this.category;

        if(c=='f'||c=='F'){
            return "Friend";
        }else if(c=='a'||c=='A'){
            return "Acquaintance";
        }else{
            return "Business";
        }
    }
}
