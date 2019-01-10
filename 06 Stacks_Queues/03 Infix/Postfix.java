// Name: Anup Bagali 
// Date: 1/8/19

import java.util.*;

public class Postfix
{  
   public static final String OPERATORS = "+-/*^%!";
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static int eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<Integer> stack = new Stack<Integer>();
      for(String s: postfixParts){
         if(!isOperator(s)){
            stack.add(new Integer(s));
         }
         else{
            if(!"!".equals(s)){
               int second = (int)stack.pop();
               int first = (int)stack.pop();
               stack.push(new Integer(eval(first,second,s)));
            }
            else{
               int first = (int)stack.pop();
               stack.push(new Integer(eval(first,0,s)));
            }
         }
      }
      return (int)stack.pop();
   
   }
   
   public static int eval(int a, int b, String ch)
   {
      if("!".equals(ch)){
         return factorial(a);
      }
      else if("+".equals(ch)){
         return a+b;
      }
      else if("-".equals(ch)){
         return a-b;
      }
      else if("*".equals(ch)){
         return a*b;
      }
      else if("^".equals(ch)){
         return (int)Math.pow(a,b);
      }
      else if("%".equals(ch)){
         return a%b;
      }
      else{
         return a/b;
      }
   }
   
   public static boolean isOperator(String op)
   {
      return OPERATORS.contains(op);
   }
   public static int factorial(int num){
      if(num == 1 || num == 0){
         return 1;
      }else{
         return num * factorial(num-1);
      }
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/