//Aidan Weber-Concannon
//260708481
//Collaborators: Discussion Board on mycourses
package A1;

import static A1.main.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initalized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
    	int x = key*A; //A*K mod 2^w>>(w-r), applying multiplication hash 
        x= x% power2(w);
        x= x>>(w-r); //h(x)
        
        x=x+i;//Add I and then apply modular arithmatic 
        return x%m; 
        		
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
        int jumpedOver=0;
       
        for(int i=0; i<m;i++){ //if reaches the end then the key was not added!!! 
        	int hashCode = probe(key,i);//get the hashcode for the current place 
        	
        	if(Table[hashCode]==key){//Checks whether key is already there 
        		break;
        	}
        	
        	if(Table[hashCode]==-1||Table[hashCode]==-5){ //if empty or nil break the loop 
        		Table[hashCode]=key;
        		break;
        	}
        	jumpedOver++;//Add one to collisions 
        }
        return jumpedOver;
        
        
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
        //ADD YOUR CODE HERE (CHANGE THE RETURN STATEMENT)
        int collisions= 0;
        
        for(int i=0; i<m;i++){ 
        	int hashCode = probe(key,i);//get the hashcode for the current place 
        	if(isSlotEmpty(hashCode)){ //if empty break the loop 
        		break;
        		
        	}if(key==Table[hashCode]){	
        		Table[hashCode]=-5;//replace with a marker that does not match any key, but that does not trigger isSlotEmpty
        		break;
        	}
        	collisions++;//Add one to collisions
        }
        return collisions;
        
   }

}
