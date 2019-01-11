package A4;


/**
* The Map Class is a class consisting of a 2D array that can be 
* edited and printed with differant values.
*
* @author  Aidan Weber-Concannon
* @version 1.0
* @since   2018-10-11 
*/
public class Map {

    private char[][]Map; //2D Array
    private int maxRow=-1; //rows, given default -1 for error checkng
    private int maxWidth=-1; //colums which are refered to as width through out code, given default -1 for error checking


     /**
   * Constructor for the map object 
   * @param row int number of rows 
   * @param width int number of columns 
   */
    public Map(int row,int width){
        if (row<=0||width<=0){ //Error Checks for invalid boundries
            return;
        }

        maxRow= row;
        maxWidth=width;
        Map= new char[maxRow][maxWidth];

        //Fills map with water in each cell
        for(int i=0;i<maxRow;i++){
            for(int j=0;j<maxWidth;j++){
                Map[i][j]= '~';
            }
        }

    }
    /**
   * This method tests whether a row exists in the map object
   * @param test An integer of a row 
   * @return boolean - This returns if the row exists.
   */
    public boolean correctRow(int test){
        if(test>=0&&test<maxRow) return true;
        return false;
    }
    /**
   * This method tests whether a column exists in the map object
   * @param test An integer of a column
   * @return boolean - This returns truth value of whether row exists 
   */
     public boolean correctWidth(int test){
        if(test>=0&&test<maxWidth) return true;
        return false;
    }
   /**
   * This method tests whether an inputed character is a recognized Landmark 
   * @param test A character representing a landmark
   * @return boolean - This returns truth value of whether the character is a valid entry 
   */
    public boolean correctCharacter(char test){
        if(test=='~'||test=='G'||test=='#') return true;
        return false;
    }

    /**
   * This method takes a location on the map and sets its location equal to the character input.
   * If given an invalid input it leaves the map unchanged. 
   * @param row  An integer row location 
   * @param width An integer column location 
   * @param input A character representing an object to be placed in the map 
   */
    public void setValue(int row, int width,char input){
       if(this.correctWidth(width)&&this.correctRow(row)&&this.correctCharacter(input)) { //Checks that values are within bounds
           Map[row][width]= input; //sets input in map cell
       }else {
           return;//Does nothing 
       }

    }
     /**
   * A method that gets the value located at a cordinate location and returns it to the user 
   * @param row  An integer row location 
   * @param width An integer column location 
   * @return char - the object located at the location at the map.('X' if invalid)
   */
    public char getValue(int row, int width){
        if (this.correctRow(row)&&this.correctWidth(width)){ //If within bounds returns character
            return Map[row][width];
        }else{
            return 'X'; //Error value if fails
        }

    }

   /**
   * A method that produces a 2D string representation of the Map object  
   * @return String - a rowXcolumn sized string representation of the map
   */
    public String toString(){
        String s = "";
        for(int i=0;i<maxRow;i++){
            for(int j=0;j<maxWidth;j++){
                s+= Map[i][j];
            }
            s+= "\n";
            

        }
        return s;
    }
    /**
   * A method that returns the max row value of map class   
   * @return int - This returns the maximum row of the map 
   */
    public int getMaxRow(){
        return maxRow;
    }
   /**
   * A method that returns the max width value of map class   
   * @return int - This returns the maximum width of the map 
   */
    public int getMaxWidth(){
        return maxWidth;
    }

}
