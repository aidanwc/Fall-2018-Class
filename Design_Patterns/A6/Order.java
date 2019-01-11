//Name: Aidan Weber-Concannon
//ID: 260708481

import java.util.ArrayList;

public class Order {
	private ArrayList<Item> order;//List of items 
	private double total;//Total for order
	
	//Constructor 
	public Order(){
		order=new ArrayList<Item>();
		total=0.0;//Total for order begins at 0
	}
	
	//Add item to list 
	public void addItem(Item i){
		order.add(i);
		
		//Calculate total with discount
		if(i.hasDiscount()){
			total+=(i.getPrice());
		}else{
			total+=i.getPrice();
		}
	}
	
	//Returns a string representing the order 
	public String toString(){
		String s ="Your Order: \n";
		for(Item i: order){
			s+= i.getName()+" $"+i.getPrice();
			if(i.hasDiscount()){
				s+= " / Non Discounted price: $"+ i.getPrice()*2; //Shows discounted price 
			}
			s+='\n';
		}
		return s;
	}
	
	//Return total value 
	public double getTotal(){
		return total;
	}
	
}
