//name: Anup Bagali   date: 10/23/18
import java.io.*;      //the File 
import java.util.*;    //the Scanner 
public class Searches_Driver
{
   private static int amount=1325;
   public static void main(String[] args) throws Exception
   {
      String[] apple = input("declaration.txt");
      Arrays.sort(apple);  
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("Enter a word: ");
         String target = sc.next();   //Liberty  
         if(target.equals("-1") )
            break;
         Searches.reset();
         int found = Searches.linear(apple, target);
         if(found == -1)
            System.out.println(target + " was not found by the linear search.");
         else
            System.out.println("Linear Search found it at location "+found+" in " + Searches.getLinearCount()+" comparisons.");
         int found2 = Searches.binary(apple, target);
         if(found2 == -1)
            System.out.println(target + " was not found by the binary search.");
         else
            System.out.println("Binary Search found it at location "+found2+" in " + Searches.getBinaryCount()+" comparisons.");
      }
   }
   public static String[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner( new File(filename) );
      String[] array = new String[amount];
      for (int k =0; k<amount; k++)    
         array[k] = infile.next();
      infile.close();
      return array;
   }
}
/////////////////////////////////////////////////////////
class Searches
{
   private static int linearCount = 0;
   private static int binaryCount = 0;      
   public static int getLinearCount()
   {
      return linearCount;
   }
   public static int getBinaryCount()
   {
      return binaryCount;
   }
   public static void reset()
   {
      linearCount = 0;
      binaryCount = 0;
   }
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static int linear(Comparable[] array, Comparable target)
   { 
      int result = -99;
      for(int i=0;i<=array.length-1;i++){
         if(array[i].compareTo(target) == 0)
            result = i;
         linearCount++;
      }
      return result;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static int binary(Comparable[] array, Comparable target)
   {
      return binaryhelper(array,target,0,array.length-1);
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   private static int binaryhelper(Comparable[] array, Comparable target, int start, int end)
   {  
      if(start>end)
         return -1;
      if(array[start].compareTo(target)==0){
         return start;
      }else{
         if(array[(((start+end)/2))].compareTo(target) > 0){
            binaryCount++;
            end = (((start+end)/2));
            return binaryhelper(array,target,start,end);
         }else{
            start = (((start+end)/2));
            binaryCount++;
            return binaryhelper(array,target,start,end);
         }
      }
   }
}