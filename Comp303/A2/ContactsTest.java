//Aidan Weber-Concannon
//260708481

import java.util.LinkedList;

public class ContactsTest {

    public static void main(String[] args){
        testToStringMethods();
        getContactTest();
        listAllTest();
        System.out.println("Tests Completed.");
    }

    //Tests all child classes to string formatting
    public static void testToStringMethods(){
        //Create one of each contact
        aquaintanceContacts A = new aquaintanceContacts("John","2504043120",'a');
        businessContacts B = new businessContacts("Bob", "911", "11 street, Montreal", "McDonalds",'b');
        friendContacts F = new friendContacts("Frank","303-102-4043","311 Rue University","23 august 1997",'F');

        //Prints each contact
        System.out.println("***Testing To string methods:***");
        System.out.println();

        System.out.println("Acquaintance To String: ");
        System.out.println(A);

        System.out.println();
        System.out.println("Business To String: ");
        System.out.println(B);

        System.out.println();
        System.out.println("Friend To String: ");
        System.out.println(F);

        //New Line
        System.out.println();
    }

    //Method found in contactBook class
    public static void getContactTest(){

        //Variables for testing
        contactBook book = new contactBook();
        Contacts testContact = new aquaintanceContacts("Aidan","414-4433", 'a');
        Contacts holder; //holds contact from book

        //Add contact to our book
        System.out.println("***Testing Get Contact method:***");
        System.out.println();

        book.addContact(testContact);//Add contact
        System.out.print("Contact added: ");//Print contact info that was added
        System.out.println(testContact);

        System.out.println("Trying to get contact: josh"); //Test a contact that is not in book
        holder= book.getContact("josh");
        if(holder!=null){System.out.println("Found.");}
        else {System.out.println("Not found.");}

        System.out.println();

        System.out.println("Trying to get contact: Aidan"); //Test a contact that is in book
        holder= book.getContact("Aidan");
        if(holder!=null){System.out.println("Found.");}
        else {System.out.println("Not found.");}

        System.out.println();
    }
    //Found in contactBook class
    public static void listAllTest(){
        System.out.println("***Testing list all method:***");
        System.out.println();

        //Creates list and book for testing
        contactBook book = new contactBook();
        Contacts inputContact;
        LinkedList<Contacts> myList;

        //Add 100 contacts to book
        for (int i = 0; i <100 ; i++) {
            inputContact = new aquaintanceContacts(""+i,"911",'a');
            book.addContact(inputContact);
        }

        System.out.println("100 Contacts added to book.");
        System.out.println("Calling listAll() method.");
        myList=book.listAll();

        System.out.println("Size of list: "+myList.size());//List size
        System.out.println();

        //Prints out each contact in list
        for(Contacts e: myList){
            System.out.println(e);
        }

        System.out.println();
    }
}
