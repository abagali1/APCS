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
         tree = root;   
         String s=temp[0]+"";
         for(int i=1;i<=temp.length;i++){
            if(i != temp.length){
               if(temp[i] == '0'){
                  if(tree.getLeft() == null)
                     tree.setLeft(new TreeNode(""));
                  tree = tree.getLeft();
               }
               else{
                  if(tree.getRight() == null)
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
      String result = "";
      char[] arr = text.toCharArray();
      TreeNode temp = root;
      for(int i=0;i<=arr.length-1;i++){
         if((temp.getLeft() == null && temp.getRight() == null)){
            result += temp.getValue();
            temp = root;
         }
         if(arr[i] == '0'){
            temp = temp.getLeft();
         }
         else{
            temp = temp.getRight();
         }
      }
      
      return result;
      
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
