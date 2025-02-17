// Name: Anup Bagali
// Date: 12/4/18

import java.io.*;
import java.util.*;

/**
 * Use for-each loops or iterators.
 * Do not use traditional for-loops.
 */  
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }

   /**
    * @return ArrayList containing all values in the int array
    */     
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
      ArrayList<Integer> myList = new ArrayList<Integer>();
      for ( int str : rawNumbers )
         myList.add( str );
      return myList;
   }
 
   /**
    * @return ArrayList containing all values in the String array
    */    
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> result = new ArrayList<String>();   
      for(String s:rawWords){
         result.add(s);
      }
      return result;
   }

   /**
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return number of negative values in ArrayList a
    */    
   public static int countNeg(ArrayList<Integer> a)
   {
      int count =0;
      for(int b: a){
         if(b<0)
            count++;
      }
      return count;
   }
  
   /**
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return average of all values in the ArrayList a
    */    
   public static double average(ArrayList<Integer> a)
   {
      int sum =0;
      for(int b:a){
         sum+=b;
      }
      
      return (double)(sum/a.size());      
   }

   /**
    * Changes all negative values in ArrayList to 0.
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return ArrayList with negative values turned to 0 
    */    
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a)
   {
      ListIterator it = a.listIterator();
      while(it.hasNext())
         if((int)it.next() < 0)
            it.set(0);
      
      return a;
            
   }
  
   /**
    * Deletes all zero values in ArrayList.
    * Precondition:  Arraylist a is not empty; contains Integer objects
    * @return ArrayList with no zero values. 
    */   
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a)
   {
      ListIterator it = a.listIterator();
      while(it.hasNext())
         if((int)it.next() == 0)
            it.remove();
         
      return a;
   }

   /**
    * Removes duplicates from list.
    * Precondition:  Arraylist a is not empty; contains String objects
    * @return ArrayList without duplicate movie titles. 
    */   
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> result = new ArrayList<String>();
      for(int i=0;i<a.size();i++){
         String comp = a.get(i);
         boolean isin = false;
         for(int j=0;j<result.size();j++){
            if(result.get(j).equals(comp))
               isin = true;
         }
         if(!isin)
            result.add(comp);
      }
      return result;
   }
   
}

