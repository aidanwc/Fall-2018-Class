//Name: Aidan Weber-Concannon
//ID: 260708481


import java.util.ArrayList;
import java.util.LinkedList;

public class Interactive {
	public static void main(String[]args){
		
		ArrayList<Item> inventory = new ArrayList<Item>();//Initialize inventory 
		Item special= new Item("Ceasar",12.00,'S'); //special of day
		LinkedList<String> commands = new LinkedList<String>(); //Used in place of scanner 
		
		//Initialize sequence of commands, with user would use a scanner instead
		commands.add("2"); 
		commands.add("Clams");
		commands.add("Burger");
		commands.add("Brownie");
		commands.add("1");
		commands.add("Beer");
		commands.add("1");
		commands.add("Ceasar");
		commands.add("3");
		
		//Add Items to menu
		inventory.add(new Item("Waffles",6.00,'M'));
		inventory.add(new Item("Burger",7.50,'M'));
		inventory.add(new Item("Clams",4.00,'A'));
		inventory.add(new Item("Brownie",2.00,'D'));
		inventory.add(new Item("Fries",3.00,'S'));
		
		//Add drinks
		inventory.add(new Drink("Juice",1.50,false));
		inventory.add(new Drink("Beer",4.00,true));
		
		//Call new order
		newOrder(inventory,special,commands);
		
		
	}
	
	
	
	//New order for each customer, to use without driver change scanner to an actual scanner object 
	public static void newOrder(ArrayList<Item> inventory,Item special,LinkedList<String> scanner){
		
		//Used for driver
		String scanned;
		int parsed;
		
		//Copy menu
		Menu menu = new Menu();
		for(Item i:inventory){
			menu.addItem(i.Clone());
		}
		
		//Set Item of day 
		menu.todaySpecial(special);
		
		//Initialize loop and create new order
		System.out.println("New Order!\n");
		boolean loop = true;
		
		Order myOrder= new Order();
		System.out.println(menu);//prints menu
		
		while(loop){
			//Print menu 
			System.out.println();
			System.out.println("(1) Add an item, (2)Add a meal, (3)Pay and close. Please enter a number!");
			
			try{ 
				parsed= Integer.parseInt(scanner.removeFirst()) ; //Get command, would have scanner here 
			}catch(NumberFormatException e){
				continue;//Get another input 
			}
			
			System.out.println("You entered: "+parsed);//Show on console
			System.out.println();
			
			//Add item to menu
			if(parsed==1){
				System.out.println("Please enter name of item to add.");
				
				//Get input from scanner and attempt to retrieve item
				scanned = scanner.removeFirst();
				Item choice =menu.getItem(scanned);
				System.out.println("You entered: "+scanned);
				
				//If item exists in menu
				if(choice!=null){
					
					//If the item is a Drink 
					if(choice.getType()=='B'){
						
						//Cast to drink to see if it is alcoholic 
						Drink d= (Drink) choice;
						
						//Print warning 
						if(d.requiresID()){
							System.out.println("You must be 18 years or older to buy this!");
						}
					}
					//Then add item to order 
					myOrder.addItem(choice);
					System.out.println("Item Added!");
					continue;
				}
				//Item not found, goes back to menu 
				System.out.println("Item not found, please try again!");
			}
			//Chooses to order meal
			else if(parsed==2){
				boolean validMeal=false;
				
				//Loops while meal is not valid 
				while(!validMeal){	
					Item appetizer=null;
					Item main=null;
					Item dessert=null;
					
					System.out.println("Please enter an appetizer.");
					String name=scanner.removeFirst();//Would use scanner here 
					appetizer=menu.getItem(name);
					System.out.println("You entered: "+name);
					
					System.out.println("Please enter a main.");
					name=scanner.removeFirst();//Would use scanner here 
					main=menu.getItem(name);
					System.out.println("You entered: "+name);
					
					System.out.println("Please enter a dessert.");
					name=scanner.removeFirst();//Would use scanner here
					dessert= menu.getItem(name);
					System.out.println("You entered: "+name);
					
					if(appetizer==null||main==null||dessert==null){//Avoid null exceptions 
						System.out.println("One of above items was not found, check your spelling!");
						continue;
					}
					
					
					if(appetizer.getType()=='A'&&main.getType()=='M'&&dessert.getType()=='D'){
						dessert=dessert.Clone();
						dessert.changeDiscount();//Apply discount to cloned item
						
						myOrder.addItem(appetizer);
						myOrder.addItem(main);
						myOrder.addItem(dessert);
						validMeal= true;//Add items to order and exit loop
						System.out.println("Meal Added!");
					}
				}
			}
			
			//User chooses to quit
			else if(parsed==3){
				System.out.println(myOrder);//Print order 
				System.out.println("Total: $"+myOrder.getTotal());//Print total
				System.out.println("Customer pays!");//Customer pays, customer class could be added in future 
				
				loop=false;//Exit loop
			}else{
				System.out.println("Please enter 1, 2, or 3");//user entered an incorrect input
			}
		}
	}
		
		
}


