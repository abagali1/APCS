// Name: Anup Bagali
// Date: 5/30/19
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs6: Dijkstra
 * and Graphs7: Dijkstra with Cities
 */

class Edge 
{
   //public fields not common on AP exam
   public final wVertex target;  
   public final double weight;   
  
   public Edge(wVertex argTarget, double argWeight) 
   {
      target = argTarget;
      weight = argWeight;
   }
   
   public String toString(){
      return target.getName() + ": " + weight;
   }
}

interface wVertexInterface 
{
   String getName();
   double getMinDistance();
   void setMinDistance(double m);
   wVertex getPrevious();   //for Dijkstra 7
   void setPrevious(wVertex v);  //for Dijkstra 7
   ArrayList<Edge> getAdjacencies();
   void addEdge(wVertex v, double weight);   
   int compareTo(wVertex other);
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
   private wVertex previous;  //for building the actual path in Dijkstra 7
   
   public wVertex(String name){
      this.name = name;
      adjacencies = new ArrayList<Edge>();
   }
   
   public String getName(){
      return this.name;
   }
   
   public double getMinDistance(){
      return this.minDistance;
   }
   
   public wVertex getPrevious(){
      return this.previous;
   }
   
   public ArrayList<Edge> getAdjacencies(){
      return this.adjacencies;
   }
   
   public void setMinDistance(double minDistance){
      this.minDistance = minDistance;
   }
   
   public void setPrevious(wVertex v){
      this.previous = v;
   }
   
   public void addEdge(wVertex v, double weight){
      this.adjacencies.add(new Edge(v, weight));
   }
   
   public int compareTo(wVertex v){
      return (int)(this.minDistance - v.minDistance);
   }
   
   public String toString(){
      return this.getName() + ": " + this.getMinDistance();
   }
   
}

interface AdjListWeightedInterface 
{
   List<wVertex> getVertices();
   Map<String, Integer> getNameToIndex();
   wVertex getVertex(int i);   
   wVertex getVertex(String vertexName);
   void addVertex(String v);
   void addEdge(String source, String target, double weight);
   void minimumWeightPath(String vertexName);   //Dijkstra's
}

/* Interface for Graphs 7:  Dijkstra with Cities 
 */

interface AdjListWeightedInterfaceWithCities 
{       
   List<String> getShortestPathTo(wVertex v);
   AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException ;
}
 

public class AdjListWeighted implements AdjListWeightedInterface, AdjListWeightedInterfaceWithCities
{
   private List<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();

   public List<wVertex> getVertices(){
      return this.vertices;
   }
   
   public Map<String, Integer> getNameToIndex(){
      return this.nameToIndex;
   }
   
   public wVertex getVertex(int index){
      return this.getVertices().get(index);
   }
   
   public wVertex getVertex(String name){
      return this.getVertices().get(this.getNameToIndex().get(name));
   }
   
   public void addVertex(String v){
      wVertex temp = new wVertex(v);
      this.getVertices().add(temp);
      this.getNameToIndex().put(v, this.getVertices().indexOf(temp));
   }
   
   public void addEdge(String source, String target, double weight){
      getVertex(source).addEdge(getVertex(target),weight);
   }
   
   public void minimumWeightPath(String vertexName){
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      this.getVertex(vertexName).setMinDistance(0);
      pq.add(this.getVertex(vertexName));
      while(!pq.isEmpty()){
         wVertex temp = pq.remove();
         for(Edge e: temp.getAdjacencies()){
            if(temp.getMinDistance() + e.weight < e.target.getMinDistance()){
               if(pq.contains(e.target)){
                  pq.remove(e.target);
               }
               e.target.setMinDistance(temp.getMinDistance() + e.weight);
               pq.add(e.target);
               e.target.setPrevious(temp);
            }
         }
      }
   }
   
   public List<String> getShortestPathTo(wVertex v){
      ArrayList<String> result = new ArrayList<String>();
      while(v != null ){
         result.add(0,v.getName());
         v = v.getPrevious();
      }
      return result;
   }
   
   public AdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException{
      Scanner infile = new Scanner(vertexNames);
      infile.nextInt();
      while(infile.hasNextLine()){
         this.addVertex(infile.next());
      }
      
      infile = new Scanner(edgeListData);
      while(infile.hasNextLine()){
         String[] temp = infile.nextLine().split(" ");
         this.addEdge(temp[0],temp[1],Integer.parseInt(temp[2]));
      }
      return this;
   }
   /*  enter your code for two new methods in Graphs 7 */
   
   
}   


