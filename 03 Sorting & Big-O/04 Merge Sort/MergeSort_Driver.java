 //name: Anup Bagali    date: 10/29/18
import java.util.*;
import java.io.*;
public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      //double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
      int n = (int)(Math.random()*50+10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();
      print(array);
        	
      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a)   
   {                            // The for-each loop. 
      for(double number : a)    //doing something to each element
         System.out.print(number+" ");
      System.out.println();
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
   public static void print(Object[] peach)
   {
      for(Object o: peach){
         System.out.print(o.toString() + " " );
      }
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
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }
   /*  array			array that is being sorted
       copyBuffer		temp space needed during the merge process
       low, high		bounds of subarray
       middle			midpoint of subarray   */
   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
      // if ( high - low  < CUTOFF )                  //switch to selection sort  when
         // SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      // else
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   public static void merge(double[] array, double[] copyBuffer,
                                   int low, int middle, int high)
   
   {
      // Initialize i1 and i2 to the first items in each subarray  
      // Interleave items from the subarrays into the copyBuffer in such 
      // a way that order is maintained.
      int i1=low;
      int i2=middle+1;
      
      
      for(int i = low;i<=high; i++)
      {
         if(i1>middle)
         {
            copyBuffer[i] = array[i2];
            i2++;
         }
         else if(i2>high)
         {
            copyBuffer[i] = array[i1];
            i1++;
         }
         else if(array[i1]<array[i2])
         {
            copyBuffer[i] = array[i1];
            i1++;
         }
         else{
            copyBuffer[i] = array[i2];
            i2++;
         }
         
      }      
      
      
      //then copy the just-sorted values (from low to high)
      // from the copyBuffer back to the array.
      for(int i=low;i<=high;i++){
         array[i] = copyBuffer[i];
      }
      
   }	
   
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   { 
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high)
   {  
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);
         merge(array, copyBuffer, low, middle, high);
      }
   }
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   */
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void merge(Comparable[] array, Comparable[] copyBuffer,
                                   int low, int middle, int high)
   {
      int i1=low;
      int i2=middle+1;
      
      
      for(int i = low;i<=high; i++)
      {
         if(i1>middle)
         {
            copyBuffer[i] = array[i2];
            i2++;
         }
         else if(i2>high)
         {
            copyBuffer[i] = array[i1];
            i1++;
         }
         else if(array[i1].compareTo(array[i2]) <0)
         {
            copyBuffer[i] = array[i1];
            i1++;
         }
         else{
            copyBuffer[i] = array[i2];
            i2++;
         }
         
      }      
      
      
      //then copy the just-sorted values (from low to high)
      // from the copyBuffer back to the array.
      for(int i=low;i<=high;i++){
         array[i] = copyBuffer[i];
      }
   }    	
}

/***************************************************
This is the extension.  You will have to uncomment Lines 54-56 above.
name: Anup Bagali  date: 10/31/2018
***********************************************/
class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
      int maxPos;
      for(int k = 0; k < array.length; k++)
      {
         maxPos = findMax(array, low,high);
         swap(array, maxPos, array.length - k - 1);
      }
   }
   private static int findMax(double[] array, int low, int upper)
   {
      int max = low;
      for(int i=1;i<=upper;i++){
         if(array[max]<array[i]){
            max = i;
         }
      }
      return max;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   } 
}
