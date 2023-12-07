import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class HandsCollection {

    List<Hand> hands;

    public HandsCollection(String fileName) throws FileNotFoundException {

        hands = new LinkedList<>();



        try( BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null){
                hands.add(new Hand(line));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Collections.sort(hands);

    }

    int getTotalWinnings(){
        int tot = 0;
        for (int i=0; i < hands.size(); i++){
            tot += hands.get(i).bed * (i+1);
        }

        return tot;
    }





    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        for (Hand hand : hands){
            builder.append(hand.toString());
            builder.append("\n");
        }

        return builder.toString();
    }
}
