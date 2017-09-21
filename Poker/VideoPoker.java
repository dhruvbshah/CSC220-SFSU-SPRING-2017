package PJ4;
import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker. 
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. 
 * The player is dealt one five-card poker playerHand. 
 * After the first draw, which is automatic, you may hold any of the cards and draw 
 * again to replace the cards that you haven't chosen to hold. 
 * Your cards are compared to a table of winning combinations. 
 * The object is to get the best possible combination so that you earn the highest 
 * payout on the bet you placed. 
 *
 * Winning Combinations
 *  
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, 
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. 
 * 2. Two Pair: two sets of pairs of the same card denomination. 
 * 3. Three of a Kind: three cards of the same denomination. 
 * 4. Straight: five consecutive denomination cards of different suit. 
 * 5. Flush: five non-consecutive denomination cards of the same suit. 
 * 6. Full House: a set of three cards of the same denomination plus 
 * 	a set of two cards of the same denomination. 
 * 7. Four of a kind: four cards of the same denomination. 
 * 8. Straight Flush: five consecutive denomination cards of the same suit. 
 * 9. Royal Flush: five consecutive denomination cards of the same suit, 
 * 	starting from 10 and ending with an ace
 *
 */


/* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */



public class VideoPoker {

    // default constant values
    private static final int startingBalance=100;
    private static final int numberOfCards=5;

