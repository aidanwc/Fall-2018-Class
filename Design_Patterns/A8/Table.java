//Aidan Weber-Concannon
//260708481


import java.util.ArrayList;
import java.util.List;

public class Table<T> {
	private ArrayList<String[]> table =new ArrayList<String[]>();
	private int numOfColumns;
	private Strategy<T> formatting;
	
	
	//Constructs list takes a List of column names, ArrayList.get(0) will have header, takes strategy
	public Table(List<String> columns,Strategy<T> s){
		numOfColumns=columns.size();
		formatting=s;
		
		//Copy header to table
		String[] header =new String[numOfColumns];
		for(int i=0;i<numOfColumns;i++){
			header[i]=columns.get(i);
		}
		//Add header 
		table.add(header);
		
	}
	
	//Add an object to list 
	public void addToTable(T object){
		String[] entry= formatting.makeEntry(object);
		table.add(entry);
	}
	
	//Remove an object from list 
	public void removeFromTable(T object){
		//Turn object in to comparable form
		String[] entry= formatting.makeEntry(object);
		
		for (int i = 0; i < table.size(); i++) {
			String[] temp = table.get(i);
			
			if(equalEntries(entry, temp)){
				table.remove(i);
				break;
			}

		}
	}
	
	//Compares two entries to see if they are equal, used by remove 
	public boolean equalEntries(String[] first,String[] second){
		for (int i = 0; i < numOfColumns; i++) {
			if(!first[i].equals(second[i])){
				return false;
			}
		}
		return true;
	}
	
	//Prints table
	public void printTable(){
		
		//Get header and make format
		String[] header=table.get(0);
		String[] entry;
		String format="";
		int headerSize=0;//Size of Line break
		String line="";
		
		//Gets size of header 
		for(int i=0;i<numOfColumns;i++){		
			format+="%"+(header[i].length()+3)+"s";
			headerSize+=header[i].length()+3;
		}
		//Makes line equal to header size 
		for(int i=0;i<headerSize;i++){
			line+="-";
		}
		
		//Print table 
		for(int i=0;i<table.size();i++){
			//Get entry
			entry=table.get(i);
			
			//Print line after header 
			if(i==1){
				System.out.println(line);
			}
			
			//print each entry
			for(int j=0;j<numOfColumns;j++){
				format="%"+(header[j].length()+3)+"s"; //Format, add 3 so that it looks nicer 
				
				//If end of entry add a new line 
				if(j==numOfColumns-1){
					format+="\n";
				}
				//Print entry 
				System.out.format(format, entry[j]);
			}
			
			
		}
		
	}
	
}
