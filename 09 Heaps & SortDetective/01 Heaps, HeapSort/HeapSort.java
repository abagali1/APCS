// Name: Anup Bagali
// Date: 3/27/19

public class HeapSort
{
   public static int SIZE;  //9 or 100
	
   public static void main(String[] args)
   {
      //Part 1: Given a heap, sort it. Do this part first. 
      //SIZE = 9;  
      //double heap[] = {-1,99,80,85,17,30,84,2,16,1};
      
      //display(heap);
      //sort(heap);
      //display(heap);
      //System.out.println(isSorted(heap));
      
      //Part 2:  Generate 100 random numbers, make a heap, sort it.
      SIZE = 100;
      double[] heap = new double[SIZE + 1];
      heap = createRandom(heap);
      display(heap);
      makeHeap(heap, SIZE);
      display(heap); 
      sort(heap);
      display(heap);
      System.out.println(isSorted(heap));
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
      for(int i=array.length-1;i>0;i--){
         swap(array,1,i);
         heapDown(array,1,i);
      }
      
   
      if(array[1] > array[2])   //just an extra swap, if needed.
         swap(array, 1, 2);
   
   }
  
   public static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
   
   public static void heapDown(double[] array, int k, int size) //fixme rip
   {
   
      int left = 2*k;
      int right = 2*k +1;
      if(left>size){
         return;
      }
      else{
         int max = k;
         if(left<size &&  array[left]>array[max]){
            max = left;
         }
         if(right<size && array[right]>array[max]){
            max = right;
         }
         if(array[max] < array[k])
            return;
         if(max != k){
            swap(array,k,max);
            heapDown(array,max,size);
         
         }
      
         
      }
   }
   
   public static boolean isSorted(double[] arr)
   {
      int result = 0;
      for(int i=1;i<=arr.length-1;i++){
         if(arr[i] > arr[i-1]){
            result++;
         }
      }
      return result == arr.length-1;
   }
   
   //****** Part 2 *******************************************

   //Generate 100 random numbers (between 1 and 100, formatted to 2 decimal places) 
   public static double[] createRandom(double[] array)
   {  
      array[0] = -1;   //because it will become a heap
      for(int i=1;i<=array.length-1;i++){
         array[i] = Math.floor( ((Math.random() * 100) + 1) *100)/100;
      }
       
      return array;
   }
   
   //turn the random array into a heap
   public static void makeHeap(double[] array, int size)
   {
      for(int k =size/2;k>0;k--){
         heapDown(array,k,size);
      }
   }
}

