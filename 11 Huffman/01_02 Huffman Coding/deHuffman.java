// Name: Anup Bagali
// Date: 4/9/19
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode tree = new TreeNode(null);
      TreeNode root = tree;
      while(huffLines.hasNextLine()){
         char[] temp = huffLines.nextLine().toCharArray();
         //tree = root;   
         String s=temp[0]+"";
         for(int i=1;i<=temp.length;i++){
            if(i != temp.length){
               if(temp[i] == 0){
                  tree.setLeft(new TreeNode(""));
                  tree = tree.getLeft();
               }
               else{
                  tree.setRight(new TreeNode(""));
                  tree = tree.getRight();
               }
            }
            else{
               tree.setValue(s);
            }
         }
      }
      return root;
   }
   public static String dehuff(String text, TreeNode root)
   {
   return null;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
