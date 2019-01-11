//Name: Aidan Weber-Concannon
//ID: 260708481


public class Item {
	
	private String name;
	private double price;
	private char type;
	private boolean discount;
	
	//Constructor for item
	public Item(String name, double price, char type){
		this.name=name;
		this.price=price;
		//If valid type makes sure that it is upper case 
		if(validType(type)){
			this.type=Character.toUpperCase(type);
		}
	}
	//Getter 
	public String getName(){
		return name;
	}
	
	//Gets priced based on discount 
	public double getPrice(){
		if(this.hasDiscount()){
			return price/2;
		}else{
			return price;
		}
	}
	//Returns type 
	public char getType(){
		return type;
	}
	
	//Used by menu class to decode
	public String getTypeString(){
		if(type=='A'){
			return "Appetizer";
		}else if(type=='M'){
			return "Main";
		}else if(type=='D'){
			return "Dessert";
		}else if(type=='B'){
			return "Beverage";
		}else if(type=='S'){
			return "Side";
		}else{
			return "";
		}
	}
	
	//Returns whether item has discount
	public boolean hasDiscount(){
		return discount;
	}
	
	//Changes discount from current value to opposite value 
	public void changeDiscount(){
		if(!discount){
			discount=true;
		}else{
			discount=false;
		}
	}
	
	//Checks if a valid type, each type uses first letter of name, B for Drink(Beverage) 
	private static boolean validType(char c){
		c=Character.toUpperCase(c);
		if(c=='A'||c=='M'||c=='D'||c=='S'||c=='B'){//
			return true;
		}
		return false;
	}
	//Used to add cloned item to order  
	public Item Clone(){
		return new Item(this.name,this.price,this.type);
	}
	
}
