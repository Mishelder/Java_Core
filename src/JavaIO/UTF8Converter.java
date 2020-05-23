package JavaIO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UTF8Converter {

    public static int[] convertUTF8toCodePointString(byte[] utf8) throws IllegalArgumentException {
        List<Integer> arr = new ArrayList<Integer>();
        int posIn = 0;
        int byteInSequence ;
        String codePoint;
        for(int index =0;index<utf8.length;index++){
            codePoint = String.format("%8s", Integer.toBinaryString(utf8[index] & 0xFF )).replace(' ', '0');
            posIn=0;
            byteInSequence=0;
            while(true){
                if(codePoint.charAt(0)=='0') {
                    arr.add(Integer.parseInt(codePoint,2));
                    break;
                }else{
                    if (codePoint.charAt(posIn) == '1') {
                        byteInSequence++;
                        posIn++;
                    } else {
                        String result ="";

                        for(int b = 0; b<byteInSequence;b++){
                            if(b==0){
                                result+=codePoint.substring(byteInSequence+1);
                            }else{
                                codePoint = String.format("%8s", Integer.toBinaryString(utf8[index+b] & 0xFF )).replace(' ', '0');
                                result+=codePoint.substring(2);
                            }

                        }
                        index+=byteInSequence-1;
                        arr.add(Integer.parseInt(result,2));
                        break;
                    }

                }
            }

        }
        int[] a = new int[arr.size()];
        for(int i=0;i<arr.size();i++){
            a[i] = arr.get(i).intValue();
        }
        return a;
    }




    public static int[] convertUTF8toCodePoint(byte[] utf8) {
        int[] result = new int[utf8.length];
        int posIn = 0;
        int posOut = 0;
        int size=0;
        while (posIn < utf8.length) {
            byte octet0 = utf8[posIn++];
            String binary="";
            if ((octet0 & 0b1000_0000) == 0) {
                result[posOut++] = octet0;
                size++;
                continue;
            } else if((octet0 & 0b0010_0000) == 0) {
                binary =String.format("%8s", Integer.toBinaryString((octet0+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                result[posOut++] = Integer.parseInt(binary,2) ;
                size++;
                continue;
            }else if((octet0 & 0b0001_0000) == 0) {
                binary =String.format("%8s", Integer.toBinaryString((octet0+127-64) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                result[posOut++] = Integer.parseInt(binary,2) ;
                size++;
                continue;
            }else if((octet0 & 0b0000_1000) == 0) {
                binary =String.format("%8s", Integer.toBinaryString((octet0+127-64-16) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                result[posOut++] = Integer.parseInt(binary,2) ;
                size++;
                continue;
            }else if((octet0 & 0b0000_0100) == 0) {
                binary =String.format("%8s", Integer.toBinaryString((octet0+127-64-8) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                result[posOut++] = Integer.parseInt(binary,2) ;
                size++;
                continue;
            }else if((octet0 & 0b0000_0010) == 0) {
                binary =String.format("%8s", Integer.toBinaryString((octet0+127-64-4) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                binary +=String.format("%8s", Integer.toBinaryString((utf8[posIn++]+127) & 0xFF )).replace(' ', '0');
                result[posOut++] = Integer.parseInt(binary,2) ;
                size++;
                continue;
            }
        }
        return Arrays.copyOfRange(result, 0, size);
    }
}