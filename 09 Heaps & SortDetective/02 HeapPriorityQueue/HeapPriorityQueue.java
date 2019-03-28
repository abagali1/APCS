 //Name: Anup Bagali
 //Date: 3/28/19
 
import java.util.*;


/* implement the API for java.util.PriorityQueue
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(myHeap.size()-1);
      return true;
   }
   
   public E remove()
   {
      
      E temp = myHeap.remove(1);
      swap(1,myHeap.size()-1);
      heapUp(myHeap.size()-1);
      return temp;
   }
   
   public E peek()
   {
      if(!isEmpty())
         return myHeap.get(1);
      else
         return null;
   }
   
   public boolean isEmpty()
   {
      return myHeap.size() <= 1;
   }
   
   private void heapUp(int k)
   {
      if(k<=1)
         return;
      int parent = k/2;
      if(myHeap.get(k).compareTo(myHeap.get(parent)) < 0){
         swap(parent,k);
         heapUp(parent);
      }
     
   }
   
   private void swap(int a, int b)
   {
      E temp = myHeap.get(a);
      myHeap.set(a,myHeap.get(b));
      myHeap.set(b,temp);
   }
   
   private void heapDown(int k, int size)
   {
      int left = 2*k;
      int right = 2*k +1;
      if(left>size){
         return;
      }
      else{
         int max = k;
         if(left<size &&  myHeap.get(max).compareTo(myHeap.get(left))<0){
            max = left;
         }
         if(right<size && myHeap.get(max).compareTo(myHeap.get(right))<0){
            max = right;
         }
         if(myHeap.get(max).compareTo(myHeap.get(k))<0)
            return;
         if(max != k){
            swap(k,max);
            heapDown(max,size);
         
         }
      
         
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  

}
