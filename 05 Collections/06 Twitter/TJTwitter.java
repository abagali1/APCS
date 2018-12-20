import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.7.jar
import java.util.*;
import java.io.*;
import javax.swing.*;

public class TJTwitter {
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private int numberOfTweets; 
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
  
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      // Connects to Twitter and performs authorizations.
      twitter = TwitterFactory.getSingleton(); 
      consolePrint = console;
      statuses = new ArrayList<Status>();
      terms = new ArrayList<String>();
   }
   public List<String> getTerms()
   {
      return terms;
   }
   public int getNumberOfTweets()
   {
      return numberOfTweets;
   }
   public String getMostPopularWord()
   {
      return popularWord;
   }
   public int getFrequencyMax()
   {
      return frequencyMax;
   }
  /******************  Part 1 *******************/
  /** 
   * This method tweets a given message.
   * @param String  a message you wish to Tweet out
   */
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);  
   }

   
  /******************  Part 2 *******************/
  /** 
   * This method queries the tweets of a particular user's handle.
   * @param String  the Twitter handle (username) without the @sign
   */
   @SuppressWarnings("unchecked")
   public void queryHandle(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      terms.clear();
      fetchTweets(handle);
      splitIntoWords();	
      removeCommonEnglishWords();
      sortAndRemoveEmpties();
      mostPopularWord(); 
   }
	
  /** 
   * This method fetches the most recent 2,000 tweets of a particular user's handle and 
   * stores them in an arrayList of Status objects.  Populates statuses.
   * @param String  the Twitter handle (username) without the @sign
   */
   public void fetchTweets(String handle) throws TwitterException, IOException
   {
      // Creates file for dedebugging purposes
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); 
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      numberOfTweets = statuses.size();
      fileout.println("Number of tweets = " + numberOfTweets);
   
   }   

  /** 
   * This method takes each status and splits them into individual words.   
   * Remove punctuation by calling removePunctuation, then store the word in terms.  
   */
   public void splitIntoWords()
   {
   
      
      for(Status t: statuses){
         String[] temp = t.getText().split(" ");
         for(String b: temp){
            terms.add(removePunctuation(b).toLowerCase());
         }
      }
   }

  /** 
   * This method removes punctuation (but not apostrophes) from each individual word.
   * Consider reusing code you wrote for a previous lab.     
   * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
   * @ param String  the word you wish to remove punctuation from
   * @ return String the word without any punctuation  
   */
   public String removePunctuation( String s )
   {
      char[] punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()".toCharArray(); 
      
      for(char c: punct)
         s = s.replace(c+"","");
      
      return s.toLowerCase();
   
   }

  /** 
   * This method removes common English words from the list of terms.
   * Remove all words found in commonWords.txt  from the argument list.    
   * The count will not be given in commonWords.txt. You must count the number of words in this method.  
   * This method should NOT throw an excpetion.  Use try/catch.   
   */
   @SuppressWarnings("unchecked")
   public void removeCommonEnglishWords()
   {	
   
      Scanner infile;
      try{ 
         infile = new Scanner(new File("commonWords.txt")); 
      }
      catch(Exception e){
         System.out.println(e); 
         return;
      }
      ArrayList<String> words = new ArrayList<String>();
      while(infile.hasNext())
         words.add(infile.next().toLowerCase());
            
      ListIterator<String> it= terms.listIterator();
      
      while(it.hasNext()){
         if(words.contains(it.next()))
            it.remove();
      }
   }

  /** 
   * This method sorts the words in terms in alphabetically (and lexicographic) order.  
   * You should use your sorting code you wrote earlier this year.
   * Remove all empty strings while you are at it.  
   */
   @SuppressWarnings("unchecked")
   public void sortAndRemoveEmpties()
   {
   
      sort(terms);
      ListIterator<String> it = terms.listIterator();
      while(it.hasNext())
         if(it.next().isEmpty())
            it.remove(); 
      
      
   }
   public static void sort(List<String> array)
   {
      int maxPos;
      for(int k = 0; k < array.size(); k++)
      {
         maxPos = findMax(array, array.size() - k-1);
         swap(array, maxPos, array.size() - k - 1);
      }
   }
   @SuppressWarnings("unchecked")
    public static int findMax(List<String> array, int upper)
   {
      int max = 0;
      for(int i =0;i<=upper;i++){
         if(array.get(i).compareTo(array.get(max)) > 0)
            max = i;
      }
      return max;
   }
   public static void swap(List<String> array, int a, int b)
   {
      String temp = array.get(a);
      array.set(a,array.get(b));
      array.set(b,temp);
   }

  
  /** 
   * This method returns the most common word from terms.    
   * Consider case - should it be case sensitive?  The choice is yours.
   * @return String the word that appears the most times
   * @post will popopulate the frequencyMax variable with the frequency of the most common word 
   */
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      String most = null;
      String prev = null;
      int num =0;
      int max=0;
      for(String s:terms){
         if(s.equals(prev)){
            num++;
         }
         else{
            if(num>max){
               max = num;
               most = prev;
            }
            num = 1;
            prev =s;
         }
      }
      frequencyMax = max;
      return most;   
   }


  /******************  Part 3 *******************/
   public boolean investigate ()
   {
      Query query = new Query("drake");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(37.0902,95.7129), Double.MAX_VALUE, Query.MILES);
      query.setSince("2010-11-15");
      int count = 0;
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
            //count += result.getTweets().size();
         }
         System.out.println(result.getTweets().size() +" drake fans on twitter");
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
      return true;
   }
 
  /** 
   * This method determines how many people in Arlington, VA 
   * tweet about the Miami Dolphins.  Hint:  not many. :(
   */
   public void sampleInvestigate ()
   {
      Query query = new Query("offices are closed today");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2018-1-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  
}