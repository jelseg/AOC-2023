public class Range {
    public long start;
    public long l;

    public Range(long start, long l) {
        this.start = start;
        this.l = l;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",start,l);
    }
}
