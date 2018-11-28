//name: Anup Bagali
//date: 8-31-18  
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static final String VOWELS = "aeiouAEIOU";
   public static void main(String[] args) 
   {
      part_1_using_pig();
      // part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println( p );
      }		
   }
   public static String pig(String s)
   {
      if(s.isEmpty())
         return "";
      
      if(!s.contains("a") && !s.contains("e") && !s.contains("i") && !s.contains("o") && !s.contains("u") && !s.contains("A") && !s.contains("E") && !s.contains("I") && !s.contains("O") && !s.contains("U") && !(s.contains("y") && s.indexOf("y") != 0) && !(s.contains("Y") && s.indexOf("Y") != 0))
         return "**** NO VOWEL ****";
      
      boolean upCase = false;
      boolean punc = false;
      
      if(Character.isUpperCase(s.charAt(0))){
         s = s.substring(0, 1).toLowerCase() + s.substring(1);
         upCase = true;
      }
      
      
      String leadingConst = "";
      
      if(isVowel(s.charAt(0),0)){
         String temp;
         if(s.contains("!")){
            temp = s.replace("!","");
            temp = temp + "way" + s.substring(s.indexOf("!"),s.lastIndexOf("!")+1);
         }
         else if(s.contains("?")){
            temp = s.replace("?","");
            temp = temp + "way" + s.substring(s.indexOf("?"),s.lastIndexOf("?")+1);
         }
         else if(s.contains(".")){
            temp = s.replace(".","");
            temp = temp + "way" +  s.substring(s.indexOf("."),s.lastIndexOf(".")+1);
         }
         else if(s.contains(":")){
            temp = s.replace(":","");
            temp = temp + "way" +  s.substring(s.indexOf(":"),s.lastIndexOf(":")+1);
         }
         else if(s.contains(";")){
            temp = s.replace(";","");
            temp = temp + "way" +  s.substring(s.indexOf(";"),s.lastIndexOf(";")+1);
         }
         else if(s.contains("\"")){
            temp = s.replace("\"","");
            temp = "\"" + s + "way\"";
         }
         else{
            temp = s + "way";
         }
         if(upCase){
            temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
         }
         return temp;
         
      }
      else{
         
         for(int i=0;i<=s.toCharArray().length-1;i++){
         
            if(!isVowel(s.toCharArray()[i],i)){
               leadingConst += s.toCharArray()[i];
            }
            else{
               break;
            }
         }
         
         String result = s.substring(s.indexOf(leadingConst.charAt(leadingConst.length()-1))+1,s.length()) + leadingConst + "ay";
      
         if(s.contains("!")){
            result = result.replace("!","");
            result = result + s.substring(s.indexOf("!"),s.lastIndexOf("!")+1);
         }
         if(s.contains("?")){
            result = result.replace("?","");
            result = result + s.substring(s.indexOf("?"),s.lastIndexOf("?")+1);
         }
         if(s.contains(".")){
            result = result.replace(".","");
            result = result + s.substring(s.indexOf("."),s.lastIndexOf(".")+1);
         }
         if(s.contains(",")){
            result = result.replace(",","");
            result = result + s.substring(s.indexOf(","),s.lastIndexOf(",")+1);
         }
         if(s.contains(":")){
            result = result.replace(":","");
            result = result + s.substring(s.indexOf(":"),s.lastIndexOf(":")+1);
         }
         if(s.contains(";")){
            result = result.replace(";","");
            result = result + s.substring(s.indexOf(";"),s.lastIndexOf(";")+1);
         }
         
         if(upCase)
            result = result.substring(0, 1).toUpperCase() + result.substring(1);
         
         if(result.toLowerCase().charAt(0) == 'u' && result.toLowerCase().charAt(result.length()-3) == 'q'){
            result = result.substring(1,result.indexOf("ay")) + "uay";
         
            if(upCase)
               result = result.substring(0, 1).toUpperCase() + result.substring(1);
         }
         if(result.contains("\"")){
            result = result.replace("\"","");
            if(Character.isUpperCase(s.charAt(1)))
               result = "\"" + result.substring(0,1).toUpperCase() + result.substring(1).toLowerCase() + "\"";
            else
               result = "\"" + result + "\"";
         }
         return result;
      }
      
   }

   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }
   
   public static boolean isVowel(char c, int index){
   
      return (VOWELS.contains(String.valueOf(c)) || (index != 0 && c == 'y') || (index != 0 && c == 'Y'));
   }

/****************************** 
*  precondition:  both Strings include .txt
*  postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	
      while(infile.hasNextLine()){
         String[] line = infile.nextLine().split(" ");
         for(String s: line){
            outfile.append(pig(s) + " ");
         }
         outfile.append("\n");
         
      }
   
      outfile.close();
      infile.close();
   }
   
   public static String pigReverse(String s){
      String punctuation = ".,?!;:\"";
      String pigged = pig(s);
      String result = "";
      boolean upCase = false;
      
      if(Character.isUpperCase(s.charAt(0))){
         pigged = pigged.substring(0,1).toLowerCase() + pigged.substring(1);
         upCase = true;
      }
      
      for(int i=pigged.length()-1;i>=0;i--){
         result = result + pigged.charAt(i);
      }
      if(s.contains("!")){
         result = result.replace("!","");
         result = result + s.substring(s.indexOf("!"),s.lastIndexOf("!")+1);
      }
      if(s.contains("?")){
         result = result.replace("?","");
         result = result + s.substring(s.indexOf("?"),s.lastIndexOf("?")+1);
      }
      if(s.contains(".")){
         result = result.replace(".","");
         result = result + s.substring(s.indexOf("."),s.lastIndexOf(".")+1);
      }
      if(s.contains(",")){
         result = result.replace(",","");
         result = result + s.substring(s.indexOf(","),s.lastIndexOf(",")+1);
      }
      if(s.contains(":")){
         result = result.replace(":","");
         result = result + s.substring(s.indexOf(":"),s.lastIndexOf(":")+1);
      }
      if(s.contains(";")){
         result = result.replace(";","");
         result = result + s.substring(s.indexOf(";"),s.lastIndexOf(";")+1);
      }
         
      if(upCase)
         result = result.substring(0, 1).toUpperCase() + result.substring(1);
         
      if(result.toLowerCase().charAt(0) == 'u' && result.toLowerCase().charAt(result.length()-3) == 'q'){
         result = result.substring(1,result.indexOf("ay")) + "uay";
         
         if(upCase)
            result = result.substring(0, 1).toUpperCase() + result.substring(1);
      }
      if(result.contains("\"")){
         result = result.replace("\"","");
         if(Character.isUpperCase(s.charAt(1)))
            result = "\"" + result.substring(0,1).toUpperCase() + result.substring(1).toLowerCase() + "\"";
         else
            result = "\"" + result + "\"";
      }
   
      return result;
   }
}
