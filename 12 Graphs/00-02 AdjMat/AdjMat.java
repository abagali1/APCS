 // Name: Anup Bagali  
// Date: 4/24/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 * Graph1 WarshallDriver,
 * and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   void addEdge(int source, int target);
   void removeEdge(int source, int target);
   boolean isEdge(int from, int to);
   String toString();   
   int edgeCount();
   List<Integer> getNeighbors(int source);
   //public ListString> getReachables(String from);  //Warshall extension
}

interface Warshall      
{
   boolean isEdge(String from, String to);
   Map<String, Integer> getVertices();     
   void readNames(String fileName) throws FileNotFoundException;
   void readGrid(String fileName) throws FileNotFoundException;
   void displayVertices();
   void allPairsReachability();  // Warshall's Algorithm
}

interface Floyd
{
   int getCost(int from, int to);
   int getCost(String from, String to);
   void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
     
   public AdjMat(int size){
      grid = new int[size][size];
      vertices = new TreeMap<String,Integer>();
   } 
   public Map<String,Integer> getVertices(){
      return vertices;
   }
   public void readGrid(String fileName) throws FileNotFoundException{
      Scanner infile = new Scanner(new File(fileName));
      int size = infile.nextInt();
      grid = new int[size][size];
      int count = 0;
      while(infile.hasNextLine()){
         String[] line = infile.nextLine().split(" ");
         if(line.length == size){
            for(int i=0;i<line.length;i++){
               grid[count][i] = Integer.parseInt(line[i]);
            }
            count++;
         }
      }
   }
   public void readNames(String fileName) throws FileNotFoundException{
      Scanner infile = new Scanner(new File(fileName));
      int size = infile.nextInt();
      int count = size;
      while(infile.hasNextLine()){
         vertices.put(infile.next(),size-count);
         count--;
      }
   }
   public void displayVertices(){
      for(String s: vertices.keySet()){
         System.out.println(vertices.get(s)+"-"+s);
      }
   }
   public void allPairsReachability(){
      for(String s: vertices.keySet()){
         for(int i=0;i<=grid.length-1;i++){
            for(int j=0;j<=grid.length-1;j++){
               if(grid[i][vertices.get(s)]==1 && grid[vertices.get(s)][j]==1 ){
                  grid[i][j] = 1;
               }
            }
         }
      }
   }
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source,int target){
      grid[source][target] = 0;
   }
   public boolean isEdge(int source,int target){
      if(source >= grid.length || target >= grid[0].length-1){
         return false;
      } 
      return grid[source][target] > 0;
   }
   public boolean isEdge(String source, String target){
      return grid[vertices.get(source)][vertices.get(target)] > 0;
   }
   public int edgeCount(){
      int result = 0;
      for(int i=0;i<=grid.length-1;i++){
         for(int j=0;j<=grid[0].length-1;j++){
            if(grid[i][j] > 0)
               result++;
         }
      }
      return result;
   }
   public List<Integer> getNeighbors(int source){
      if(source >= grid.length){
         return null;
      }
      List<Integer> result = new ArrayList<Integer>();
      for(int i=0;i<=grid[source].length-1;i++){
         if(grid[source][i] > 0)
            result.add(i);
      }
      return result;
   }
   public String toString(){
      String result = "";
      for(int i=0;i<=grid.length-1;i++){
         for(int j=0;j<=grid[0].length-1;j++){
            result += grid[i][j]+ " ";
         }
         result += "\n";
      }
      return result;
   }
   
   
}
