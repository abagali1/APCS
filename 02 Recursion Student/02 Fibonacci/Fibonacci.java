// Name:Anup Bagali
// Date:9/27/18
  
import java.util.*;
public class Fibonacci
{
   public static void main(String[] args)
   {
       long start, end, fib; //why long?
       int[] fibNumber = {1, 5, 10, 20, 30, 40, 41, 42};
       System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
       for(int n = fibNumber[0]; n <= fibNumber[fibNumber.length - 1]; n++)
       { 
           start = System.nanoTime();
           fib = fibIterate(n);
           end = System.nanoTime();
           System.out.print("\t\t" + n + "\t\t" + fib + "\t" + (end-start)/1000.);
           start = System.nanoTime();   	
           fib = fibRecur(n);      
           end = System.nanoTime();
           System.out.println("\t" + fib + "\t\t" + (end-start)/1000.);
       }
   }
   
   /**
    * Calculates the nth Fibonacci number by interation
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibIterate(int n)
   {
      int p = 0;
      int ne = 1;
      int r = 0;
      for(int i=0;i<n;i++){
         r = p+ne;
         p = ne;
         ne = r;
      } 
      return r;
          
   }

   /**
    * Calculates the nth Fibonacci number by recursion
    * @param n A variable of type int representing which Fibonacci number
    *          to retrieve
    * @returns A long data type representing the Fibonacci number
    */
   public static long fibRecur(int n)
   {
        if(n==0 || n==1){
         return 1;
        }else{
         return fibRecur(n-1) + fibRecur(n-2);
        }
         
   }
}