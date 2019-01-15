// Name: Anup Bagali
// Date: 1/15/19

import java.io.*;
import java.util.*;
import com.google.api.client.auth.oauth2.Credential;


public class SongQueue
{
   private static Scanner keyboard;  //use this global Scanner for this lab only
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      keyboard = new Scanner(System.in);
      songQueue = readPlayList();
      printSongList();
      
      String prompt = "Add song (A), Play song (P), Delete song (D), Quit (Q):  ";
      String str = "";
      do{      
         System.out.print(prompt);
         str = keyboard.nextLine().toUpperCase();
         processRequest( str );
         //System.out.println();
      }while(!str.equals("Q"));
      System.out.println("No more music for you today. Goodbye!");
      
   }
   
   public static Queue<String> readPlayList() throws IOException
   {
      Scanner infile = new Scanner (new File("songs.txt"));
      Queue<String> result = new LinkedList<String>();
      while(infile.hasNextLine()){
         String n = infile.nextLine();
         result.add(n.substring(0,n.indexOf("-")-1));
      }        
      return result;
   }
   
   public static void processRequest(String str)
   {
      if("A".equals(str)){
         add();
      }
      else if("P".equals(str)){
         play();
      }
      else if("D".equals(str)){
         delete();
      }
   }
   
   public static void add(){
      System.out.print("Song to add? ");
      String song = keyboard.next();
      songQueue.add(song);
      System.out.println("Your music queue: " + songQueue.toString());
   }
   
   public static void play(){
      if(songQueue.isEmpty()){
         System.out.println("Empty queue!");
      }
      else{
         System.out.println("Now playing: "+songQueue.remove());
      }
   }
   
   public static void delete(){
      System.out.println("Your song queue: " + songQueue.toString());
      System.out.print("Enter song to delete (exact match): ");
      String song = keyboard.nextLine();
      boolean contains = false;
      Queue<String> safe = songQueue;
      Queue<String> temp = new LinkedList<String>();
      while(!songQueue.isEmpty()){
         if(!(songQueue.peek().equals(song))){
            temp.add(songQueue.remove());
         }
         else{
            songQueue.remove();
            contains = true;
         }
      }
      songQueue = temp;
      if(contains){
         System.out.println("Your music queue: " + songQueue.toString());
      }
      else{
         System.out.println("Error: Song not in list.");
      }
   }
   
   public static void printSongList()
   {
      System.out.println("Your music queue: " +songQueue.toString());  
   }
   
   public static Queue<String> getQueue()
   {
      return songQueue;
   }
}

/*********************************************

 Your music queue: [Really Love, Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Really Love
 Your music queue: [Uptown Funk, Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Uptown Funk
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Alright, Traveller, Alright]
 Delete which song (exact match)?  Alright
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  d
 Your music queue: [Thinking Out Loud, Traveller]
 Delete which song (exact match)?  xxx
 Error:  Song not in list.
 Your music queue: [Thinking Out Loud, Traveller]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  a
 Song to add? Girl Crush
 Your music queue: [Thinking Out Loud, Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Thinking Out Loud
 Your music queue: [Traveller, Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Traveller
 Your music queue: [Girl Crush]
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Now playing: Girl Crush
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  p
 Empty queue!
 Your music queue: []
 
 Add song (A), Play song (P), Delete song (D), Quit (Q):  q
 
 No more music today!

**********************************************/