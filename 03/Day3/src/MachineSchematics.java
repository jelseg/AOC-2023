import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MachineSchematics {

    List<List<MachinePart>> parts;


    public MachineSchematics(String filename) throws IOException {

        parts = new ArrayList<>();

        try( BufferedReader reader = new BufferedReader(new FileReader(filename))){

            String line;
            while ((line = reader.readLine()) != null) {

                List<MachinePart> partsLine = new ArrayList<>();

                String numberString = "";
                int numberstringStart = -1;

                for (int i=0; i < line.length(); i++){

                    char c = line.charAt(i);

                    if(Character.isDigit(c)) {
                        if (numberString.isEmpty()) {
                            numberstringStart = i;
                        }
                        numberString += c;
                    } else {
                        if (!numberString.isEmpty()) {

                            int value = Integer.parseInt(numberString);
                            numberString = "";

                            partsLine.add(new MachinePartNumbers(numberstringStart, i - 1, value));
                        }
                        if (c!='.'){
                            partsLine.add(new MachinePartSymbol(i,c));
                        }
                    }
                }

                if (!numberString.isEmpty()) {

                    int value = Integer.parseInt(numberString);
                    numberString = "";

                    partsLine.add(new MachinePartNumbers(numberstringStart, line.length() - 1, value));
                }

                parts.add(partsLine);

            }

        };

    }


    //just for testing
    public int getPartsCount(){
        int n = 0;
        for (List<MachinePart> partsLine : parts){
            n += partsLine.size();
        }
        return n;
    }

    public void checkPartNumbers() {

        List<MachinePart> prevLine = new ArrayList<>();
        List<MachinePart> thisLine = new ArrayList<>();

        for (List<MachinePart> nextLine : parts) {
            checkPartsLine(prevLine,thisLine,nextLine);
            prevLine = thisLine;
            thisLine = nextLine;
        }
        checkPartsLine(prevLine,thisLine, new ArrayList<>());
    }

    private void checkPartsLine(List<MachinePart> prevLine,List<MachinePart> thisLine,List<MachinePart> nextLine ) {
        List<MachinePart> threeLines = new LinkedList<>();
        threeLines.addAll(prevLine);
        threeLines.addAll(thisLine);
        threeLines.addAll(nextLine);

        for (MachinePart p:thisLine) {
            p.checkIsPartOrGear(threeLines);
        }

    }

    public int getSumPartNumbers() {
        int tot = 0;
        for( List<MachinePart> ml : parts) {
            for (MachinePart p: ml) {
                if (p instanceof MachinePartNumbers) {
                    tot += p.getValue();
                }
            }
        }
        return tot;
    }

    public int getSumGearNumbers() {
        int tot = 0;
        for( List<MachinePart> ml : parts) {
            for (MachinePart p: ml) {
                if (p instanceof MachinePartSymbol) {
                    tot += p.getValue();
                }
            }
        }
        return tot;
    }

}
