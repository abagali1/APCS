// Name: Anup Bagali
// Date: 4/9/19 
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
         
      PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<HuffmanTreeNode>();
      HashMap<String, Integer> freq = new HashMap<String,Integer>();
         
      char[] temp = message.toCharArray();
      for(char c: temp){
         if(freq.containsKey(c+"")){
            freq.replace(c+"",freq.get(c+"")+1);
         }
         else{
            freq.put(c+"",1);
         }
      }
      
      for(String s:freq.keySet()){
         q.add(new HuffmanTreeNode(s,freq.get(s)));
      }
      while(q.size() != 1){
         HuffmanTreeNode first = q.remove();
         HuffmanTreeNode second = q.remove();
      
         
         //PriorityQueue<HuffmanTreeNode> temp = new PriorityQueue<HuffmanTreeNode>();
         q.add(new HuffmanTreeNode("*",first.getFreq()+second.getFreq(),first,second));
         
      }
      HashMap<String, String> code = new HashMap<String,String>();
      PrintWriter m = new PrintWriter("message."+middlePart+".txt","UTF-8");
      PrintWriter c = new PrintWriter("scheme."+middlePart+".txt","UTF-8");
      for(String s: freq.keySet()){
         String toWrite = getCode(q.peek(),s,"");
         code.put(s,toWrite);
         toWrite = (s)+toWrite;
         c.println(toWrite);
      }
      c.close();
      String result = "";
      for(char ch: temp){
         result += code.get(ch+"");
      }
      m.println(result);
      m.close();
      
     // System.out.println(q);
            
   }
   public static String getCode(HuffmanTreeNode t,String letter, String path){
      if( letter.equals(t.getValue())){
         return path;
      }
      if(t.getLeft() == null && t.getRight() == null){
         return "";
      }
      return getCode(t.getLeft(),letter,path+"0") + getCode(t.getRight(),letter,path+"1");
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String obj;
   private int freq;
   private HuffmanTreeNode left;
   private HuffmanTreeNode right;
   
   public HuffmanTreeNode(){
      obj = null;
      freq = 0;
      left = right = null;
   }
   public HuffmanTreeNode(String o, int f, HuffmanTreeNode l, HuffmanTreeNode r){
      obj = o;
      freq = f;
      left = l;
      right = r;
   }
   public HuffmanTreeNode(String o, int f){
      obj = o;
      freq = f;
   }
   public void setFrequency(int f){
      freq=f;
   }
   public void setValue(String o){
      obj = o;
   }
   public void setLeft(HuffmanTreeNode l){
      left = l;
   }
   public void setRight(HuffmanTreeNode r){
      right = r;
   }
   public HuffmanTreeNode getRight(){
      return right;
   }
   public HuffmanTreeNode getLeft(){
      return left;
   }
   public Object getValue(){
      return obj;
   }
   public int getFreq(){
      return freq;
   }
   
   public int compareTo(HuffmanTreeNode n){
      if(this.freq > n.freq){
         return 1;
      }
      else if(this.freq == n.freq){
         if("*".equals(n.obj)){
            return 1;
         }
         if( (this.obj).compareTo(n.obj) > 0){
            return 1;
         }
         else if( (this.obj).compareTo(n.obj) == 0){
            return 0;
         }
         else{
            return -1;
         }
      }
      else{
         return -1;
      }
   }
   public String toString(){
      return obj+":"+freq;
   }
   
}
