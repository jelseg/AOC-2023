import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        HandsCollection hc = new HandsCollection("input/opgave.txt");

        //System.out.println(hc);

        System.out.println(hc.getTotalWinnings());
    }
}