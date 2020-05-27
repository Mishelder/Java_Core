package JavaIO.SecondLab;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TestPerson {

    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        EntityOutput out = new EntityOutputStream(buff);
        for(int i=0;i<10;i++){
            out.writePerson(new Person("Mike_"+i,i));
        }
        byte[] rawBytes = buff.toByteArray();

        EntityInput in = new EntityInputStream(new ByteArrayInputStream(rawBytes));
        for(int i=0;i<10;i++){
            Person person = in.readPerson();
            System.out.println("Person_"+i);
            System.out.println("Age: "+person.getAge());
            System.out.println("Name: "+person.getName());
            System.out.println();
            if(!person.getName().equals("Mike_"+i)){
                throw new AssertionError();
            }
            if(person.getAge()!= i){
                throw new AssertionError();
            }

        }
    }
}
