import java.util.List;

public abstract class MachinePart {

    int xStart;
    int xEnd;

    public MachinePart(int xStart, int xEnd) {
        this.xStart = xStart;
        this.xEnd = xEnd;
    }

    public abstract int getValue();

    public abstract boolean checkIsPartOrGear(List<MachinePart> allToCheck);

    public int getxStart() {
        return xStart;
    }

    public int getxEnd() {
        return xEnd;
    }
}
