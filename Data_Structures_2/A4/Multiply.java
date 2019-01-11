package A4;
//Aidan Weber-Concannon
//260708481
//No collaborators 

import java.util.*;
import java.io.*;

public class Multiply{

    private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }
    
    public static int[] naive(int size, int x, int y) {

        // YOUR CODE GOES HERE  (Note: Change return statement)
        int[] results= new int[2];//Make array
        int cost=0;
        int product=0;
        
        if(size==1){//Size is 1 bit
        	results[0]=x*y; //multiply 
        	results[1]=1; //Add 1 to cost 
        	return results;
        }else{
        	int m=(size/2)+(size%2);
        	int a=x>>m; //a=x/2^m
        	int b=x&((1<<m)-1); //b=x mod2^m
        	int c=y>>m; //c=y/2^m
        	int d=y&((1<<m)-1);//d=y mod 2^m
        	
        	//Call recursions 
        	int first[]=naive(m,a,c); 
        	int second[]=naive(m,b,d);
        	int third[]=naive(m,b,c);
        	int fourth[]=naive(m,a,d);
        	
        	//Get results 
        	int e=first[0]; 
        	int f =second[0];
        	int g=third[0];
        	int h=fourth[0];
        	
        	//Sum cost from children and add cost from current 
        	cost=first[1]+second[1]+third[1]+fourth[1];
        	cost+=(3*m);
        	product=(e<<(m<<1))+((g+h)<<m)+f; //Find product 
        	
        	results[0]=product;
        	results[1]=cost;
        	
        	return results; //return result
        
        }
        
    }

    public static int[] karatsuba(int size, int x, int y) {
        
        // YOUR CODE GOES HERE  (Note: Change return statement)
    	 int[] results= new int[2];
         int cost=0;
         int product=0;
         
         //condition check here 
         if(size==1){
         	results[0]=x*y; //Multiply
         	results[1]=1;  //Cost of one 
         	return results;
         }else{
        	
        	 int m=(size/2)+(size%2);
         	boolean oneNegative=false; //If one negative must change after 
        	
         	//If both negative then product is postive 
        	if(x<0&&y<0){
        		x=Math.abs(x);
        		y=Math.abs(y);
        	}
        	if((x<0&&y>0)||(y<0&&x>0)){//If one negative, treat as positive and then make negative after 
        		x=Math.abs(x);
        		y=Math.abs(y);
        		oneNegative=true;
        	}
         	
        	int a=x>>m;//Same as with naive 
         	int b=x&((1<<m)-1);
         	int c=y>>m;
         	int d=y&((1<<m)-1);
         	
         	int first[]=naive(m,a,c); //Same as with naive except use subsitiution 
        	int second[]=naive(m,b,d);
        	int third[]=naive(m,a-b,c-d);
        	
        	int e=first[0]; //Get results 
        	int f =second[0];
        	int g=third[0];
        	
        	cost=first[1]+second[1]+third[1]; //Sum cost of childern 
        	cost+=(6*m);  //cost of current
        	product= (e<<(m<<1))+((e+f-g)<<m)+f; //Find result
        	
        	if(oneNegative){
        		product=(-1*product); //If changed sign earlier, revers sign on product 
        	}
        	
        	results[0]=product;
        	results[1]=cost;
        	return results; //Return result1
        	
         }
        
        
    }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;
                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);
                    int y = randomInt(size);
                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);
            
                    if (resNaive[0] != resKaratsuba[0]) {
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];
                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;
                System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}