    // default constant payout value and playerHand types
    private static final int[] multipliers={1,2,3,5,6,9,25,50,250};
    private static final String[] goodHandTypes={ 
	  "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	  "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet    
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;
    Scanner scan = new Scanner(System.in);
    /** default constructor, set balance = startingBalance */
    public VideoPoker()
    {
	this(startingBalance);
    }

    /** constructor, set given balance */
    public VideoPoker(int balance)
    {
	this.playerBalance= balance;
        oneDeck = new Decks(1);
    }

    /** This display the payout table based on multipliers and goodHandTypes arrays */
    private void showPayoutTable()
    { 
	System.out.println("\n\n");
	System.out.println("Payout Table   	      Multiplier   ");
	System.out.println("=======================================");
	int size = multipliers.length;
	for (int i=size-1; i >= 0; i--) {
		System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
	}
	System.out.println("\n\n");
    }

    /** Check current playerHand using multipliers and goodHandTypes arrays
     *  Must print yourHandType (default is "Sorry, you lost") at the end of function.
     *  This can be checked by testCheckHands() and main() method.
     */
    private void checkHands()
    {
        // implement this method! 
        
        //mtaches playerHand accordingly to handTypes
        // basically a make-shift switch statement
        // must be ordered by highest to lowest handType
        List<Integer> handTypes = new ArrayList<>();
         for(int i = 0; i < 5; i++) {
             handTypes.add(playerHand.get(i).getRank());
        }
         Collections.sort(handTypes);

        if (royalFlush()) {
            System.out.println(goodHandTypes[8]);
            playerBalance += (playerBet * multipliers[8]);

        } else if (straightFlush(handTypes)) {
            System.out.println(goodHandTypes[7]);
            playerBalance += (playerBet * multipliers[7]);

        } else if (fourofAKind(handTypes)) {
            System.out.println(goodHandTypes[6]);
            playerBalance += (playerBet * multipliers[6]);

        } else if (FullHouse(handTypes)) {
            System.out.println(goodHandTypes[5]);
            playerBalance += (playerBet * multipliers[5]);

        } else if (Flush()) {
            System.out.println(goodHandTypes[4]);
            playerBalance += (playerBet * multipliers[4]);

        } else if (Straight(handTypes)) {
            System.out.println(goodHandTypes[3]);
            playerBalance += (playerBet * multipliers[3]);

        } else if (threeofAKind(handTypes)) {
            System.out.println(goodHandTypes[2]);
            playerBalance += (playerBet * multipliers[2]);

        } else if (twoPair(handTypes)) {
            System.out.println(goodHandTypes[1]);
            playerBalance += (playerBet * multipliers[1]);

        } else if (royalPair()) {
            System.out.println(goodHandTypes[0]);
            playerBalance += (playerBet * multipliers[0]);

        } else {
            System.out.println("Sorry, you lost.");
            
        }
    }

    /**
     * ***********************************************
     * add additional private methods here ....
     *
     ************************************************
     */
    //booleans to check handTypes
    private boolean royalPair() {
        int Aces = 0;
        int Jacks = 0;
        int Queens = 0;
        int Kings = 0;

        for (Card playerHand1 : playerHand) {
            if (playerHand1.getRank() == 1) {
                Aces++;
            }
            if (playerHand1.getRank() == 11) {
                Jacks++;
            }
            if (playerHand1.getRank() == 12) {
                Queens++;
            }
            if (playerHand1.getRank() == 13) {
                Kings++;
            }

        }
        return Aces == 2 || Jacks == 2 || Queens == 2 || Kings == 2;
    }

    private boolean twoPair(List<Integer> a) {
       	int twoPair = 0;
		for (int i = 0; i < a.size() - 1; i++) {
			if (a.get(i) == a.get(i + 1)) {
				twoPair++;
				i += 2;
			}
		}
		return twoPair == 2;
	}
    

    private boolean threeofAKind(List<Integer> b) {
        boolean threeofaKind = false;
        for (int i = 0; i < b.size() - 2; i++) {
            if (b.get(i) == b.get(i + 1) && b.get(i) == b.get(i + 2)) {
                threeofaKind = true;
            }
        }
        return threeofaKind;
    }


    private boolean FullHouse(List<Integer> c) {
        boolean threeofaKind = false;
        boolean twoPair = false;
        if (((c.get(0) == c.get(1)) && (c.get(0) == c.get(2)))) {
            for (int i = 0; i < c.size() - 2; i++) {
                if ((c.get(i) == c.get(i + 1)) && (c.get(i) == c.get(i + 2))) {
                    threeofaKind = true;
                }
            }
            if (threeofaKind = true) {
                for (int i = 3; i <= c.size() - 2; i++) {
                    if (c.get(i) == c.get(i + 1)) {
                        twoPair = true;

                    }
                }
            } else {
                for (int i = 0; i < c.size() - 3; i++) {
                    if (c.get(i) == c.get(i + 1)) {
                        twoPair = true;
                    }
                    if (twoPair = true) {
                        for (int j = 2; j < c.size() - 2; j++) {
                            if ((c.get(j) == c.get(j + 1)) && (c.get(j) == c.get(j + 2))) {
                                threeofaKind = true;
                            }
                        }

                    }
                }
            }

        }
        return (threeofaKind && twoPair);
	}

    

    private boolean fourofAKind(List<Integer> d) {
        boolean fourofaKind = false;
        for (int i = 0; i < d.size() - 3; i++) {
            if ((d.get(i) == d.get(i + 1)) && (d.get(i) == d.get(i + 2)) && (d.get(i) == d.get(i+3))) {
                fourofaKind = true;
            }

        }
        return fourofaKind ;
    }
    
    private boolean Straight(List<Integer> e) {
	  int straight = 0;
		for (int i = 0; i < e.size() - 1; i++) {
			if ((e.get(i) + 1) == (e.get(i + 1))) {
				straight++;
			}
		}
		return straight == 4;
	}

    private boolean Flush() {
        int flush = 0;
        for (int i = 0; i < 4; i++) {
            if( playerHand.get(i).getSuit() == playerHand.get(i + 1).getSuit()){
                flush++;
            }
        }
        return flush == 4;

    }

    private boolean straightFlush(List<Integer> f) {
   
        return (Flush() && Straight(f));

    }

    private boolean royalFlush() {
        int Ace = 0;
        int Jack = 0;
        int Queen = 0;
        int King = 0;
        int Ten =0;
        boolean royalFlush = false;
        for (Card playerHand1 : playerHand) {
            if (playerHand1.getRank() == 1) {
                Ace++;
            }
            else if (playerHand1.getRank() == 10) {
                Ten++;
            }
           else if (playerHand1.getRank() == 11) {
                Jack++;
            }
           else if (playerHand1.getRank() == 12) {
                Queen++;
            }
           else if (playerHand1.getRank() == 13) {
                King++;
                 royalFlush = true;
                }
         
            else {
            }
        }
        return (Ten == 1  && Jack == 1  && Queen == 1  && King  == 1  && Ace == 1  && Flush());
    }
    //prompts use if they want to change cards
    
    private void changeCards() {
        Scanner change = new Scanner (System.in);
        System.out.println("\nEnter positions of cards to keep (e.g. 1 4 5 )");
        	List<Card> keptCards = new ArrayList<>();
		List<Card> newCards= new ArrayList<>();
                String tokens = change.nextLine();
		Scanner scanTokens = new Scanner(tokens);
		scanTokens = scanTokens.useDelimiter("\\s*");
		while (scanTokens.hasNext()) {
			String numstring = scanTokens.findInLine("\\d+");
			int a = Integer.parseInt(numstring);
			if (a != 0) {
				keptCards.add(playerHand.get(a - 1));
			}
		}
		int count = 5;
		int counter = keptCards.size();
		while (count > counter) {
			try {
				newCards = oneDeck.deal(1);
			} catch (PlayingCardException e) {
				e.printStackTrace();
			}
			keptCards.add(newCards.get(0));
			count--;
		}
		playerHand = keptCards;
		System.out.println ("Hand: " + playerHand);
    }
    

    public void play() 
    {
    /** The main algorithm for single player poker game 
     *
     * Steps:
     * 		showPayoutTable()
     *
     * 		++	
     * 		show balance, get bet 
     *		verify bet value, update balance
     *		reset deck, shuffle deck, 
     *		deal cards and display cards
     *		ask for positions of cards to keep  
     *          get positions in one input line
     *		update cards
     *		check hands, display proper messages
     *		update balance if there is a payout
     *		if balance = O:
     *			end of program 
     *		else
     *			ask if the player wants to play a new game
     *			if the answer is "no" : end of program
     *			else : showPayoutTable() if user wants to see it
     *			goto ++
     */

        // implement this method!
        showPayoutTable();
        boolean balance = true;  
        while (balance) {
            System.out.println("Balance: " + playerBalance);
            boolean bet = true;
            while (bet) {
                System.out.print("Enter bet: ");
                playerBet = scan.nextInt();
                if (startingBalance >= playerBet && playerBet >= 0) {
                    System.out.println("You placed a bet of: $" + playerBet);               
                } 
                while(playerBet > playerBalance) {
                    System.out.println("Your balance is too low, try again.");
                     playerBet = scan.nextInt();
                }
            
                oneDeck.reset();
                oneDeck.shuffle();
                playerHand = new ArrayList<>();
                try {
                    playerHand = oneDeck.deal(numberOfCards);
                  
                } catch (PlayingCardException e) {
                    e.printStackTrace();
                }
                System.out.println("Hand: " + playerHand);
                changeCards();
                playerBalance = (playerBalance - playerBet);
                checkHands();

                System.out.println("Your current balance is: $" + this.playerBalance);
            
                if (playerBalance == 0) {
                    balance = false;
                    System.out.println("Your balance is too low to play! Bye!");
                    System.exit(0);
                } else {
                    System.out.println("Would you like to play again?  press [y] for yes, press [n] to end program");
                    String choice = scan.next();
                    if (choice.equals("n")) {
                        System.out.println("You have ended the program.");
                        balance = false;
                        System.exit(0);
                    } else {
                        System.out.println("Would you like to see the payout table again? press [y] for yes, press [n] for no");
                        String paychoice = scan.next();
                        if (paychoice.equals("y")) {
                            showPayoutTable();
                            System.out.println("---------------------------");
                        } else {
                            System.out.println("---------------------------");
                        }
                    }
                }
            }
        }

    }


    



    /*************************************************
     *   Do not modify methods below
    /*************************************************

    /** testCheckHands() is used to test checkHands() method 
     *  checkHands() should print your current hand type
     */ 

    public void testCheckHands()
    {
      	try {
    		playerHand = new ArrayList<Card>();

		// set Royal Flush
		playerHand.add(new Card(3,1));
		playerHand.add(new Card(3,10));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(3,11));
		playerHand.add(new Card(3,13));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight Flush
		playerHand.set(0,new Card(3,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Straight
		playerHand.set(4, new Card(1,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Flush 
		playerHand.set(4, new Card(3,5));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	", 
	 	// "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

		// set Four of a Kind
		playerHand.clear();
		playerHand.add(new Card(3,8));
		playerHand.add(new Card(0,8));
		playerHand.add(new Card(3,12));
		playerHand.add(new Card(1,8));
		playerHand.add(new Card(2,8));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Three of a Kind
		playerHand.set(4, new Card(3,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Full House
		playerHand.set(2, new Card(1,11));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Two Pairs
		playerHand.set(1, new Card(1,9));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// set Royal Pair
		playerHand.set(0, new Card(1,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");

		// non Royal Pair
		playerHand.set(2, new Card(3,3));
		System.out.println(playerHand);
    		checkHands();
		System.out.println("-----------------------------------");
      	}
      	catch (Exception e)
      	{
		System.out.println(e.getMessage());
      	}
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) 
    {
	VideoPoker pokergame = new VideoPoker();
	pokergame.testCheckHands();
    }
}
