// Name: Anup Bagali
// Date: 11/26/18

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   
   public int size()
   {
      return size;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      DLNode temp = head;
      if(index == 0){
         DLNode n = new DLNode(obj,head,head.getNext());
         head.setNext(n);
         head.getNext().getNext().setPrev(n);
      }else if(index == size){
         DLNode n = new DLNode(obj,head.getPrev(),head);
         head.getPrev().setNext(n);
         head.setPrev(n);
      }else{
         for(int i=0;i<index;i++){
            temp = temp.getNext();
         }
         DLNode n = new DLNode(obj,temp,temp.getNext());
         temp.getNext().setPrev(n);
         temp.setNext(n);
      }
      size++;
      
                    
   }
   
   /* return obj at position index. 	*/
   public Object get(int index)
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head;
      for(int i=0;i<=index;i++){
         pointer = pointer.getNext();
      }
      return pointer.getValue();
      
   }
   
   /* replaces obj at position index. 
        returns the obj that was replaced*/
   public Object set(int index, Object obj)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode pointer = head;
      for(int i=0;i<=index;i++){
         pointer = pointer.getNext();
      }
      Object temp = pointer.getValue();
      pointer.setValue(obj);
      return temp; 
      
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object of the node that was removed.    */
   public Object remove(int index)
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      Object p = get(index);
      DLNode temp = head;
      if(index == 0){
         head.getNext().setPrev(null);
         head.setNext(head.getNext().getNext());
         head.getNext().getPrev().setNext(null);
         head.getNext().setPrev(head);
      }else if(index == size){
         head.getPrev().setNext(null);
         head.setPrev(head.getPrev().getPrev());
         head.getPrev().getNext().setPrev(null);
         head.getPrev().setNext(head);
      }else{
         for(int i=0;i<=index;i++){
            temp = temp.getNext();
         }
         temp.getPrev().setNext(temp.getNext());
         temp.getNext().setPrev(temp.getPrev());
         temp.setNext(null);
         temp.setPrev(null);
      }
      size--;
      return p;
   }
   
   /* inserts obj at front of list, increases size   */
   public void addFirst(Object obj)
   {
      add(0,obj);
   }
   
   /* appends obj to end of list, increases size    */
   public void addLast(Object obj)
   {
      add(size,obj);
   }
   
   /* returns the first element in this list  */
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   /* returns the last element in this list  */
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   /* returns and removes the first element in this list, or
       returns null if the list is empty  */
   public Object removeFirst()
   {
      if(size == 0)
         return null;
      else
         return remove(0);
   }
   
   /* returns and removes the last element in this list, or
       returns null if the list is empty  */
   public Object removeLast()
   {
      if(size == 0)
         return null;
      else
         return remove(size-1);
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      DLNode pointer;
      String result = "[";
      for(pointer = head.getNext(); pointer.getNext() != head;pointer=pointer.getNext()){
         result += pointer.getValue() + ", ";
      }
      result += pointer.getValue() + "]";
      return result;
   }
}