//Aidan Weber-Concannon
//260708481


import java.util.ArrayList;

public class Driver {
	
	public static void main(String[] args) {
		
		//Using Strategy create table, strategy defines the headers 
		Strategy<Food> strategy=new foodStrategy<Food>();
		ArrayList<String> columns=strategy.headerNames();
		
		//Make Table
		Table<Food> table1= new Table<Food>(columns,strategy);
		
		//Make Foods 
		Food banana = new Food("Banana",27,0,1);
		Food egg=new Food("Egg",0,5,6);
		Food bagel=new Food("Bagel",56,2,11);
		
		//Add items 
		table1.addToTable(banana);
		table1.addToTable(egg);
		table1.addToTable(bagel);
	
		
		
		//Print table 
		table1.printTable();
		System.out.println();
		
		
		//Using template//
		columns=new ArrayList<String>();
		columns.add("Name");
		columns.add("Calories");
		
		//Give list of headers 
		TableTM<Food> table2=new foodTable<Food>(columns);
		table2.addToTable(banana);
		table2.addToTable(egg);
		table2.addToTable(bagel);
		
		//Print table 
		table2.printTable();
		
	}
}
