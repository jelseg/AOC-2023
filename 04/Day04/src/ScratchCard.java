import java.util.LinkedList;
import java.util.List;

public class ScratchCard {

    private List<Integer> winningNumbers;
    private List<Integer> myNumbers;

    private int cardNumber;

    private int nCopies;


    public ScratchCard(String cardString) {
        String[] splitOne = cardString.split(":");

        String[] splitTwo = splitOne[0].split(" ");
        cardNumber = Integer.parseInt(splitTwo[splitTwo.length-1]);

        splitTwo = splitOne[1].split("\\|");

        winningNumbers = stringToIntegerList(splitTwo[0]);
        myNumbers = stringToIntegerList(splitTwo[1]);

        nCopies = 1;

    }

    private List<Integer> stringToIntegerList(String s) {
        List<Integer> result = new LinkedList<>();

        s = s.trim();
        s = s.replaceAll("^ +| +$|( )+", "$1");
        String[] splitted = s.split(" ");
        for (String split : splitted) {
            result.add(Integer.parseInt(split));
        }

        return result;
    }

    public int nWon(){
        int n = 0;
        for (int wn: winningNumbers){
            for (int mn : myNumbers) {
                if (wn == mn) {
                    n++;
                }
            }
        }
        return n;
    }


    @Override
    public String toString(){
        return String.format("%d: %s | %s = %d, %d",cardNumber, winningNumbers.toString(),myNumbers.toString(), nWon(), nCopies);
    }

    public void addCopies(int n) {
        nCopies += n;
    }
    public int getnCopies() {
        return nCopies;
    }

}
