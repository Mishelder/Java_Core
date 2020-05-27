package JavaIO.SecondLab;

public class Person {

    private final String name;
    private final int age;

    public Person(final String name, final int age){
        this.age = age;
        this.name = name;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }
}
