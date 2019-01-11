//Aidan Weber-Concannon
//260708481

import java.util.HashMap;
import java.util.LinkedList;

public class contactBook {
    private HashMap<String, LinkedList<Contacts>> book; //Hash map containing nodes of contacts

    //Book to store all the contacts
    public contactBook(){
        this.book= new HashMap<String, LinkedList<Contacts>>();
    }

    //Add contact method(Case sensitive)
    public void addContact(Contacts contact){
        if (!book.containsKey(contact.getName())){ //if the name doesn't exist creates new list

            LinkedList<Contacts> list = new LinkedList<Contacts>();//Creates list
            list.add(contact); //adds contact
            book.put(contact.getName(),list);//puts list and key in to map
        } else{
            LinkedList<Contacts> madeList = book.get(contact.getName()); //Gets the already made list
            madeList.add(contact); //Adds the contact to list
        }

    }

    //Returns a contact by searching its name(Case sensitive)
    public Contacts getContact(String name){
        LinkedList<Contacts> list = book.get(name);//Gets list
        if(list==null) return null; //Returns null if the name does not exist in list

        Contacts contact = list.getFirst();//Gets first name in list
        return contact;
    }

    //Prints entire list
    public LinkedList<Contacts> listAll(){

        LinkedList<Contacts> copyOfBook = new LinkedList<Contacts>();

        for (LinkedList<Contacts> list : book.values()){ //Iterates through book
            for(int i =0; i<list.size();i++){
                Contacts contact = list.get(i); //Gets all contacts with the same name
                copyOfBook.add(contact); //Adds to linked list
            }
        }
        return copyOfBook;
    }

}
