// Name:
// Date:

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      SIZE = 9;  
      double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      display(heap);
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      // SIZE = 100;
      // double[] heap = new double[SIZE + 1];
      // heap = createRandom(heap);
      // display(heap);
      // makeHeap(heap, SIZE);
      // display(heap); 
      // sort(heap);
      // display(heap);
      // System.out.println(isSorted(heap));
   }
   
	//******* Part 1 ******************************************
   public static void display(double[] array)
   {
      for(int k = 1; k < array.length; k++)
         System.out.print(array[k] + "    ");
      System.out.println("\n");	
   }
   
   public static void sort(double[] array)
   {
      /* enter your code here */
      
      
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   }
  
   public static void swap(double[] array, int a, int b)
   {
   
   }
   
   public static void heapDown(double[] array, int k, int size)
   {
   
   }
   
   public static boolean isSorted(double[] arr)
   {

   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
       
       
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
   
   }
}

