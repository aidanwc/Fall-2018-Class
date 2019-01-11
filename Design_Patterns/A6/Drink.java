//Name: Aidan Weber-Concannon
//ID: 260708481


//Extends item
public class Drink extends Item {
	private Boolean Alcoholic;//Tells if you need ID 
	
	//Constuctor for class, takes alcoholic 
	Drink(String name, double price,Boolean alcoholic){
		super(name,price,'B');//Call super constructor 
		Alcoholic=alcoholic;
	}
	//Does Drink need ID
	public boolean requiresID(){
		return Alcoholic;
	}
	//Clones item
	public Item Clone(){
		return new Drink(this.getName(),this.getPrice(),this.Alcoholic);
	}
	

}
