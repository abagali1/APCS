//Name: Anup Bagali 
//Date: 12/13/18
//The Twitter API is at http://twitter4j.org

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.7.jar
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Twitter_Driver
{
   private static PrintStream consolePrint;
   public static void main(String[] args) throws TwitterException, IOException{
   
      consolePrint = System.out; // this preserves the standard output so we can get to it later      
   
      // PART 1
      // set up classpath and properties file
          
      TJTwitter bigBird = new TJTwitter(consolePrint);
      
      // Create and set a String called message here
   
      
      String message = "first twee1t";
      //bigBird.tweetOut(message);
      
       
   
      // PART 2
      // Choose a public Twitter user's handle 
      /*
      Scanner scan = new Scanner(System.in);
      consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
      String twitter_handle = scan.next();
       
      // Find and print the most popular word they tweet 
      while (!twitter_handle.equals("done"))
      {
         bigBird.queryHandle(twitter_handle);
         consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord()+ ".");
         consolePrint.println("The word appears " + bigBird.getFrequencyMax() + " times.");
         consolePrint.println();
         consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
         twitter_handle = scan.next();
      }
      */
      
      // PART 3

      bigBird.investigate();
      
      
   }//end main         
      
}//end driver        
      
 


