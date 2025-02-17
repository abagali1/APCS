// Name: Anup Bagali
// Date: 5/23/19
 
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
      String temp = "";
      for(Vertex v: this.getAdjacencies()){
         temp += v.getName() + " ";
      }
      result = this.getName() + " [" + temp;
      return result.substring(0,result.length()-1)+"]";
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


public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
   private Map<String, Integer> nameToIndex = new TreeMap<String, Integer>();
   private int edgecount=0;
     
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
      if(!nameToIndex.containsKey(v)){
         Vertex temp = new Vertex(v);
         this.vertices.add(temp);
         this.nameToIndex.put(temp.getName(),this.vertices.indexOf(temp));
      }
   }
   public void addEdge(String source, String target){
      
      if(nameToIndex.containsKey(source) && nameToIndex.containsKey(target)){
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }
      else if(!nameToIndex.containsKey(source) && nameToIndex.containsKey(target)){
         this.addVertex(source);
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }
      else if(nameToIndex.containsKey(source) && !nameToIndex.containsKey(target)){
         this.addVertex(target);
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }
      else{
         this.addVertex(source);
         this.addVertex(target);
         this.vertices.get(this.nameToIndex.get(source)).addEdge(this.vertices.get(this.nameToIndex.get(target)));
      }
      edgecount++;
   }
   public int edgeCount(){
      int result = 0;
      for(Vertex v: this.vertices){
         result += v.getAdjacencies().size();
      }
      return result;
   }
   public boolean isReachable(String source, String target){
      return depthFirstSearch(source).contains(this.vertices.get(this.nameToIndex.get(target)));
   }
   public List<Vertex> depthFirstSearch(String name){
      return this.depthFirstSearch(this.vertices.get(nameToIndex.get(name)));
   }
   public List<Vertex> depthFirstSearch(Vertex v){
      List<Vertex> result = new ArrayList<Vertex>();
      Stack<Vertex> stack = new Stack<Vertex>();
      
      stack.push(v);
      result.add(stack.pop());
      ArrayList<Vertex> edges1 = v.getAdjacencies();
      for(Vertex ve: edges1){
         stack.push(ve);
      }
      while(!stack.isEmpty()){
         Vertex temp = stack.pop();
         if(!result.contains(temp)){
            result.add(temp);
            ArrayList<Vertex> edges = temp.getAdjacencies();
            for(Vertex ve: edges){
               stack.push(ve);
            }
         }
      }
      return result;
   }
   public List<Vertex> breadthFirstSearch(String name){
      return this.breadthFirstSearch(this.vertices.get(nameToIndex.get(name)));
   }
   public List<Vertex> breadthFirstSearch(Vertex v){
      List<Vertex> result = new ArrayList<Vertex>();
      Queue<Vertex> temp = new LinkedList<Vertex>();
      
      temp.add(v);
      result.add(temp.remove());
      ArrayList<Vertex> edges1 = v.getAdjacencies();
      for(Vertex ve: edges1){
         temp.add(ve);
      }
      while(!temp.isEmpty()){
         Vertex vt = temp.remove();
         if(!result.contains(vt)){
            result.add(vt);
            ArrayList<Vertex> edges = vt.getAdjacencies();
            for(Vertex ve: edges){
               temp.add(ve);
            }
         }
      }
      return result;
   }
   public void graphFromEdgeListData(String fileName) throws FileNotFoundException{
      Scanner infile = new Scanner(new File(fileName));
      
      while(infile.hasNextLine()){
         String[] line = infile.nextLine().split(" ");
         this.addEdge(line[0],line[1]);
      }
   }
   public boolean isFullyReachable(){
      for(String i: nameToIndex.keySet())
         for(String j:nameToIndex.keySet())
            if(!isReachable(i,j))
               return false;
      return true;
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



