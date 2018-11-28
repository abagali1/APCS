// Name: Anup Bagali
// Date: 8-29-18
 
import java.text.DecimalFormat;
public class SmartCard_Driver
{
   public static void main(String[] args) 
   {
      Station downtown = new Station("Downtown", 1);
      Station center = new Station("Center City", 1);
      Station uptown = new Station("Uptown", 2);
      Station suburbia = new Station("Suburb", 4);
   
      SmartCard jimmy = new SmartCard(20.00); 
      jimmy.board(center);                    //Boarded at Center City.  SmartCard has $20.00
      jimmy.disembark(suburbia);              //From Center City to Suburb costs $2.75.  SmartCard has $17.25
      jimmy.disembark(uptown);				//Error:  did not board?!
      System.out.println();
   			
      SmartCard susie = new SmartCard(1.00); 
      susie.board(uptown);            		//Boarded at Uptown.  SmartCard has $1.00
      susie.disembark(suburbia);				//Insuffient funds to exit. Please add more money.
      System.out.println();
   
      SmartCard kim = new SmartCard(.25);    
      kim.board(uptown);            		    //Insuffient funds to board. Please add more money.
      System.out.println();
   
      SmartCard b = new SmartCard(10.00);   
      b.board(center);            		    //Boarded at Center City.  SmartCard has $10.00
      b.disembark(downtown);					//From Center City to Downtown costs $0.50.  SmartCard has $9.50
      System.out.println();
        
      SmartCard mc = new SmartCard(10.00);  
      mc.board(suburbia);            		    //Boarded at Suburbia.  SmartCard has $10.00
      mc.disembark(downtown);					//From Suburbia to Downtown costs $2.75.  SmartCard has $7.25
      System.out.println();
    
      SmartCard bad = new SmartCard(-5.0);
      bad.board(downtown);
      bad.disembark(center);
      System.out.println();
   }
} 	

//Note SmartCard is not denoted as public.  Why?
class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   public final static double MAX_FARE = 0.75;
   public double money;
   public Station boardedZone;
   public boolean boarded;
   
   public SmartCard(double m){
   
      money = m;
   
   }
   
   public void addMoney(double m){
   
      money += m;
   
   }
   
   public String getBalance(){
   
      return df.format(money);
   
   }
   
   public boolean isBoarded(){
   
      return boarded;
   
   }
   
   public void board(Station s){
   
      if(isBoarded()){
         System.out.println("Error: Already Boarded!");
         return;
      }
      
      if(money < MIN_FARE){
         System.out.println("Insufficient funds to board. Please add more money.");
         return;      
      }
      
      boarded = true;
      boardedZone = s;
      System.out.println("Boarded at "+s.getName()+". SmartCard has "+getBalance());
         
   }
   
   public double cost(Station s){
   
      double fare = MIN_FARE;
      
      if(s.getZone() == boardedZone.getZone()){
         return fare;
      }else{
         fare += Math.abs(s.getZone() - boardedZone.getZone()) * 0.75;
         return fare;
      }
      
   
   }
   
   public void disembark(Station s){
   
      if(!isBoarded()){
         System.out.println("Error: Did not board!");
         return;      
      }
      
      double fare = cost(s);
      
      if(fare > money){
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      }
      
      boarded = false;
      money -= fare;
      System.out.println("From " + boardedZone.getName() + " to " + s.getZone() + " cost " + df.format(fare) + ". SmartCard has " + getBalance());
   
   }
   
     
}
   
//Note Station is not a public class.  Why?
class Station
{
   private String name;
   private int zone;
   
   public Station(String n, int z){
   
      name = n;
      zone = z;
      
   }
   
   public String getName(){
   
      return name;
   
   }
   
   public int getZone(){
   
      return zone;
      
   }
   
   private void setName(String n){
   
      name = n;
   
   }
   
   private void setZone(int z){
   
      zone = z;
   
   }
   
   public String toString(){
   
      return "Station "+name+" at Zone "+zone;
   
   }  
}

 /*******************  Sample Run ************

 Boarded at Center City.  SmartCard has $20.00
 From Center City to Suburb costs $2.75.  SmartCard has $17.25
 Error: did not board?!
 
 Boarded at Uptown.  SmartCard has $1.00
 Insufficient funds to exit. Please add more money.
 
 Insufficient funds to board. Please add more money.
 
 Boarded at Center City.  SmartCard has $10.00
 From Center City to Downtown costs $0.50.  SmartCard has $9.50
 
 Boarded at Suburb.  SmartCard has $10.00
 From Suburb to Downtown costs $2.75.  SmartCard has $7.25
 
 ************************************************/