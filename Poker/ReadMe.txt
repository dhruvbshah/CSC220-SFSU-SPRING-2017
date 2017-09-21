{\rtf1\ansi\ansicpg1252\cocoartf1504\cocoasubrtf830
{\fonttbl\f0\fnil\fcharset0 Menlo-Regular;}
{\colortbl;\red255\green255\blue255;\red0\green116\blue0;\red14\green14\blue255;\red0\green0\blue0;
}
{\*\expandedcolortbl;;\csgenericrgb\c0\c45600\c0;\csgenericrgb\c5500\c5500\c100000;\csgenericrgb\c0\c0\c0;
}
\margl1440\margr1440\vieww10800\viewh8400\viewkind0
\deftab543
\pard\tx543\pardeftab543\pardirnatural\partightenfactor0

\f0\fs22 \cf2 \CocoaLigature0 /*\
 * Ref: {\field{\*\fldinst{HYPERLINK "http://en.wikipedia.org/wiki/Video_poker"}}{\fldrslt \cf3 http://en.wikipedia.org/wiki/Video_poker}}\
 *      {\field{\*\fldinst{HYPERLINK "http://www.freeslots.com/poker.htm"}}{\fldrslt \cf3 http://www.freeslots.com/poker.htm}}\
 *\
 *\
 * Short Description and Poker rules:\
 *\
 * Video poker is also known as draw poker. \
 * The dealer uses a 52-card deck, which is played fresh after each playerHand. \
 * The player is dealt one five-card poker playerHand. \
 * After the first draw, which is automatic, you may hold any of the cards and draw \
 * again to replace the cards that you haven't chosen to hold. \
 * Your cards are compared to a table of winning combinations. \
 * The object is to get the best possible combination so that you earn the highest \
 * payout on the bet you placed. \
 *\
 * Winning Combinations\
 *  \
 * 1. Jacks or Better: a pair pays out only if the cards in the pair are Jacks, \
 * 	Queens, Kings, or Aces. Lower pairs do not pay out. \
 * 2. Two Pair: two sets of pairs of the same card denomination. \
 * 3. Three of a Kind: three cards of the same denomination. \
 * 4. Straight: five consecutive denomination cards of different suit. \
 * 5. Flush: five non-consecutive denomination cards of the same suit. \
 * 6. Full House: a set of three cards of the same denomination plus \
 * 	a set of two cards of the same denomination. \
 * 7. Four of a kind: four cards of the same denomination. \
 * 8. Straight Flush: five consecutive denomination cards of the same suit. \
 * 9. Royal Flush: five consecutive denomination cards of the same suit, \
 * 	starting from 10 and ending with an ace\
 *\
 */\cf4 \
\
\
\cf2 /* This is the video poker game class.\
 * It uses Decks and Card objects to implement video poker game.\
 * Please do not modify any data fields or defined methods\
 * You may add new data fields and methods\
 * Note: You must implement defined methods\
 */}