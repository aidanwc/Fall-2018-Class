//Name: Aidan Weber-Concannon
//ID: 260708481


import java.util.ArrayList;

//Holds inventory of everything available 
public class Menu {
	
	private ArrayList<Item> menu;
	private Item itemOfDay=null;//One special on the menu 
	
	//Constructor 
	public Menu(){
		menu= new ArrayList<Item>();
	}
	//Adds item 
	public void addItem(Item i){
		menu.add(i);
	}
	
	//Sets item of day, if there was previous item there sets it back to normal
	public void todaySpecial(Item i){
		
		//Item already there 
		if(itemOfDay!=null){
			
			//Change price in menu to original 
			menu.remove(itemOfDay);
			itemOfDay.changeDiscount();
			menu.add(itemOfDay);
			
		}
		//Item exists in menu already, remove so there are no duplicates 
		if(this.getItem(i.getName())!=null){
			Item duplicate =this.getItem(i.getName());
			menu.remove(duplicate);
		}
		
		//Add item to menu, changes discount, sets as item of day 
		i.changeDiscount();
		menu.add(i);
		itemOfDay=i;
	}
	
	//Goes through list and finds item with same name 
	public Item getItem(String name){
		for(Item i: menu){//Iterates through list
			if(name.toLowerCase().equals(i.getName().toLowerCase())){
				return i; 
			}
			
		}
		return null;
	}
	
	//Returns a string of menu
	public String toString(){
		String s ="Menu: \n";
		for(Item i: menu){
			s+= i.getName()+" $"+i.getPrice()+" ("+i.getTypeString()+") ";
			if(i.hasDiscount()){
				s+= " / Non Discounted price: $"+ i.getPrice()*2;//Concatenates discounted price 
			}
			s+='\n';
		}
		return s;
	}
}
