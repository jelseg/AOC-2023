public class Card {

    private int value;

    public Card(int v) {
        this.value = v;
    }

    public Card(char c) {
        switch (c) {
            case 'A': value=14; break;
            case 'K': value=13;break;
            case 'Q': value=12;break;
            //case 'J': value=11;break;
            case 'J': value=-1;break;
            case 'T': value=10;break;
            default: value= c - '0';
        }
    }

    public int getValue() {
        return value;
    }


    @Override
    public String toString() {
        return String.format("<%d>",value);
    }
}
