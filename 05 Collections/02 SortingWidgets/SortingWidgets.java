// Name:
// Date:

import java.io.File;     
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This working code uses raw arrays.
 * Refactor code to use ArrayList<E> instead.
 */      
public class SortingWidgets
{
   public static void main(String[] args) throws Exception
   {
      Comparable[] apple = input("widgets.txt");  
      sort(apple);
      output(apple);
   }
   
   public static Comparable[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      Comparable[] array = new Comparable[57];	
      for(int k =0; k<57; k++)    		    //use a while-loop
      {								  
         int x = infile.nextInt();
         int y = infile.nextInt();
         array[k] = new Widget(x, y);      //don't use an index number
      }
      infile.close();
      return array;
   }
   
   public static void sort(Comparable[] array)
   {
      int maxPos;
      for(int k = 0; k < array.length; k++)		
      {
         maxPos = findMax(array, array.length - k - 1);
         swap(array, maxPos, array.length - k - 1);
      }
   }
   
   public static int findMax(Comparable[] array, int upper)
   {
      int maxPos = 0;
      for(int j = 1; j <= upper; j++)			
         if(array[j].compareTo(array[maxPos]) > 0)	
            maxPos = j;					
      return maxPos;
   }
   
   public static void swap(Comparable[] array, int a, int b)
   {
      Comparable temp = array[a];				
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void output(Object[] array)
   {
      for(int k = 0; k < array.length; k++)		//use the for-each loop
         System.out.println(array[k]);
   }
}

///////////////////////////////////////////////////////////////

class Widget implements Comparable<Widget>
{
   // fields
   private int myCubits, myHands;
   
   // constructors
   public Widget()
   {
      myCubits = myHands = 0;
   }
   
   public Widget(int x)
   {
      myCubits = x;
      myHands = 0;
   }
   
   public Widget(int x, int y)
   {
      myCubits = x;
      myHands = y;
   }
   
   public Widget(Widget arg)    //copy constructor
   {
      myCubits = arg.getCubits();
      myHands = arg.getHands(); 
   }
   
   //accessors and modifiers
   public int getCubits()
   {
      return myCubits;
   }
   
   public int getHands()
   {
      return myHands;
   }
   
   public void setCubits(int x)
   {
      myCubits = x;
   }
   
   public void setHands(int x)
   {
      myHands = x;
   }
   
   //other methods
   public int compareTo(Widget other)
   {
      if(myCubits < other.getCubits())
         return -1;
      if(myCubits > other.myCubits)
         return 1;
      if(myHands < other.myHands)    //"private" is at the class level
         return -1;
      if(myHands > other.getHands())
         return 1;
      return 0;
   }
   
   public boolean equals(Widget other)    //not equals(Object arg)
   {
      return compareTo(other) == 0;
   }
   
   public String toString()
   {
      return myCubits + " cubits " + myHands + " hands";
   }
}