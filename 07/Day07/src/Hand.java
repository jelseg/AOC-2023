public class Hand implements Comparable<Hand> {

    Card[] cards;
    int bed;

    HandType ht;


    public Hand(String s){

        String[] splitted = s.split(" ");

        String s1 = splitted[0];

        cards = new Card[s1.length()];
        for(int i =0; i < s1.length(); i++) {
            cards[i] = new Card(s1.charAt(i));
        }

        bed = Integer.parseInt(splitted[1]);

        ht = HandType.UNSET;

    }

    @Override
    public int compareTo(Hand o) {

        if (getHt().strength < o.getHt().strength) {
            return -1;
        }

        if (getHt().strength > o.getHt().strength){
            return 1;
        }

        for (int i=0; i < cards.length; i++){
            if(cards[i].getValue() < o.cards[i].getValue()) {
                return -1;
            }
            if(cards[i].getValue() > o.cards[i].getValue()) {
                return 1;
            }
        }
        return 0;
    }

    enum HandType {
        UNSET(-1),HIGH(0),PAIR(1),DOUBLE(2),TOK(3),FULL(4),FOUR(5),FIVE(6);
        int strength;
        HandType(int str){
            this.strength = str;
        }

        HandType addJoker(){
            //assumes Fiv card hand
            switch (this) {
                case HIGH: return PAIR;
                case PAIR: return TOK;
                case DOUBLE: return FULL;
                case TOK: return FOUR;
                case FOUR: return FIVE;
            }
            return this;
        }
    }

    private HandType setHandType() {

        int[] nFound = new int[15];

        for (Card c : cards) {
            nFound[c.getValue()]++;
        }

        ht = HandType.HIGH;

        for(int n : nFound) {
            switch(n) {
                case 5: ht = HandType.FIVE; break;
                case 4: ht = HandType.FOUR; break;

                case 3: if(ht == HandType.PAIR) {
                    ht = HandType.FULL;
                }
                else {
                    ht = HandType.TOK;
                } break;

                case 2: if (ht == HandType.TOK) {
                    ht = HandType.FULL;
                } else if ( ht == HandType.PAIR) {
                    ht = HandType.DOUBLE;
                } else {
                    ht = HandType.PAIR;
                }break;
            }
        }

        return ht;
    }

    private HandType setHandType2() {

        int[] nFound = new int[15];
        int nJokers = 0;

        for (Card c : cards) {
            if(c.getValue() != -1)
                nFound[c.getValue()]++;
            else {
                nJokers++;
            }
        }

        ht = HandType.HIGH;

        for(int n : nFound) {
            switch(n) {
                case 5: ht = HandType.FIVE; break;
                case 4: ht = HandType.FOUR; break;

                case 3: if(ht == HandType.PAIR) {
                    ht = HandType.FULL;
                }
                else {
                    ht = HandType.TOK;
                } break;

                case 2: if (ht == HandType.TOK) {
                    ht = HandType.FULL;
                } else if ( ht == HandType.PAIR) {
                    ht = HandType.DOUBLE;
                } else {
                    ht = HandType.PAIR;
                }break;
            }
        }

        for (int i = 0; i < nJokers; i++) {
            ht = ht.addJoker();
        }


        return ht;
    }

    public HandType getHt(){
        if(ht == HandType.UNSET) {
            setHandType2();
        }

        return ht;
    }


    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Card c : cards) {
            builder.append(c.toString());
        }
        builder.append(" " + bed);
        builder.append(" ");
        builder.append(getHt());

        return builder.toString();
    }

}
