//Aidan Weber-Concannon
//260708481

//import scanner/linked list
import java.util.LinkedList;
import java.util.Scanner;

public class ContactsMain {

    public static void main(String[] args){

        //initilize play as true
        boolean play= true;
        Scanner scan = new Scanner(System.in);
        String input; //buffer for scanner
        int choice; //int buffer for scanner

        contactBook book= new contactBook(); //Starts a new hashmap
        LinkedList<Contacts> copyOfBook = new LinkedList<Contacts>(); //List to be passed to list all method, avoid I/O in other classes

        char typeOfContact; //Buffers for making new contacts
        String name;
        String phoneNumber;
        String address;
        String businessName;
        String birthday;

        //Plays so long as person doesnt quit
        while(play){

            //Print Menu
            System.out.println("CHOOSE ONE OF THE FOLLOWING OPTIONS:");
            System.out.println("1. New Contact");
            System.out.println("2. Find Contact");
            System.out.println("3. List All");
            System.out.println("4. Quit");

            //Gets encoded menu option
            try{
                input = scan.next();
                choice = Integer.parseInt(input);
            }catch (NumberFormatException e){
                choice=0;
            }
            //Creates a new contact
            if(choice==1){
                Contacts newContact;//Buffer for contact

                //Asks for contact type
                System.out.println("A for acquaintance, B for Business or F for Friend? ");
                typeOfContact= scan.next().charAt(0); //gets letter

                if(!validCharacter(typeOfContact)){//If not a valid contact type goes back to main menu

                    System.out.println("Please enter a valid contact type.");
                    continue;
                }

                //Clears scanner buffer
                scan.nextLine();
                System.out.println("Name:");
                name=scan.nextLine(); //Gets name

                //Gets phone number
                System.out.println("Phone number:");
                phoneNumber=scan.nextLine();

                //Creates business contact
                if(typeOfContact=='B'||typeOfContact=='b'){//If business contact get other fields
                    System.out.println("Address:");
                    address=scan.nextLine(); //Fills in adress

                    System.out.println("Business Name:");
                    businessName=scan.nextLine(); //Fills in name
                    newContact= new businessContacts(name,phoneNumber,address,businessName,typeOfContact); //creates business contact

                }else if(typeOfContact=='f'||typeOfContact=='F'){ //Gets fields for friends
                    System.out.println("Address:");
                    address=scan.nextLine();

                    System.out.println("Birthday:");
                    birthday=scan.nextLine();
                    newContact= new friendContacts(name,phoneNumber,address,birthday,typeOfContact); //Creates friend contact

                }else{
                    newContact= new aquaintanceContacts(name,phoneNumber,typeOfContact); //Creates an acquaintance
                }
                book.addContact(newContact);//Adds created contact to book
                System.out.println("Added!");

                //Find contact
            } else if(choice==2){

                System.out.println("Enter the name you wish to search for: ");
                name = scan.next();

                //Looks in map for contact name
                Contacts contact = book.getContact(name);

                if(contact==null){
                    System.out.println("Contact not found"); //Prints error if contact is not found
                }else{
                    System.out.println(contact);//Otherwise prints out first contact if found
                }
            }else if(choice==3){
                System.out.println("CONTACTS: (TYPE - NAME - PHONE - ADDRESS - BIRTHDAY - BUSINESS)"); //Heading
                copyOfBook= book.listAll(); //Gets a copy of contacts
                for(Contacts e: copyOfBook){
                    System.out.println(e); //prints each contact
                }

            }else if(choice==4){
                play=false; //quits loop
            }else{
                System.out.println("Please enter 1,2,3 or 4."); //Lets player re-enter choice if a number 1-4 was not chosen
            }
            System.out.println();//New line
        }
    }

    //Tests whether input is a valid character of a contact type
    public static boolean validCharacter(char c){
        if(c=='a'||c=='A'||c=='b'||c=='B'||c=='f'||c=='F'){
            return true;
        }
        return false;
    }
}

