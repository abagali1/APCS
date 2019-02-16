// Name: Anup Bagali
// Date: 2/15/19

import java.util.*;
import java.io.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberOfWidgets = 10;
   public static void main( String[] args ) 
   {
      // Day one 
      tree = new BSTobject<String>();
      tree = build(tree, "T");
      System.out.print(tree);
      System.out.println("Size: " + tree.size());
      System.out.println("Is empty: "+ tree.isEmpty());
   		
   	// Day two
   	// Your assignment the second day is to finish the BSTobject class.  
   	// Specifically, prompt the user for a string, put the characters into 
      // a BST, call toString on this tree, and print the size of the tree.
   	
      // Day two, Widgets			
   	// Next, fill your BST with 10 Widget objects from widgets.txt.  Display 
      // the tree. Then prompt the user to enter cubits and hands.  If the tree 
      // contains that Widget, delete it, of course restoring the BST order. 
      // Display the new tree and its size. If the tree does not contain that 
      // Widget, print "Not found".*/
   
   	// Day three -- AVL tree  -----------------------
   }
  
   /* Build the tree for Strings, Day 1
    */
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
     
              
   }
   
   /* Build the tree for Widgets, Day 2
    */
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.out.println("File not found.");
      }        
      
      for(int i = 0; i < numberOfWidgets; i++)   
      {
      
      
      }
   }
}

interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );             //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );          //returns the object, not a Node<E>
   public int size();
   public String toString();
}

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
   // Declare 2 fields 
   private Node root;
   private int size;
   // Create a default constructor
   public BSTobject(){
      root = null;
      size = 0;
   }   
      
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
   
   //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t == null){
         return new Node<E>(obj);
      }else{
         if( ((t.getValue()).compareTo(obj)) >= 0){
            t.setLeft(add(t.getLeft(),obj));
         }else{
            t.setRight(add(t.getRight(),obj));
         }
         return t;
      }
   }
   public int size(){
      return size;
   }
   
   /* Implement the interface here.  Use TreeNode as an example,
    * but root is a field. You need add, contains, isEmpty, 
    * delete, size, and toString.  
    */
   public boolean contains(E element){
      return contains(root, element);
   }
   public boolean contains(Node<E> t, E x){
      Node<E> p = t;
      while(p != null){
         if( ((p.getValue())).compareTo(x) == 0){
            return true;
         }else{
            Comparable temp = p.getValue();
            if( temp.compareTo(x) > 0){
               p = p.getLeft();
            }else{
               p = p.getRight();
            }
         }
      }
      return false;
   }
   public boolean isEmpty(){
      return root == null;
   }
   public E delete(E element){
      if(contains(element)){
         size--;
      }
      return (E)delete(root, element);
   }
   
   public E delete(Node<E> current, E target){
      Node<E> root = current;  //don't lose the root!
      Node<E> parent = null;
      
      if(!contains(current,target)){
         return null;
      }
      else{
         while( ((Comparable)current.getValue()).compareTo(target) != 0){
            Comparable temp = (Comparable)current.getValue();
            if( temp.compareTo(target) > 0){
               parent = current;
               current = current.getLeft();
            }
            else{
               parent = current;
               current = current.getRight();
            }
         }
         
         if(current.getLeft() == null && current.getRight() == null){
            if(current == root){
               return null;
            }
            else{
               if( parent.getLeft() == current ){
                  parent.setLeft(null);
                  return current.getValue();
               }
               else{
                  parent.setRight(null);
                  return current.getValue();
               }
            }
         }
         else if(current.getRight() != null && current.getLeft() == null){
            if(current == root){
               Node<E> ret = root.getRight();
               root.setRight(null);
               return ret.getValue();
            }
            else{
               if(parent.getLeft() == current){
                  parent.setLeft(current.getRight());
                  current.setRight(null);
                  return current.getValue();
               }
               else{
                  parent.setRight(current.getRight());
                  current.setRight(null);
                  return current.getValue();
               }
            }
         }
         else if(current.getRight() == null && current.getLeft() != null){
            if(current == root){
               Node<E> ret = root.getLeft();
               root.setLeft(null);
               return ret.getValue();
            }
            else{
               if(parent.getLeft() == current){
                  parent.setLeft(current.getLeft());
                  current.setLeft(null);
                  return current.getValue();
               }
               else{
                  parent.setLeft(current.getLeft());
                  current.setLeft(null);
                  return current.getValue();
               }
            }
         }
         else if(current.getLeft() != null && current.getRight() != null){
            Node<E> max = current.getLeft();
            Node<E> maxParent = current;
            while(max.getRight() != null){
               maxParent = max;
               max = max.getRight();
            }
            if(max.getLeft() == null){
               maxParent.setRight(null);
               max.setLeft(current.getLeft());
               max.setRight(current.getRight());
            
               if(parent.getLeft() == null){
                  parent.setLeft(max);
               }
               else{
                  parent.setRight(max);
               }
               current.setLeft(null);
               current.setRight(null);
               return current.getValue();
            }
            else{
               maxParent.setRight(max.getLeft());
               max.setLeft(current.getLeft());
               max.setRight(current.getRight());
               if(parent.getLeft() == null){
                  parent.setLeft(max);
               }
               else{
                  parent.setRight(max);
               }
               current.setLeft(null);
               current.setRight(null);
               return current.getValue();
            }
         }
      }
      return root.getValue();  //never reached
   }
   public String toString(){
      return "":
   }


   

    
   /* Private inner class 
    */  
   private class Node<E>
   {
      // 3 fields 
      private E value;
      private Node<E> left;
      private Node<E> right;
      // 2 constructors, one-arg and three-arg
      public Node(E value){
         this.value = value;
      }
      public Node(E value, Node<E> left, Node<E> right){
         this.value = value;
         this.left = left;
         this.right = right;
      }
      //methods--Use TreeNode as an example. See Quick Reference Guide.
      public E getValue(){
         return value;
      }
      public Node<E> getLeft(){
         return left;
      }
      public Node<E> getRight(){
         return right;
      }
      public void setValue(E value){
         this.value = value;
      }
      public void setLeft(Node<E> left){
         this.left = left;
      }
      public void setRight(Node<E> right){
         this.right = right;
      } 
      
   }

}