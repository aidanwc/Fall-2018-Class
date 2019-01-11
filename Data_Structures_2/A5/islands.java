
//Aidan Weber-Concannon
//260708481
//Collaborators:Discussion board, Alex wang  

import java.io.*;
// import java.util.*;
// import java.util.regex.*;
// import java.math.*;
// import static java.lang.System.out;
//
public class islands {
	private int rows;
	private int length;
	private char[][] map; //stores image 
	
	//Constructor
	public islands(int rows, int length){
		this.rows=rows;
		this.length=length;
		map=new char[rows][length];
	}
	
	
	public int getRows() {
		return rows;
	}

	public int getLength() {
		return length;
	}

	public char[][] getMap() {
		return map;
	}
	//Counts Islands by counting and then deleting island 
	public int countIslands(){
		
		int numIslands=0;
		char[][]map=this.getMap();
		map=map.clone();//clone as algorithim destroys original 
		
		//checks whole map 
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.length;j++){
				if(map[i][j]=='-'){//if discover a new island then colour it
					colour(i,j,map);
					numIslands++; //count island 
				}
			}
		}
		return numIslands; 
	}
	
	//Colour method that uses DFS 
	public void colour(int row, int column,char[][] map){
		if(row<0||column<0||row>=this.rows||column>=this.length){//check that bounds are correct, base case 
			return;
		}else if(map[row][column]=='#'){//check only after we make sure is bounds are within array
			return;
		}else{
			//colour
			map[row][column]='#';
			
			//four possible places to check
			colour(row-1, column,map); 
			colour(row+1,column,map);
			colour(row,column+1,map);
			colour(row,column-1,map);
			
		}
		
	}
	//Counts islands on multiple maps
	public static int[] countMaps(islands[] maps){
		int[] results = new int[maps.length];//stores results in array
		
		//Iterate through maps 
		for(int i=0;i<maps.length;i++){
			islands map=maps[i];
			results[i]=map.countIslands();//call algorthim
		}
		return results;
	}

	//Read file 
	public static islands[] readFile(String file){
		try{
			FileReader fr = new FileReader(file);//Make Reader 
			BufferedReader br = new BufferedReader(fr);
			String currentLine = "";
	       
			currentLine=br.readLine();//Get number of problems
			int numberOfProblems=Integer.parseInt(currentLine);
			islands[] maps= new islands[numberOfProblems];
			
			//Initialize each object 
			for(int i=0;i<numberOfProblems;i++){
				currentLine=br.readLine();
				String[] tokens= currentLine.split(" ");//gets rows and lengths
				
				int rows= Integer.parseInt(tokens[0]);
				int length=Integer.parseInt(tokens[1]);
				
				maps[i]=new islands(rows,length); //make new island 
				char[][]map=maps[i].getMap(); //get map 
				
				for(int j=0;j<rows;j++){
					String line=br.readLine(); //copy each cell of array line by line 
					for(int p=0;p<length;p++){
						map[j][p]=line.charAt(p);
					}
				}
				
			
			}
			
			br.close();
			fr.close();
			return maps;
	        
		
		
		
		}catch(FileNotFoundException e){
			System.out.println("File Not found");
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	//write results to file, same code as balloons 
	public static void writeFile(String fileName,int[] results){
		try {
			
			//make file if needed 
			File file =new File(fileName);
			if (!file.exists()) {
			     file.createNewFile();
			 }
			
			//open file 
			FileWriter fw= new FileWriter(file,false);
			BufferedWriter bw=new BufferedWriter(fw);
			
			//write results to line 
			for(int i:results){
				String line =""+i;
				bw.write(line);
				bw.newLine();
			}
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Driver method
	public static void main(String[] args){
		islands[] input=readFile("testIslands.txt");
		int[] output= countMaps(input);
		writeFile("testIslands_solution.txt",output);


		
	}
	
}
