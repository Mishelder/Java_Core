package JavaIO.SecondLab;

public class Point {

    private final int x;
    private final int y;

    public Point (final int x, final int y ){
        if(x<0||15<x){
            throw new IllegalArgumentException();
        }else if(y<0||15<y){
            throw new IllegalArgumentException();
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
