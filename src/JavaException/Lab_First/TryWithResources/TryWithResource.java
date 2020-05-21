package JavaException.Lab_First.TryWithResources;

import JavaException.Lab_First.AutoCloseableFactory;
import JavaException.Lab_First.TryBody;

public class TryWithResource {

    public static void call(AutoCloseableFactory factory, TryBody body) throws Throwable{
        try(AutoCloseable resources = factory.create() ) {
            body.runBody();
        }
    }

}
