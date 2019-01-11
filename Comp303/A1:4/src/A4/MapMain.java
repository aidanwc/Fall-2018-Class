package A4;


/**
* The MapMain program implements an application that
* instanciates a map object and asks user for input.
*
* @author  Aidan Weber-Concannon
* @version 1.0
* @since   2018-10-11 
*/

import java.util.Scanner;
public class MapMain {
	/**
   * Main method, calls the playGame method
   * @param args Unused 
   */
    public static void main(String[] args) {
        playGame(); //Start a new game
    }
	/**
   * Creates a map object and queues user for input, displays map on screen following termination of program.
   */
    public static void playGame(){
        String input = ""; //A string for parsing
        int rows=-1;
        int width=-1;
        char characterInput;
        boolean play= true; //boolean variable for whether to continue

        //Import scanner
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Map!");

        //Ask for row size of map
        while(rows<=0) {
            System.out.print("Please input the maximum Number of Rows:");
            input = scan.next();

            rows = safeParse(input); //Parses input safetly
            if (rows > 0) break;     //If correct value, exits loop
            System.out.println("A row must be a valid integer greater than 0.");


        }

        //Asks for column size of map
        while(width<=0) {
            System.out.print("Please input the maximum Number of Columns:");
            input = scan.next();

            width = safeParse(input); //Safetly parses
            if (width >0 ) break; //Breaks loop if correct value
            System.out.println("A row must be a valid integer greater than 0.");
        }

        //Creates a new map
        Map map= new Map(rows,width);

        //If map has row size of -1 it has failed to be initiated
        if(map.getMaxRow()==-1){
            System.out.println("Error, map creation failed, program terminated.");
            return;
        }

        //Otherwise map has been created
        System.out.println("Map has been created.");
        System.out.println("Please add an object to the Map(~ for water, G for grass, # for tree)");

        //Loop that asks user to change map
        while (play) {
            rows = -1; //Resets inputs to false statements
            width = -1;
            characterInput = 'X';
            boolean correctInput=false;

            //Gets Row of change
            while (rows < 0 || rows >= map.getMaxRow()) {
                System.out.print("Row:");
                input = scan.next();

                rows = safeParse(input); //Safely parses input
                if (map.correctRow(rows)) break; //Checks whether row is within bounds
                System.out.println("Invalid row! Must be between 0 and " + (map.getMaxRow() - 1)); //Error message if incorrect input
            }

            //Gets Column of change
            while (width < 0 || width >= map.getMaxWidth()) {
                System.out.print("Width:");
                input = scan.next();

                width = safeParse(input); //Safely parses
                if (map.correctWidth(width)) break; //Checks whether row is whithing bounds
                System.out.println("Invalid width! Must be between 0 and " + (map.getMaxWidth() - 1)); //Error message


            }
            //Gets character to replace
            while (!map.correctCharacter(characterInput)) {
                System.out.print("Character:");
                characterInput = scan.next().charAt(0); //Gets first char in string

                if (map.correctCharacter(characterInput)) break; //If valid breaks, otherwise it asks again
                System.out.println("Invalid character! Must be a ~ G or # ");


            }

            //Takes location and character and changes it in array
            map.setValue(rows, width, characterInput);
            System.out.println(characterInput + " has been added at " + (rows - 1) + ", " + (width - 1)); //Subtracts 1 to deal with rows starting at zero

            //Loop to ask user to play again
            while (!correctInput) {
                System.out.println("Would you like to enter another character?(Y/N)");
                characterInput = scan.next().charAt(0);

                if (characterInput == 'Y' || characterInput == 'y') {
                    play=true; //Causes game to ask for input again
                    correctInput=true;
                }else if (characterInput=='N'||characterInput=='n'){
                    play=false; //Terminates game
                    correctInput=true;
                }else{
                    System.out.println("Unvalid input, Please enter Y or N."); //Asks again
                }
            }

        }
        //Prints map and returns
       System.out.println(map);
    }

    /**
   * This method saftely parses strings inorder to avoid Number format exceptions it will then convert it to an int
   * @param text A string to parse 
   * @return int - returns an integer version of the string. 
   */
    public static int safeParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1; //Returns -1 if a non integer is entered
        }
    }
}