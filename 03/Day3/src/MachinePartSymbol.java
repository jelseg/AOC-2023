import java.util.LinkedList;
import java.util.List;

public class MachinePartSymbol extends MachinePart{

    char symbol;

    List<MachinePartNumbers> adjNumbers;

    public MachinePartSymbol(int x, char symbol) {
        super(x, x);
        this.symbol = symbol;
        adjNumbers = new LinkedList<>();
    }

    @Override
    public int getValue() {

        if (adjNumbers.size()==2) {
            int tot = 1;

            for (MachinePartNumbers p : adjNumbers) {
                tot *= p.value;
            }
            return tot;
        }

        return 0;
    }

    @Override
    public boolean checkIsPartOrGear(List<MachinePart> allToCheck) {

        if (symbol != '*') {
            return false;
        }

        adjNumbers = new LinkedList<>();

        for (MachinePart mp : allToCheck){

            if (mp instanceof MachinePartNumbers){
                if (xEnd >= mp.getxStart()-1 && xEnd <= mp.getxEnd()+1) {
                    adjNumbers.add((MachinePartNumbers) mp);
                }
            }
        }

        if (adjNumbers.size()!=2){
            adjNumbers = new LinkedList<>();
            return false;
        }
        return true;

    }
}
