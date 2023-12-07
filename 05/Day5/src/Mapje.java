import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Mapje {

    String name;
    List<Triple> values;


    public Mapje(String name) {
        this.name = name;

        values = new LinkedList<>();
    }

    public void addMapLine(String lineText){
        String[] splitted = lineText.split(" ");
        addMapLine(Long.parseLong(splitted[0]),Long.parseLong(splitted[1]),Long.parseLong(splitted[2]));
    }
    public void addMapLine(long destStart, long sourceStart, long length){

        values.add(new Triple(destStart,sourceStart,length));

    }

    public long getDest(long sourceNumber){

        for (Triple t: values) {
            if (sourceNumber >= t.getY() && sourceNumber < t.getY() + t.getZ()){
                return t.getX() + (sourceNumber - t.getY());
            }
        }
        return sourceNumber;

    }


    public List<Range> getDest(Range sources) {

        List<Range> result = new LinkedList<>();

        List<Range> currentRanges = new LinkedList<>();
        currentRanges.add(sources);

        for (Triple t : values) {

            List<Range> newSourceRanges = new LinkedList<>();

            for (Range r: currentRanges) {
                List<Range>[] ret = getDestAndSplitRangeOneMapItem(r,t);
                result.addAll(ret[0]);
                newSourceRanges.addAll(ret[1]);
            }

            currentRanges = newSourceRanges;

        }

        result.addAll(currentRanges);

        return result;
    }

    //current range will change
    private List<Range>[] getDestAndSplitRangeOneMapItem(Range source,Triple mapItem) {
        List<Range> result = new LinkedList<>();
        List<Range> currentRanges = new LinkedList<>();

        Triple t = mapItem;
        Range p = source;

        //range source is volledig in één range van map
        if (t.getY() <= p.start && t.getY() + t.getZ() >= p.start + p.l){
            //currentRanges.remove(p);
            result.add(new Range(t.getX() + p.start - t.getY(),p.l));
        }
        //range source starts in range map, but ends outside
        else if (t.getY() <= p.start && t.getY() + t.getZ() > p.start) {

            //currentRanges.remove(p);
            currentRanges.add(new Range(t.getY() + t.getZ(),p.start + p.l - t.getY() - t.getZ()));

            result.add(new Range(t.getX() + p.start - t.getY(),t.getY() + t.getZ() - p.start));

        }
        //range source starts before range map but ends inside
        else if (t.getY() < p.start + p.l && t.getY() + t.getZ() >= p.start + p.l) {

            //currentRanges.remove(p);
            currentRanges.add(new Range(p.start, t.getY()-p.start));

            result.add(new Range(t.getX(),p.start + p.l -t.getY()));

        }
        //range map splits range source
        else if (t.getY() > p.start && t.getY() + t.getZ() < p.start + p.l) {
            //currentRanges.remove(p);
            currentRanges.add(new Range(p.start,t.getY()-p.start));
            currentRanges.add(new Range(t.getY()+t.getZ(),p.start + p.l - t.getZ()-t.getY()));

            result.add(new Range(t.getX(),t.getZ()));
        }
        else currentRanges.add(p);

        return new List[]{result, currentRanges};

    }



    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder(name);
        builder.append("\n");

        for (Triple t : values) {
            builder.append((t.toString()));
            builder.append("\n");
        }

        return builder.toString();

    }
}
