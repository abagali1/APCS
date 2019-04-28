// Name: Anup Bagali
// Date: 4/27/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 * Graphs4: DFS-BFS
 * and Graphs5: EdgeListCities
 */

/* Graphs 3: EdgeList 
 */
interface VertexInterface
{
   String toString(); // Don't use commas in the list.  Example: "C [C D]"
   String getName();
   ArrayList<Vertex> getAdjacencies();
   void addEdge(Vertex v);
} 

class Vertex implements VertexInterface 
{
   private String name;
   private ArrayList<Vertex> adjacencies;
   public Vertex(String name){
      this.name = name;
      this.adjacencies = new ArrayList<Vertex>();
   }
   public String getName(){
      return this.name;
   }
   public ArrayList<Vertex> getAdjacencies(){
      return this.adjacencies;
   }
   public void setName(String name){
      this.name = name;
   }
   public void addEdge(Vertex v){
      this.adjacencies.add(v);
   }
   public void removeEdge(Vertex v){
      this.adjacencies.remove(v);
   }
   public String toString(){
      String result = "";
      for(Vertex v: this.getAdjacencies()){
         result += v.getName() + " ";
      }
      result = this.getName() + " [" + result.substring(0,result.length()-1) + "]";
      return result;
   }
  
}   

interface AdjListInterface 
{ 
   List<Vertex> getVertices();
   Vertex getVertex(int i) ;
   Vertex getVertex(String vertexName);
   Map<String, Integer> getVertexMap();
   void addVertex(String v);
   void addEdge(String source, String target);
   String toString();  //returns all vertices with their edges (omit commas)
}

  
/* Graphs 4: DFS and BFS 
 */
interface DFS_BFS
{
   List<Vertex> depthFirstSearch(String name);
   List<Vertex> depthFirstSearch(Vertex v);
   List<Vertex> breadthFirstSearch(String name);
   List<Vertex> breadthFirstSearch(Vertex v);
   //List<Vertex> depthFirstRecur(String name);
   //List<Vertex> depthFirstRecur(Vertex v);
   //void depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/* Graphs 5: Edgelist with Cities 
 */
interface EdgeListWithCities
{
   void graphFromEdgeListData(String fileName) throws FileNotFoundException;
   int edgeCount();
   boolean isReachable(String source, String target);
   boolean isFullyReachable();
}


public class AdjList implements AdjListInterface  //DFS_BFS //EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
     
   public ArrayList<Vertex> getVertices(){
      return this.vertices;
   }
   public Vertex getVertex(int i){
      return this.vertices.get(i);
   }
   public Vertex getVertex(String vertexName){
      return this.vertices.get(nameToIndex.get(vertexName));
   }
   public Map<String, Integer> getVertexMap(){
      return this.nameToIndex;
   }
   public void addVertex(String v){
      Vertex temp = new Vertex(v);
      this.vertices.add(temp);
      this.nameToIndex.put(temp.getName(),this.vertices.indexOf(temp));
   }
   public void addEdge(String source, String target){
      if(nameToIndex.containsKey(source) && nameToIndex.containsKey(target)){
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }else if(!nameToIndex.containsKey(source) && nameToIndex.containsKey(target)){
         this.addVertex(source);
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }else if(nameToIndex.containsKey(source) && !nameToIndex.containsKey(target)){
         this.addVertex(target);
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }else{
         this.addVertex(source);
         this.addVertex(target);
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }
   }
   public String toString(){
      String result = "";
      for(Vertex v: this.getVertices()){
         if(v.getAdjacencies().size() != 0)
            result += v.toString()+"\n";
         else
            result += "";
      }
      return result;
   }
 
       
}



