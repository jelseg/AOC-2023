import java.util.List;
import java.util.stream.Stream;

public class MachinePartNumbers extends MachinePart{

    boolean isPartNumber = false;
    int value;

    public MachinePartNumbers(int xStart, int xEnd, int value) {
        super(xStart, xEnd);
        this.value = value;
    }

    @Override
    public int getValue() {
        if (isPartNumber){
            return value;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean checkIsPartOrGear(List<MachinePart> allToCheck) {


        for (MachinePart p : allToCheck) {

            if (p instanceof MachinePartSymbol){
                if (p.getxEnd() >= xStart-1 && p.getxEnd() <= xEnd+1) {
                    isPartNumber = true;
                    return true;
                }
            }

        }

        isPartNumber = false;
        return false;

    }
}
