// Name: Anup Bagali
// Date: 2/7/19

import java.util.*;

/*  Driver for a binary expression tree class.
 *  Input: a postfix string, space delimited tokens. 
 */
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 /");
      postExp.add("20.0 3.0 -4.0 + *");
      postExp.add("2 3 + 5 / 4 5 - *");
      postExp.add("5.6");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
class BXT
{
   private TreeNode root;
   
   public BXT()
   {
      root = null;
   }
    
   public void buildTree(String str)
   {
      String[] arr = str.split(" ");
      Stack<TreeNode> s = new Stack<TreeNode>();
      
      for(String a: arr){
         if(!isOperator(a)){
            s.push(new TreeNode(a));
         }
         else{
            TreeNode temp = new TreeNode(a);
            temp.setRight(s.pop());
            temp.setLeft(s.pop());
            s.push(temp);
         }
      }
      root = s.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(isOperator((String)t.getValue())){
         return computeTerm((String)t.getValue(),evaluateNode(t.getLeft()),evaluateNode(t.getRight()));
      }else{
         return Double.parseDouble((String)t.getValue());
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
      if("+".equals(s)){
         return a+b;
      }
      else if("-".equals(s)){
         return a-b;
      }
      else if("/".equals(s)){
         return a/b;
      }
      else{
         return a*b;
      }
   }
   
   private boolean isOperator(String s)
   {
      return "+-/*".contains(s);
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
   
      toReturn += inorderTraverse(t.getLeft());//recurse left
      toReturn += t.getValue() + " "; //inorder visit
      toReturn += inorderTraverse(t.getRight());   //recurse right
   
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //preorder visit
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }

}

/***************************************

 Postfix Exp: 14 -5 /
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20.0 3.0 -4.0 + *
 		-4.0
 	+
 		3.0
 *
 	20.0
 Infix order:  20.0 * 3.0 + -4.0 
 Prefix order:  * 20.0 + 3.0 -4.0 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 Postfix Exp: 5.6
 5.6
 Infix order:  5.6 
 Prefix order:  5.6 
 Evaluates to 5.6
 ------------------------
 
 *******************************************/