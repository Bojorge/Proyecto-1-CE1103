public class Borde {

    private int x, y;
    private boolean horizontal;

    Borde() {
        x = y = -1;
        horizontal = false;
    }

    Borde(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return ((horizontal ? "H " : "V ") + x + " " + y);
    }

}
