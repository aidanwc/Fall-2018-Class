package A5;
//
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;
//
public class islands {
	private int rows;
	private int length;
	private char[][] map;
	//Constructor
	public islands(int rows, int length){
		this.rows=rows;
		this.length=length;
		map=new char[rows][length];
	}
	
	//Read file 
	public int getRows() {
		return rows;
	}

	public int getLength() {
		return length;
	}

	public char[][] getMap() {
		return map;
	}
	public int countIslands(){
		int numIslands=0;
		char[][]map=this.getMap();
		map=map.clone();//clone as destroying originil
		
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.length;j++){
				if(map[i][j]=='-'){//if discover a new island then colour it
					colour(i,j,map);
					numIslands++;
				}
			}
		}
		return numIslands;
	}
	
	//Colour method that uses DFS of array
	public void colour(int row, int column,char[][] map){
		if(row<0||column<0||row>=this.rows||column>=this.length){//check that bounds are correct, base case 
			return;
		}else if(map[row][column]=='#'){//check only after we make sure is within array
			return;
		}else{
			map[row][column]='#';
			
			colour(row-1, column,map); //check the closest tiles 
			colour(row+1,column,map);
			colour(row,column+1,map);
			colour(row,column-1,map);
			
		}
		
	}
	public static int[] countMaps(islands[] maps){
		int[] results = new int[maps.length];
		for(int i=0;i<maps.length;i++){
			islands map=maps[i];
			results[i]=map.countIslands();
		}
		return results;
	}
	public static islands[] readFile(String file){
		try{
			FileReader fr = new FileReader(file);//Make Reader 
			BufferedReader br = new BufferedReader(fr);
			String currentLine = "";
	       
			currentLine=br.readLine();//Get number of problems
			int numberOfProblems=Integer.parseInt(currentLine);
			islands[] maps= new islands[numberOfProblems];
			
			//Initialize each one 
			for(int i=0;i<numberOfProblems;i++){
				currentLine=br.readLine();
				String[] tokens= currentLine.split(" ");//get number of balloons in each room
				
				int rows= Integer.parseInt(tokens[0]);
				int length=Integer.parseInt(tokens[1]);
				
				maps[i]=new islands(rows,length);
				char[][]map=maps[i].getMap();
				
				for(int j=0;j<rows;j++){
					String line=br.readLine();
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
	//write file 
	public static void writeFile(String fileName,int[] results){
		try {
			File file =new File(fileName);
			if (!file.exists()) {
			     file.createNewFile();
			 }
			FileWriter fw= new FileWriter(file,false);
			BufferedWriter bw=new BufferedWriter(fw);
			
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
	public static void main(String[] args){
		islands[] input=readFile("testIslands.txt");
		int[] output= countMaps(input);
		writeFile("testIslands_solution.txt",output);
		System.out.println("Done");

		
	}
	
}
