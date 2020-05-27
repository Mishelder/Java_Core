package JavaIO.SecondLab;

import java.io.*;

public class EntityInputStream implements EntityInput {

    private final DataInput in;

    public EntityInputStream(final InputStream in){
        this.in = new DataInputStream(in);
    }

    @Override
    public Person readPerson() throws IOException {
        int age;
        String name ;
        int readPersonAge = in.readInt();
        if(readPersonAge>=0){
            age = readPersonAge;
            boolean hasName = in.readBoolean();
            if(hasName){
                return new Person(in.readUTF(),age);
            }else{
                return new Person(null,age);
            }

        }else{
            return new Person(null,-1);
        }
    }

    @Override
    public Point readPoint() throws IOException {
        byte readPoint;
        String binaryPoint;
        try {
             readPoint = in.readByte();
        }catch (EOFException e){
            return new Point(0,0);
        }
        binaryPoint= String.format("%8s", Integer.toBinaryString(readPoint & 0xFF )).replace(' ', '0');

        return new Point(Integer.parseInt(binaryPoint.substring(0,4),2),
                         Integer.parseInt(binaryPoint.substring(4),2));
    }
}
