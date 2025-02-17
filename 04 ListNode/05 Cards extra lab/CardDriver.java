// Name: Anup Bagali
// Date: 11/27/18

public class CardDriver
{
   public static void main(String[] args)
   {
       Card c = new Card(1, 12);
       System.out.println(c);
       
       Card c1 = new Card(Card.CLUBS, Card.KING);
       System.out.println(c1);
      
       CardDeck cd = new CardDeck();
       cd.printDeck(); 	
   
       System.out.println(cd.getTopCard().toString());
       System.out.println();
       cd.printDeck();
      
       cd.shuffle(5);
       cd.printDeck();    
   }
}

class CardDeck 
{
   private ListNode myCards;
   
   public CardDeck() 
   {
      for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++)
         for (int rank = Card.ACE; rank <= Card.KING; rank++) 
         {    
            Card card = new Card(suit, rank);
            putAtEnd(card);
         }
   } 
    
   /*  returns the top card from the deck.
       reassigns a pointer to the new top card.
    */
   public Card getTopCard() 
   {  
      Object t =myCards.getValue();
      ListNode next = myCards.getNext();
      myCards.setNext(null);
      myCards = next;
      return (Card)t;
      
   }
   
   /*  helper method to put a card at the end of the deck.
    */
   private void putAtEnd(Card c)
   {
      if(myCards == null){
         myCards = new ListNode(c,null);
      }else{
         ListNode t = new ListNode(c,myCards.getNext());
         myCards.setNext(t);
      }
   }
   
   public void printDeck()
   {
      ListNode pointer = myCards;
      for(pointer = myCards;pointer.getNext() != null;pointer=pointer.getNext()){
         System.out.print(pointer.getValue().toString()+", ");         
      }
      System.out.print(pointer.getValue()+"\n");
      
   }
   	
	/*  splits this deck into two almost equal halves;
		 chooses the split point randomly at 26 +- 10; 
	    the first half remains in this deck in the same order;
		 the second half is placed into a separate linked list in the same order;
		 @return a reference to the list that refers to the second half of the deck
	 */
   private ListNode split()
   {
      int index = (int)(16 + Math.random() * (36-16+1));
      ListNode pointer = myCards;
      for(int i=0;i<index;i++){
         pointer = pointer.getNext();
      }
      ListNode second = pointer.getNext();
      pointer.setNext(null);
      return second;
   }
      
   /*  combines the cards from cards1 and cards2 into one list.
		 takes one card from cards1, the next from cards2, the third
		 from cards1, and so on, alternating decks;  if there are cards
		 left over, all the rest of those cards are copied into the combined list.
		 @return a reference to the first node of the combined list.
	 */
   private ListNode combine(ListNode cards1, ListNode cards2)
   {
      ListNode result = cards1;
      for(cards1 = cards1.getNext();cards1 != null;cards1=cards1.getNext()){
         
         
         result.setNext(cards2);
         result = result.getNext();
         result.setNext(cards1);
         result = result.getNext();
         
         
         cards2 = cards2.getNext();
         
         
      }
      if(cards2 != null){
         for(cards2 = cards2;cards2!=null;cards2=cards2 = cards2.getNext()){
            result.setNext(cards2);
            result = result.getNext();
         } 
      }
      return result;
      
   }
      
      
   /*  splits the deck, then combines the 2 halves;
	    this operation is repeated numTimes number of times.
	 */
   public void shuffle(int numTimes)
   {
      for(int i=0;i<numTimes;i++){
         ListNode second = split();
         myCards = combine(myCards,second);
      }
   }
}

/*
using ideas from	http://www.ccs.neu.edu/jpt/fhs-04-05/Cards/CardSampler/
*/
class Card 
{

   public static final int CLUBS  = 0;     //for playing Bridge 
   public static final int DIAMONDS = 1;
   public static final int HEARTS = 2;
   public static final int SPADES = 3;
    
   public static final int ACE = 1;		   //Aces are always low
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
      
   private int rank;
   private int suit;
      
   private String[] rankList = {"","Ace","2","3","4","5","6","7","8","9","10","Jack","Queen","King"};
   private String[] suitList = {"Clubs","Diamonds","Hearts","Spades"};
     
   public Card(int s, int r) {
      if ((ACE <= r) && (r <= KING))
         this.rank = r;
      else
         throw new RuntimeException
                ("Invalid rank in Card constructor: " + rank);
        
      if ((CLUBS <= s) && (s <= SPADES))
         this.suit = s;
      else
         throw new RuntimeException
                ("Invalid suit in Card constructor: " + suit);
   }
   
   public int getRank() 
   { 
      return rank; 
   }
    
   public int getSuit() 
   { 
      return suit; 
   }
    
   public String getRankAsString() 
   { 
      return rankList[rank]; 
   }
    
   public String getSuitAsString() 
   { 
      return suitList[suit]; 
   }
         
   public String toString()
   {
      return ( getRankAsString()+" of "+getSuitAsString() );
   }
}

