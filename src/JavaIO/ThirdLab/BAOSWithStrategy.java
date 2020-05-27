package JavaIO.ThirdLab;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BAOSWithStrategy extends OutputStream {

    private final static int DEFAULT_START_SIZE = 16;
    private final static AllocateStrategy DEFAULT_ALLOCATE_STRATEGY = new DoubleAllocateStrategy();

    private final AllocateStrategy strategy;
    private final List<byte[]> bufferList = new ArrayList<>(16);
    private int count =0;

    public BAOSWithStrategy(){
        this(DEFAULT_START_SIZE,DEFAULT_ALLOCATE_STRATEGY);
    }

    public BAOSWithStrategy(final int startSize){
        this(startSize,DEFAULT_ALLOCATE_STRATEGY);
    }

    public BAOSWithStrategy(final AllocateStrategy strategy){
        this(DEFAULT_START_SIZE,strategy);
    }

    public BAOSWithStrategy(final int startSize,final AllocateStrategy strategy ){
        bufferList.add(new byte[startSize]);
        this.strategy = strategy;
    }

    @Override
    public void write(int b) throws IOException {
        byte[] lastBuff = bufferList.get(bufferList.size()-1);
        if(count == lastBuff.length){
            int newSize = strategy.nextAfter(lastBuff.length);
            byte[] newLastBuff = new byte[newSize];
            bufferList.add(newLastBuff);
            count =0;
            lastBuff = newLastBuff;
        }
        lastBuff[count++] = (byte) b;
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b,0,b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {

        byte[] lastBuff = bufferList.get(bufferList.size()-1);
        int possibleByteToWrite = lastBuff.length - count;
        if (possibleByteToWrite == 0 || possibleByteToWrite < b.length) {
            int newLength = lastBuff.length;
            while (b.length >= newLength) {
                newLength = strategy.nextAfter(newLength);
            }
            if(possibleByteToWrite>0){
                System.arraycopy(b,off,lastBuff,count,possibleByteToWrite);
                off+=possibleByteToWrite;
                len-=possibleByteToWrite;
            }
                count = 0;

            byte[] newLastBuff = new byte[newLength];
            bufferList.add(newLastBuff);

            lastBuff = newLastBuff;
        }
        System.arraycopy(b,off,lastBuff,count,len);
        count+=len;
    }

    public void writeTo(OutputStream out) throws IOException{
        for (byte[] bytes : bufferList) {
            out.write(bytes);
        }
    }

    public byte[] toByteArray(){
        int sizeOfByteArray=0;
        for (byte[] bytes : bufferList) {
            sizeOfByteArray += bytes.length;
        }
        byte[] byteArray = new byte[sizeOfByteArray];
        for(int i=0,j=0;i<bufferList.size();i++){
            System.arraycopy(bufferList.get(i),0,byteArray,j,bufferList.get(i).length);
            j+=bufferList.get(i).length;
        }
        return byteArray;
    }

    @Override
    public void flush() throws IOException {
        super.flush();
    }

    @Override
    public void close() throws IOException {
        super.close();
    }


}
