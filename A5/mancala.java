package A5;
//
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;
//
public class mancala {
	//Dynamic programming 
	public static int minRocks (int[] board,HashMap<int[],Integer> memory){
		if(memory.get(board)!=null){
			return memory.get(board);
		}
		
		int count=0;
		int[]temp = null;//Copy array
		
		//Counts the numbers of rocks in array 
		for(int i=0;i<12;i++){
			if(board[i]==1)count++;
		}
		//Goes through each index 
		for(int i=0;i<12;i++){
			//If can jump right jumps right 
			if(i+2<12&&board[i]==1&&board[i+1]==1&&board[i+2]==0){
				temp=board.clone();
				temp[i]=temp[i+1]=0;
				temp[i+2]=1;
				int left=minRocks(temp,memory);
				
				count =Math.min(count, left);//min of two values 
				memory.put(temp, left);
			}
			//If can jump left, jumps left 
			if(i-2>=0&&board[i]==1&&board[i-1]==1&&board[i-2]==0){
				temp=board.clone();
				temp[i]=temp[i-1]=0;
				temp[i-2]=1;
				int right =minRocks(temp,memory);
				
				count=Math.min(count, right);//Maximum of three values 
				memory.put(temp, right);
			}	
		}
						
		return count;
	}
	
	public static int[] playGames(int[][] boards){
		int[] results= new int[boards.length];
		int place=0;
		
		for(int[] board: boards){
			HashMap<int[],Integer> memory =new HashMap<int[], Integer>();
			results[place]=minRocks(board,memory);
			place++;
		}
		return results;
	}


	public static int[][] readFile(String file){
		try{
			FileReader fr = new FileReader(file);//Make Reader 
			BufferedReader br = new BufferedReader(fr);
			String currentLine = "";
	       
			currentLine=br.readLine();//Get number of problems
			int numberOfProblems=Integer.parseInt(currentLine);
			int[][] boards= new int[numberOfProblems][12];
			
			//Initialize each one 
			for(int i=0;i<numberOfProblems;i++){
				currentLine=br.readLine();
				String[] tokens= currentLine.split(" ");//get each array thing 
				
				for(int j=0;j<12;j++){
					boards[i][j]=Integer.parseInt(tokens[j]);
				}
				
			
			}
			
			br.close();
			fr.close();
			return boards;
	        
		}catch(FileNotFoundException e){
			System.out.println("File Not found");
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
		return null;
	}











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
		final long startTime = System.currentTimeMillis();
		int input[][]=readFile("/Users/Aidan/Desktop/COMP2018/Comp251/CS251/src/A5/testMancala.txt");
		
		int[] output= playGames(input);
		writeFile("/Users/Aidan/Desktop/last.txt",output);
		System.out.println("Done");

		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime));
	}
	
	
	
	
}
