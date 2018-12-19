import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.7.jar
import java.util.*;
import java.io.*;
import javax.swing.*;

public class Panel extends JPanel{

   private int count =0;
   private JLabel text;
   private static PrintStream consolePrint;
   private TJTwitter bigB;
   public Panel(){
      consolePrint = System.out;
      bigB = new TJTwitter(consolePrint);
      
      
      setLayout(new FlowLayout());
      
       text = new JLabel("");
      this.add(text);
      
      if(bigB.investigate()){
         startParty();
      }else{
         doHW();
      }
      JButton again = new JButton("Try Again");
      again.addActionListener(new Listener()); 
      this.add(again); 
   }
   private class Listener implements ActionListener{
      public void actionPerformed(ActionEvent e){
         count++;
         if(bigB.investigate()){
            startParty();
         }else{
            doHW();
         }
         if(count > 5){
            JOptionPane.showMessageDialog(null,"The definition of insanity is doing the same thing over and over again and expecting a different result.");
         }
      }
   }
   public void startParty(){
      text.setText("NO SCHOOL!!!!!");
   }
   public void doHW(){
      text.setText("do your hw");
   }
   
}
