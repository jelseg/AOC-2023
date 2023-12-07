public class Triple {
    long x;
    long y;
    long z;

    public Triple(long x, long y, long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public long getZ() {
        return z;
    }

    @Override
    public String toString(){
        return String.format("(%d,%d,%d)",x,y,z);
    }
}
