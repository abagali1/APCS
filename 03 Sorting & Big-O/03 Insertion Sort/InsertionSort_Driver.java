 //name:  Anup Bagali   date: 10/25/18
import java.util.*;
import java.io.*;
public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   public static void print(double[] a)
   {
      for(double d: a)         //for-each
         System.out.print(d+" ");
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object abc : papaya)     //for-each
         System.out.print(abc+" ");
   }
   public static boolean isAscending(double[] a)
   {
      boolean sorted = true;
   
      for (int i = 0; i < a.length - 1; i++) {
         if (a[i] > a[i+1]) {
            sorted = false;
            break;
         }
      }
      return sorted;
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      boolean sorted = true;
   
      for (int i = 0; i < a.length - 1; i++) {
         if (a[i].compareTo( a[i+1]) >0) {
            sorted = false;
            break;
         }
      }
      return sorted;
   }
}

//**********************************************************
 //name:Anup Bagali     date:10/25/18
class Insertion
{
   public static double[] sort(double[] array)
   {         
      for (int i = 1; i < array.length; i++) {
         for(int j = shift(array,i,array[i]) ; j > 0 ; j--){
            if(array[j] < array[j-1]){
               double temp = array[j];
               array[j] = array[j-1];
               array[j-1] = temp;
            }
         }
      }
      return array;
   }
   private static int shift(double[] array, int index, double value)
   {  
      int i =index;
      while(i>=0 && array[i] > value){
         i--;
      }
      return i;
   }
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
      for (int i = 1; i < array.length; i++) {
         for(int j = shift(array,i,array[i]) ; j > 0 ; j--){
            if(array[j].compareTo(array[j-1]) < 0){
               Comparable temp = array[j];
               array[j] = array[j-1];
               array[j-1] = temp;
            }
         }
      }
      return array;
   }
   @SuppressWarnings("unchecked")
    private static int shift(Comparable[] array, int index, Comparable value)
   {
      int i =index;
      while(i>=0 && array[i].compareTo(value) > 0){
         i--;
      }
      return i;
   }
}
