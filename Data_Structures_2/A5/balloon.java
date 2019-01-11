package A5;
//
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;
//
public class balloon {
	private int numberOfBalloons;
	private int[]  room;//each array slot will hold the height of balloon or a zero if empty 
	
	public balloon(int numberOfBalloons){
		this.numberOfBalloons=numberOfBalloons;
		room=new int[numberOfBalloons];
	}
	
	public int getNumberOfBalloons() {
		return numberOfBalloons;
	}

	public int[] getRoom() {
		return room;
	}
	//Greedy algorithm, fires arrow at height of left most balloons height
	public int minArrows(){
		int arrowsFired=0;
		int[] copyRoom=room.clone();
		
		for(int i=0;i<copyRoom.length;i++){
			int height=copyRoom[i];//fire at height of leftmost height
			if(copyRoom[i]!=0){
				copyRoom= fire(copyRoom, height);
				arrowsFired++;
			}
		}
		return arrowsFired;
		
	}
	//Finds minimum arrows for multiple rooms
	public static int[] findMinimums(balloon[] rooms){
		int[] results = new int[rooms.length];
		for(int i=0;i<rooms.length;i++){
			balloon room=rooms[i];
			results[i]=room.minArrows();
		}
		return results;
	}
	
	//fires an arrow at height 
	public static int[] fire(int[] room,int height){
		int currentHeight=height;
		for(int i=0;i<room.length;i++){
			if(room[i]!=0&&currentHeight==room[i]){
				room[i]=0;
				currentHeight--;
			}
			if(currentHeight==0){
				break;
			}
		}
		return room;
	}
	//Reads system from file 
	public static balloon[] readFile(String file){
		try{
			FileReader fr = new FileReader(file);//Make Reader 
			BufferedReader br = new BufferedReader(fr);
			String currentLine = "";
	       
			currentLine=br.readLine();//Get number of problems
			int numberOfProblems=Integer.parseInt(currentLine);
			balloon[] rooms= new balloon[numberOfProblems];
			
			currentLine=br.readLine();
			String[] sizeSubProblem= currentLine.split(" ");//get number of balloons in each room
			
			if(sizeSubProblem.length!=numberOfProblems){ //sanity check
				br.close();
				fr.close();
				throw new IOException("Files first line does not match second!");
			}
			
			//Make size of each array
			for(int i=0;i<numberOfProblems;i++){
				int numBalloons=Integer.parseInt(sizeSubProblem[i]);
				rooms[i]=new balloon(numBalloons);
			}
			
			//Initilize each room
			for(int i=0;i<numberOfProblems;i++){
				balloon current= rooms[i];
				
				int numBalloons =current.getNumberOfBalloons();
				int balloonsCreated=0;
				int[]room=current.getRoom();
				String[] tokens;
				//Fill in room
				while(balloonsCreated<numBalloons){
					currentLine=br.readLine();
					tokens =currentLine.split(" ");
					for(String height:tokens){
						room[balloonsCreated]=Integer.parseInt(height);
						balloonsCreated++;
					}
				}
			}
			br.close();
			fr.close();
			return rooms;
	        
		
		
		
		}catch(FileNotFoundException e){
			System.out.println("File Not found");
		}catch(IOException e){
			System.out.println(e.getMessage());
		}catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	//Writes results to file 
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
		balloon[] input=readFile("testBalloons.txt");//fix this after!!
		int[] output= findMinimums(input);
		writeFile("testBalloons_solution.txt",output);
		System.out.println("Done");

		
	}
}
