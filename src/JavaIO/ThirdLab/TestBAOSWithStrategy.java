package JavaIO.ThirdLab;

import java.io.IOException;

public class TestBAOSWithStrategy {

    public static void main(String[] args) throws IOException {
        BAOSWithStrategy baos = new BAOSWithStrategy(16);
        byte[] a = {1,2,2,3,45,65,12};
        byte[] b = {2,3,3,9,8,7};
        byte[] c = {3,4,5,4};
        baos.write(a);
        baos.write(b);
        baos.write(c);
        byte[] result = baos.toByteArray();
        print(result);
    }

    static void print(byte[] result){
        for(byte elem:result){
            System.out.println(elem+" ");
        }
    }
}
