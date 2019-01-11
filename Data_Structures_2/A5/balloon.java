
//Aidan Weber-Concannon
//260708481
//Collaborators:Discussion board, Alex wang  

import java.io.*;
// import java.util.*;
// import java.util.regex.*;
// import java.math.*;
// import static java.lang.System.out;

public class balloon {
	private int numberOfBalloons;//Number of balloons in room
	private int[]  room;//each array slot will hold the height of balloon or a zero if empty 
	
	//Constructor
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
		int[] copyRoom=room.clone(); //Clones room
		
		//Checks each slot for balloon and shoots if not empty
		for(int i=0;i<copyRoom.length;i++){
			int height=copyRoom[i];//fire at height of leftmost height
			
			if(copyRoom[i]!=0){
				copyRoom= fire(copyRoom, height);
				arrowsFired++; //increases number of arrows used 
			}
		}
		return arrowsFired;
		
	}
	//Finds minimum arrows for multiple rooms
	public static int[] findMinimums(balloon[] rooms){
		int[] results = new int[rooms.length]; //results for n rooms
		
		for(int i=0;i<rooms.length;i++){
			balloon room=rooms[i];
			results[i]=room.minArrows(); //calculates minimum arrows 
		}
		return results;
	}
	
	//fires an arrow at height specified 
	public static int[] fire(int[] room,int height){
		//Arrow height
		int currentHeight=height;
		
		for(int i=0;i<room.length;i++){
			if(room[i]!=0&&currentHeight==room[i]){
				room[i]=0;
				currentHeight--;//balloon popped, height must decrease 
			}
			
			if(currentHeight==0){//if height is zero then break loop
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
			
			//Initilize each room with balloons 
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
						room[balloonsCreated]=Integer.parseInt(height); //put in balloon at height
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
			System.out.println(e.getMessage()); //for parsing 
		}
		return null;
	}
	
	//Writes results to file 
	public static void writeFile(String fileName,int[] results){
		try {

			//makes file if not there 
			File file =new File(fileName);
			if (!file.exists()) {
			     file.createNewFile();
			 }

			 //open writer 
			FileWriter fw= new FileWriter(file,false);
			BufferedWriter bw=new BufferedWriter(fw);
			
			//writes each result to a line
			for(int i:results){
				String line =""+i;
				bw.write(line);
				bw.newLine();
			}

			//close writer 
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args){
		balloon[] input=readFile("testBalloons.txt");//Get rooms 
		int[] output= findMinimums(input); //Conduct algorithim 
		writeFile("testBalloons_solution.txt",output); //write results 
		 

		
	}
}
