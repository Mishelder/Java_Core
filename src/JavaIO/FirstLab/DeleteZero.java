package JavaIO.FirstLab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DeleteZero {

    public static void filter(InputStream src , OutputStream dst) throws IOException {
        while(true){
            int value = src.read();
            if(value>0) {
                System.out.println(value);
                dst.write(value);
            }else if(value==-1) {
                break;
            }
        }
    }

    public static void filter(InputStream src, OutputStream dst,int buffer) throws IOException{
        byte[] buff = new byte[buffer];
        while(true){
            int count = src.read();

            if(count>0){

                   System.out.println(count);

                System.out.println();
            }else if(count ==-1){
                break;
            }
        }
    }
}
