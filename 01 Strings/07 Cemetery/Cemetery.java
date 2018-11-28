// Name: Anup Bagali
// Date: 9/6/2018
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Cemetery
{
   public static void main (String [] args)
   {
      File file = new File("cemetery_short.txt");
      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries); 
      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery); 
      //for testing only
      //for (int i = 0; i < cemetery.length; i++) 
         //System.out.println(cemetery[i].toString());
      System.out.println("In the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());    
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());     
   }
   
   /* Counts and returns the number of entries in File f.
      Uses a try-catch block.   
      @param f -- the file object
   */
   public static int countEntries(File f)
   {
      int count = 0;
      try{
         Scanner infile = new Scanner(f);
         while(infile.hasNextLine()){
            count++;
            String s = infile.nextLine();
         }
         return count;
      }
      catch(IOException e){
         System.out.println("File not Found!");
         System.exit(-1);
         return -1;
      }
   }

   /* Reads the data.
      Fills the array with Person objects.
      Uses a try-catch block.   
      @param f -- the file object 
      @param num -- the number of lines in the File f  
   */
   public static Person[] readIntoArray (File f, int num)
   {
      Person[] result = new Person[num];
      try{
         Scanner infile = new Scanner(f);
         for(int i=0;i<=result.length-1;i++){
            result[i] = makeObjects(infile.nextLine());
         }
         
         return result;
      }
      catch(IOException e){
         System.out.println("File not Found!");
         System.exit(-1);
         return null;
      }
   }
   
   /* A helper method that instantiates one Person object.
      @param entry -- one line of the input file.
   */
   private static Person makeObjects(String entry)
   {
      String name = entry.substring(0,25).trim();
      String bDate = entry.substring(26,37).trim();
      String age = entry.substring(37,42).trim();
            
      return new Person(name,bDate,age);
   }  
   
   /* Finds and returns the location (the index) of the Person
      who is the youngest.
      @param arr -- an array of Person objects.
   */
   public static int locateMinAgePerson(Person[] arr)
   {
      double min = arr[0].getAge();
      int index = 0;
      for(int i=0;i<=arr.length-1;i++){
         if(arr[i].getAge() < min){
            min = arr[i].getAge();
            index = i;
         }
      }
      return index;
   }   
   
   /* Finds and returns the location (the index) of the Person
      who is the oldest.
      @param arr -- an array of Person objects.
   */
   public static int locateMaxAgePerson(Person[] arr)
   {
      double max = arr[0].getAge();
      int index = 0;
      
      for(int i=0;i<=arr.length-1;i++){
         if(arr[i].getAge() > max){
            max = arr[i].getAge() ;
            index = i;
         }
      }
      return index;
   }       
} 

class Person
{
   /* private fields  */
   private DecimalFormat df = new DecimalFormat("0.0###");
   private String name;
   private String burialDate;
   private double age;
      
   /* a three-arg constructor  */
   public Person(String n, String date, String a){
      name = n;
      burialDate = date;
      age = calculateAge(a);
   }
   
   /* any necessary accessor methods */
   public String getName(){
      return name;
   }
   public String getBurialDate(){
      return burialDate;
   }
   public double getAge(){
      return Double.parseDouble(df.format(age));
   }
   
   public double calculateAge(String a)
   {
      if(a.contains("w")){
         return (Double.parseDouble(a.substring(0,a.indexOf("w")))) / 52;
      }
      else if(a.contains("d")){
         return (Double.parseDouble(a.substring(0,a.indexOf("d")))) / 365;
      }
      else{
         return Double.parseDouble(a);
      }
   }
   public String toString(){
      return getName() + " Age: " + getAge() + " Date: " + getBurialDate();
   }
}