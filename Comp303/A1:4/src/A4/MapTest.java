package A4;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
* Junit tester mehthods to be used on the map class.
*
* @author  Aidan Weber-Concannon
* @version 1.0
* @since   2018-10-11 
*/
public class MapTest  {
    
  
   /**
   * This method tests whether a map is created, supplies a true and false case 
   */
   @Test
    public void testMap(){
       
        Map testTrue = new Map(4,4);
        Map testFalse = new Map(-3,0);
        assertEquals(4,testTrue.getMaxRow());
        assertEquals(-1,testFalse.getMaxRow());//default value for map that has not been created 
    }

   /**
   * This method tests the correctRow method, with boundry values 
   */
   @Test
    public void testCorrectRow(){
	     Map fourMap = new Map(4,4);
	   	assertFalse(fourMap.correctRow(-1));
        assertTrue(fourMap.correctRow(0));
        assertTrue(fourMap.correctRow(3));
        assertFalse(fourMap.correctRow(4));

    }

  /**
   * This method tests the correctWidth method, with boundry values 
   */
    @Test
    public void testCorrectWidth(){
    	Map fourMap = new Map(4,4);
    	assertFalse(fourMap.correctWidth(-1));
        assertTrue(fourMap.correctWidth(0));
        assertTrue(fourMap.correctWidth(3));
        assertFalse(fourMap.correctWidth(4));

    }

    /**
   * This method tests the correctCharacter method, with {G, ~, #, A, Z, 4}
   */
    @Test
    public void testCorrectCharacter(){
    	Map fourMap = new Map(4,4);
    	assertFalse(fourMap.correctCharacter('A'));
        assertTrue(fourMap.correctCharacter('~'));
        assertTrue(fourMap.correctCharacter('G'));
        assertTrue(fourMap.correctCharacter('#'));
        assertFalse(fourMap.correctCharacter('Z'));
    }

   /**
   * This method tests the setValue method with a correct input, incorrect cordinates and an incorrect character.
   */
    @Test
    public void testSetValue(){
    	        Map fourMap = new Map(4,4);
                fourMap.setValue(-4,18,'G'); //Attempts to set value
                fourMap.setValue(0,3,'G'); //Should return true 
                fourMap.setValue(0,0,'@'); //Should return false 

                assertEquals('G',fourMap.getValue(0,3));
                assertFalse('@'==fourMap.getValue(0,0));
                assertFalse('G'==fourMap.getValue(-4,18));
   }

    /**
   * This method tests the getValue method, getValue returns character 'X' if not a valid place on map.
   */
    @Test
    public void testGetValue(){
    	 Map fourMap = new Map(4,4);
    	 assertEquals('~',fourMap.getValue(0,0)); //map has '~' in every
         assertEquals('~',fourMap.getValue(0,0));
         assertEquals('X',fourMap.getValue(4,3));
         assertEquals('X',fourMap.getValue(3,4));
    }
    /**
   * This method tests the toString method 
   */
    @Test
    public void testToString(){
      Map fourMap = new Map(4,4);
      String toString= fourMap.toString();
      String expected= "~~~~\n~~~~\n~~~~\n~~~~\n";
      assertTrue(expected.equals(toString));
    }


}
