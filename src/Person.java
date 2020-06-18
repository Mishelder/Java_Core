import java.io.Serializable;

public class Person implements Serializable {
    private final String name;
    private final boolean male;

    private final int age;
    private final int phone;
    private final String street;

    public static class BuilderPerson{
        private final String name;
        private final boolean male;

        private int age=0;
        private int phone=0;
        private String street="";

        public BuilderPerson(final String name,final boolean male){
            this.name = name;
            this.male = male;
        }

        public BuilderPerson Age(final int _age){
            age =_age;
            return this;
        }

        public BuilderPerson Phone(final int _phone){
            phone = _phone;
            return this;
        }

        public BuilderPerson Street(final String _street){
            street = _street;
            return this;
        }

        public Person build(){
            return new Person (this);
        }
    }

    private Person(BuilderPerson builder){
        this.name = builder.name;
        this.age = builder.age;
        this.phone = builder.phone;
        this.male = builder.male;
        this.street = builder.street;
    }


    public static void main(String[] args) {
        Person person = new BuilderPerson("Mike",true).Phone(234).build();
        System.out.println(person);

    }

    @Override
    public String toString() {
        return "Person {"+name+" "+age+" "+male+" "+phone+" "+street+"}";
    }
}
