import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HoopScratchCards {

    List<ScratchCard> cards;

    public HoopScratchCards(String fileName) throws IOException {

        //cards = new LinkedList<>();
        cards = new ArrayList<>();

        try( BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null) {
                cards.add(new ScratchCard(line));
            }
        }

    }


    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        for (ScratchCard card: cards) {
            builder.append(card);
            builder.append("\n");
        }

        return builder.toString();
    }

    public int getTotScore(){

        int tot = 0;

        for (ScratchCard card : cards) {
            int n = card.nWon();
            if (n > 0) {
                tot += 1 << (n-1); //note for future self << is shift operator -> for powers of two
            }
        }

        return tot;

    }

    public void setNCopies() {

        int l = cards.size();

        for (int i=0; i < l; i++) {
            int nCopies = cards.get(i).getnCopies();
            int nWon = cards.get(i).nWon();

            for (int j=1; j <= nWon; j++) {
                int I = i + j;
                if (I < l){
                    cards.get(I).addCopies(nCopies);
                }
            }

        }

    }

    public int totCopies() {

        int tot = 0;

        for (ScratchCard card:cards){
            tot+= card.getnCopies();
        }
        return tot;

    }


}
