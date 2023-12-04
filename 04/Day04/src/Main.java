import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        HoopScratchCards hoop = new HoopScratchCards("input/opgave.txt");

        hoop.setNCopies();

        //System.out.println(hoop);
        //System.out.println(hoop.getTotScore());
        System.out.println(hoop.totCopies());
    }


}