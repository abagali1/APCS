//name:Anup Bagali    date:  11/1/18

import java.io.*;      //the File class
import java.util.*;    //the Scanner class
   
public class Widgets_Driver
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      // test the methods
      Widget a = new Widget();
      System.out.println("Default widget A:  " + a.toString());
      Widget b = new Widget(23, 10);
      System.out.println("2-arg constructor B:  " + b.toString());
      Widget c = new Widget(b);
      System.out.println("Copy constructor C:  " + c.toString());
      System.out.print("C's cubits = " + c.getCubits());
      System.out.println( " and hands = " + c.getHands());
      System.out.println("Test the equals methods: ");
      System.out.println("   A equals B " + a.equals(b));
      System.out.println("   B equals C " + b.equals(c));
      
      c.setCubits(3);
      c.setHands(4);
      System.out.print("C is reset to " + c.toString());
      
      System.out.println("\nTest each sort on 57 Widgets");
      Comparable[] apple = input("widgets.txt");
      Scanner sc = new Scanner(System.in);
      System.out.println("  1 Selection Sort");
      System.out.println("  2 Insertion Sort");
      System.out.println("  3 Merge Sort");
      System.out.println("  4 Quick Sort");
      System.out.print("Choose your sort:  ");
      int choice = Integer.parseInt(sc.next());
      System.out.println();
      switch( choice )
      {
         case 1:  
            Selection.sort(apple);
            break;
         case 2: 
            Insertion.sort(apple);
            break;
         case 3: 
            MergeSort.sort(apple);
            break;
         case 4:
            QuickSort.sort(apple);
            break;
         default:  System.out.println("Wrong choice");
      }
      print(apple);   
   }
          	
   public static Comparable[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner(new File(filename));
      int count = 0;
      while(infile.hasNext()){
         infile.nextLine();
         //System.out.println(infile.next());
         count++;
      }
      Comparable[] result = new Comparable[count/2];

      Scanner infile2 = new Scanner(new File(filename));
      
      for(int i=0;i<=result.length-1;i++){
         result[i] = new Widget(infile2.nextInt(), infile2.nextInt());
      }
      
      return result;
   }
   	  
   public static void print(Object[] mango)
   {
      for(Object o: mango){
         System.out.println(o.toString());
      }
   }
}

/////////////////////////////////////////////////////
class Widget implements Comparable<Widget>
{
   //fields
   private int cubits, hands;
   //constructors
   
   public Widget(){
      this.cubits = 0;
      this.hands = 0;
   }
   public Widget(int c, int h){
      this.cubits = c;
      this.hands = h;
   }
   public Widget(Widget w){
      this.cubits = w.cubits;
      this.hands = w.hands;
   }
   //accessors and modifiers
   
   public int getCubits(){
      return this.cubits;
   }
   public int getHands(){
      return this.hands;
   }
   public void setCubits(int c){
      this.cubits = c;
   }
   public void setHands(int h){
      this.hands = h;
   }
   
   //compareTo and equals
   
   public int compareTo(Widget w){
      if(this.cubits > w.cubits){
         return 1;
      }
      else if(this.cubits == w.cubits){
         if(this.hands > w.hands){
            return 1;
         }
         else if(this.hands == w.hands){
            return 0;
         }
         else{
            return -1;
         }
      }
      else{
         return -1;
      }
   }
   public boolean equals(Widget w){
      return this.compareTo(w) == 0;
   }
   
   //toString
   public String toString(){
      return this.getCubits() + " cubits " + this.getHands() + " hands";
   }
   
}
   
   
 /***************************************
      ----jGRASP exec: java Widgets_Teacher
 Default widget A:  0 cubits 0 hands
 2-arg constructor B:  23 cubits 10 hands
 Copy constructor C:  23 cubits 10 hands
 C's cubits = 23 and hands = 10
 Test the equals methods: 
    A equals B false
    B equals C true
 C is reset to 0 cubits 0 hands
 
 Test each sort on 57 Widgets
   1 Selection Sort
   2 Insertion Sort
   3 Merge Sort
   4 Quick Sort
 Choose your sort:  2
 
 0 cubits 14 hands
 1 cubits 3 hands
 2 cubits 14 hands
 5 cubits 14 hands
 10 cubits 14 hands
 11 cubits 11 hands
 12 cubits 0 hands
 12 cubits 7 hands
 13 cubits 9 hands
 15 cubits 12 hands
 17 cubits 5 hands
 18 cubits 13 hands
 19 cubits 13 hands
 19 cubits 13 hands
 22 cubits 6 hands
 23 cubits 7 hands
 24 cubits 15 hands
 24 cubits 15 hands
 26 cubits 2 hands
 28 cubits 5 hands
 28 cubits 12 hands
 29 cubits 15 hands
 31 cubits 0 hands
 32 cubits 1 hands
 32 cubits 11 hands
 32 cubits 11 hands
 32 cubits 12 hands
 35 cubits 3 hands
 39 cubits 2 hands
 39 cubits 5 hands
 41 cubits 10 hands
 43 cubits 2 hands
 43 cubits 5 hands
 43 cubits 6 hands
 51 cubits 2 hands
 54 cubits 14 hands
 55 cubits 8 hands
 56 cubits 3 hands
 57 cubits 12 hands
 62 cubits 15 hands
 63 cubits 0 hands
 64 cubits 13 hands
 67 cubits 3 hands
 70 cubits 0 hands
 73 cubits 5 hands
 74 cubits 7 hands
 75 cubits 9 hands
 81 cubits 5 hands
 85 cubits 14 hands
 86 cubits 3 hands
 90 cubits 13 hands
 91 cubits 3 hands
 92 cubits 1 hands
 92 cubits 8 hands
 96 cubits 1 hands
 98 cubits 8 hands
 99 cubits 5 hands
 
  ----jGRASP: operation complete.    
  ************************************/