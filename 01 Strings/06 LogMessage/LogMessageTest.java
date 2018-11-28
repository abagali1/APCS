// name:Anup Bagali
// date: 9-1-18
import java.util.*;

public class LogMessageTest
{
   public static void main(String[] args)
   {
      String[] messages = {
         "CLIENT3:security alert - repeated login failures",
         "Webserver:disk offline",
         "SERVER1:file not found",
         "SERVER2:read error on disk DSK1",
         "SERVER1:write error on disk DSK2",
         "Webserver:error on /dev/disk",
         "True:disk",
         "True:error on disk",
         "True:error on /dev/disk disk",
         "True:error on disk DSK1",
         "False:DISK",
         "False:error on disk3",
         "False:error on /dev/disk",
         "False:diskette"};
   
    // Parts A and B
      for (String s : messages)
      {
         LogMessage msg = new LogMessage(s);
         System.out.println(msg.getMachineId() + ":" + msg.getDescription() + " ==> " + msg.containsWord("disk"));
      }
    
   // Part C
      SystemLog theLog  = new SystemLog(messages);
      LogMessage[] removed = theLog.removeMessages("disk");
        
      System.out.println();
    
      System.out.println("Removed messages:\n");
      for (LogMessage msg : removed)
         System.out.println(msg);
      System.out.println();
    
      System.out.println("Remaining messages:\n");
      System.out.println(theLog);
    
   }
}

class LogMessage
{
   private String machineId;
   private String description;

   /* Part (a) */
   public LogMessage(String message)
   {
      machineId = message.substring(0,message.indexOf(":"));
      description = message.substring(message.indexOf(":")+1,message.length());
   
   }

   /* Part (b) */
   public boolean containsWord(String keyword){
      
      return getDescription().contains(keyword) && ((getDescription().indexOf(keyword) == 0) || (getDescription().contains(" "+keyword))) && ((getDescription().indexOf(keyword) == (getDescription().length() - keyword.length()) || (getDescription().contains(keyword+" "))));
      
   }

   public String getMachineId()
   { 
      return machineId; 
   }

   public String getDescription()
   { 
      return description; 
   }

   public String toString()
   {
      return getMachineId() + ":" + getDescription();
   }
}

class SystemLog
{
   private LogMessage[] messageList;

   public SystemLog(String[] messages)
   {
      messageList = new LogMessage[messages.length];
      for (int i=0;i<messages.length; i++)
         messageList[i]=(new LogMessage(messages[i]));
   }

  // Part (c) 

   public LogMessage[] removeMessages(String keyword)
   {
      int count = 0;
     
      for(LogMessage l: messageList)
         if(l.containsWord(keyword))
            count++;
      
      if(count == 0)
         return new LogMessage[0];
         
      
      LogMessage[] result = new LogMessage[count];
      int index = 0;
      
      for(int i=0;i<=messageList.length-1;i++){
         if(messageList[i].containsWord(keyword)){
            result[index] = messageList[i];
            index++;
         }
      }
      
      index = 0;
      LogMessage[] temp = messageList;
      messageList = new LogMessage[temp.length-count];
      for(int i=0;i<=temp.length-1;i++){
         if(!temp[i].containsWord(keyword)){
            messageList[index] = temp[i];
            index++;
         }
      }
            
      
      return result;
       
   }    


   public String toString()
   {
      String s = "";
      for (LogMessage msg : messageList)
         s += msg + "\n";
      return s;
   }
}

 /**************** Sample output:

   // Parts a and b   

 CLIENT3:security alert - repeated login failures ==> false
 Webserver:disk offline ==> true
 SERVER1:file not found ==> false
 SERVER2:read error on disk DSK1 ==> true
 SERVER1:write error on disk DSK2 ==> true
 Webserver:error on /dev/disk ==> false
 True:disk ==> true
 True:error on disk ==> true
 True:error on /dev/disk disk ==> true
 True:error on disk DSK1 ==> true
 False:DISK ==> false
 False:error on disk3 ==> false
 False:error on /dev/disk ==> false
 False:diskette ==> false
 
 
 // Part c  
 
 Removed messages:
 
 Webserver:disk offline
 SERVER2:read error on disk DSK1
 SERVER1:write error on disk DSK2
 True:disk
 True:error on disk
 True:error on /dev/disk disk
 True:error on disk DSK1
 
 Remaining messages:
 
 CLIENT3:security alert - repeated login failures
 SERVER1:file not found
 Webserver:error on /dev/disk
 False:DISK
 False:error on disk3
 False:error on /dev/disk
 False:diskette
    
 **********************************************/

