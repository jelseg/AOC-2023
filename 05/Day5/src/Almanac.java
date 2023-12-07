import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Almanac {

    List<Mapje> mapjes;
    Long[] seeeds;

    List<Range> seedRanges;

    public Almanac(String fileName){

        mapjes = new LinkedList<>();

        try( BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;

            line = reader.readLine();
            String numbersLine = line.replace("seeds: ","");
            String[] numbersStr = numbersLine.split(" ");
            seeeds = new Long[numbersStr.length];
            for (int i = 0; i < numbersStr.length; i++){
                seeeds[i] = Long.parseLong(numbersStr[i]);
            }

            Mapje m = null;

            while ((line = reader.readLine()) != null) {
                if(line.equals("")){
                    if(m != null) {
                        mapjes.add(m);
                    }
                    line = reader.readLine();
                    m = new Mapje(line);
                }
                else {
                    m.addMapLine(line);
                }

            }
            mapjes.add(m);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        seedRanges = new LinkedList<>();
        for(int i = 0; i < seeeds.length; i+=2) {
            seedRanges.add(new Range(seeeds[i],seeeds[i+1]));
        }


    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        for (Long i:seeeds) {
            builder.append(i);
            builder.append(" ");
        }

        builder.append("\n");
        for (Mapje m : mapjes){
            builder.append(m.toString());
            builder.append("\n");
        }
        return builder.toString();

    }


    public long getLocationNumber(long seedNumber) {
        long currentNumber = seedNumber;
        for (Mapje m : mapjes) {
            currentNumber = m.getDest(currentNumber);
            //System.out.println(currentNumber);
        }
        return currentNumber;
    }

    public long getClosestLocation(){

        long min = 1000000000;

        for (Long s:seeeds) {
            long loc = getLocationNumber(s);
            min = Math.min(min,loc);
        }
        return min;
    }

    public long getClosestLocation2(){

        List<Range> currentRanges = seedRanges;

        for (Mapje m: mapjes) {

            List<Range> nextRanges = new LinkedList<>();

            for (Range r : currentRanges) {
                List<Range> toAdd = m.getDest(r);
                nextRanges.addAll(toAdd);
            }
            //System.out.println(nextRanges);
            currentRanges = nextRanges;

        }

        long min = 1000000000;

        for (Range r : currentRanges) {
            min = Long.min(min,r.start);
        }

        return min;

    }


}
