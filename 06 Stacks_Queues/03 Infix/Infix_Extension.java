// Name: Anup Bagali
// Date:1/10/19

import java.util.*;

public class Infix_Extension
{
   public static final String OPERATORS = "+-/*()!%";
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix");
      ArrayList<String> infixExp = new ArrayList<String>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("( -5 + 15 ) - 6 / 3");
      infixExp.add("( 3 + 4 ) * ( 5 + 6 )");
      infixExp.add("( 3 * ( 4 + 5 ) - 2 ) / 5");
      infixExp.add("8 + -1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("9 % 4 * 5");
      infixExp.add("24 / 12");
      infixExp.add("-24 / -12");
      //infixExp.add("12 / 0");
      infixExp.add("2.5 * 2");
      infixExp.add("-2.5 * 2");
      infixExp.add("( 10 + 5 ) - 15 * 3");
      infixExp.add("3 ? 2");
      infixExp.add("( 3 + 2");
      infixExp.add("3 + 2 ]");
       
   
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);
         System.out.println(infix+"\t\t"+pf);
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> infixParts = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> stack = new Stack<String>();
      String postfix = "";
      
      if(invalidOperator(infixParts)){
         return "ERROR: String contains an invalid character";
      }
      if(!ParenMatch.checkParen(infix)){
         return "ERROR: String does not have matching parentheses";
      }
      
      for(String s: infixParts){
         if(!isOperator(s)){
            postfix += " " + s;
         }
         else if("(".equals(s)){
            stack.add(s);
         }
         else if(")".equals(s)){
            while(! ("(".equals(stack.peek())) ) {
               postfix += " " + stack.pop();
            }
            stack.pop();
         }
         else{
            if( (stack.isEmpty()) || (isLower(stack.peek().charAt(0), s.charAt(0))) || ("(".equals(stack.peek()))){
               stack.add(s);
            }
            else{ 
               while( ! ((stack.isEmpty()) || (isLower(stack.peek().charAt(0), s.charAt(0))) || ("(".equals(stack.peek()))) ) {
                  postfix += " " + stack.pop();
               }
               stack.add(s);
            }
         }
         
      }
      while(!stack.isEmpty()){
         if(isOperator(stack.peek())){
            postfix += " " + stack.pop();
         }
         else{
            stack.pop();
         }
      }
    
    return postfix.trim();
   }
   
   public static boolean invalidOperator(List<String> a){
      boolean result = true;
      for(char c: OPERATORS.toCharArray()){
         if(a.contains(c+"")){
            result = false;
         }
      }
      return result;
   }
   
   public static boolean isOperator(String s){
      return OPERATORS.contains(s);
   }
   
	//returns true if c1 has lower or equal precedence than c2
   public static boolean isLower(char ch1, char ch2)
   {
     //return (('*' == ch1) && ('/' == ch2)) || (('/' == ch1) && ('*' == ch2)) || (('+' == ch1) && ('-' == ch2)) || (('-' == ch1) &&('+' == ch2)) || (('+' == ch1) && ('*' == ch2)) || (('+' == ch1) && ('/' == ch2)) || (('-' == ch1) && ('*' == ch2)) || (('-' == ch1) && ('/' == ch2)) || (ch1 == ch2)
      if(ch1 == '+' || ch1 == '-')
         return true;
      if(ch2 == '*' || ch2 == '/')
         if(ch1 != '%')
            return true;
      return false;
   }
}
	
/********************************************

 Infix  	-->	Postfix		-->	Evaluate
 3 + 4 * 5			3 4 5 * +			23
 3 * 4 + 5			3 4 * 5 +			17
 ( -5 + 15 ) - 6 / 3			-5 15 + 6 3 / -			8
 ( 3 + 4 ) * ( 5 + 6 )			3 4 + 5 6 + *			77
 ( 3 * ( 4 + 5 ) - 2 ) / 5			3 4 5 + * 2 - 5 /			5
 8 + -1 * 2 - 9 / 3			8 -1 2 * + 9 3 / -			3
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78
 
***********************************************/