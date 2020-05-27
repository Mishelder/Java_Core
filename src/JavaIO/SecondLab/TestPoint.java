package JavaIO.SecondLab;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestPoint {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        EntityOutput out = new EntityOutputStream(buff);
        for(int i=0;i<10;i++){
            out.writePoint(new Point(i,i));
        }
        byte[] rawBytes = buff.toByteArray();

        EntityInput in = new EntityInputStream(new ByteArrayInputStream(rawBytes));
        for(int i=0;i<10;i++){
            Point point = in.readPoint();
            if(point.getX()!=i){
                throw new AssertionError();
            }
            if(point.getY()!= i){
                throw new AssertionError();
            }
            System.out.println("Point_"+i);
            System.out.println("X: "+point.getX());
            System.out.println("Y: "+point.getY());
            System.out.println();
        }
    }
}
