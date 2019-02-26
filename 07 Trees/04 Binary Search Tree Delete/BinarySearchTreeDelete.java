// Name: Anup Bagali 
// Date: 2/14/19

import java.util.*;

/* Practice with a Binary Search Tree. Uses TreeNode.
 * Prompt the user for an input string.  Build a BST 
 * from the letters. Display it as a sideways tree.  
 * Prompt the user for a target and delete that node, 
 * if it exists. Show the updated tree.
 */
public class BinarySearchTreeDelete
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
                           //Case 1a:     ECSBPWANR 
                           //Case 1b:     N
                           //Case 2a:     SNTPOR    
                           //Case 2b:     HBRNVJSZIK  
                           //case 2c:     NFAKG
                           //case 2d:     NSPQX
                           //Case 3.a:    DBNACFSEJHM 
                           //Case 3.b:    DBNACFSEJH 
                           //on the handout: HDJAGKFEOLTMNSU
      TreeNode root = buildTree( null, s );
      System.out.println( display(root, 0) );
      System.out.print("Delete? ");
      String target = sc.next();
      if( contains( root, target ) )
      {
         root = delete( root, target );
         System.out.println("\n" + target+" deleted.");
      }
      else
         System.out.println("\n" + target+" not found");
      System.out.println( display(root, 0) );
   }
   
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
	
   /* Recursive algorithm to build a BST:  if the node is 
    * null, insert the new node.  Else, if the item is less, 
    * set the left node and recur to the left.  Else, if the 
    * item is greater, set the right node and recur to the right.   
	 */
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"") <= 0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   
   private static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current != null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   
   public static TreeNode delete(TreeNode current, String target){
      TreeNode root = current;  //don't lose the root!
      TreeNode parent = null;
      
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
         if(current.getLeft() != null && current.getRight() != null && current == root){
            TreeNode max = current.getLeft();
            TreeNode maxParent = current;
            while(max.getRight() != null){
               maxParent = max;
               max = max.getRight();
            }
            if(max.getLeft() == null && max.getRight() == null){
               maxParent.setRight(null);
               max.setLeft(current.getLeft());
               max.setRight(current.getRight());
               current.setLeft(null);
               current.setRight(null);
               root = max;
               return root;
            }
            else{
               maxParent.setRight(max.getLeft());
               max.setRight(current.getRight());
               max.setLeft(current.getLeft());
               current.setLeft(null);
               current.setRight(null);
               root = max;
               return root;
            }
         
         }
         if(current.getLeft() == null && current.getRight() == null){
            if(current == root){
               return null;
            }
            else{
               if( parent.getLeft() == current ){
                  parent.setLeft(null);
                  return root;
               }
               else{
                  parent.setRight(null);
                  return root;
               }
            }
         }
         else if(current.getRight() != null && current.getLeft() == null){
            if(current == root){
               TreeNode ret = root.getRight();
               root.setRight(null);
               return ret;
            }
            else{
               if(parent.getLeft() == current){
                  parent.setLeft(current.getRight());
                  current.setRight(null);
                  return root;
               }
               else{
                  parent.setRight(current.getRight());
                  current.setRight(null);
                  return root;
               }
            }
         }
         else if(current.getRight() == null && current.getLeft() != null){
            if(current == root){
               TreeNode ret = root.getLeft();
               root.setLeft(null);
               return ret;
            }
            else{
               if(parent.getLeft() == current){
                  parent.setLeft(current.getLeft());
                  current.setLeft(null);
                  return root;
               }
               else{
                  parent.setLeft(current.getLeft());
                  current.setLeft(null);
                  return root;
               }
            }
         }
         else if(current.getLeft() != null && current.getRight() != null){
            TreeNode max = current.getLeft();
            TreeNode maxParent = current;
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
               return root;
            
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
               
               return root;
            }
         }
      }
         
      
      
      return root;  //never reached
   }
}