package ClassDesignEncapsulationExceptions.Point_3;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] getXYPair() {
        return new int[]{this.x, this.y};
    }

    public double distance(int x, int y) {
        return Math.sqrt(StrictMath.pow(x - this.x, 2) + StrictMath.pow(y - this.y, 2));
    }

    public double distance(Point point) {
        return distance(point.getX(), point.getY());
    }

    public double distance() {
        return distance(0,0);
    }
}
