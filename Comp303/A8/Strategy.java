
//Aidan Weber-Concannon
//260708481
import java.util.ArrayList;

public interface Strategy<T> {
	
	//Entry is in the form of array of strings with index a different column
	
	//Defines what an entry is 
	public String[] makeEntry(T object);
	
	//Gives list of headers 
	public ArrayList<String> headerNames();

}

//Strategy for food 
class foodStrategy<T> implements Strategy<T> {
	
	foodStrategy(){
	}
	
	//Make a table entry, #columns of entry is defined by user, user chooses columns values 
	public String[] makeEntry(T object){
		Food food=(Food) object; 
		String[] entry =new String[2]; 
		
		entry[0]=food.getName(); //Add name 
		int calories =4*food.getCarbs()+9*food.getFat()+4*food.getProtein();//Calculate Calories 
		entry[1]=calories+" cal"; //Add calories 
		
		return entry;
	}
	
	//Make list of headers 
	public ArrayList<String> headerNames(){
		ArrayList<String> headers=new ArrayList<String>();
		
		//User chooses headers 
		headers.add("Name");
		headers.add("Calories");
		
		return headers;
	}

	
}
