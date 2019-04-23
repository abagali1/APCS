// Name:   
// Date:
 
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
   //public List<String> getReachables(String from);  //Warshall extension
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

public class AdjMat implements AdjacencyMatrix 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name-->index (for Warshall & Floyd)
     
   public AdjMat(int size){
      grid = new int[size][size+1];
   } 
   public void addEdge(int source, int target){
      if(source >= grid.length || target >= grid[0].length-1){
         return;
      } 
      grid[source][target] = 1;
   }
   public void removeEdge(int source,int target){
      if(source >= grid.length || target >= grid[0].length-1){
         return;
      } 
      grid[source][target] = 0;
   }
   public boolean isEdge(int source,int target){
      if(source >= grid.length || target >= grid[0].length-1){
         return false;
      } 
      return grid[source][target] > 0;
   }
   public int edgeCount(){
      int result = 0;
      for(int i=0;i<=grid.length-1;i++){
         for(int j=0;j<grid[0].length-1;j++){
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
         for(int j=0;j<grid[0].length-1;j++){
            result += grid[i][j]+ " ";
         }
         result += "\n";
      }
      return result;
   }
   
   
}
