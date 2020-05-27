package JavaException.TryWithResources;

import JavaException.AutoCloseableFactory;
import JavaException.TryBody;

public class TryWithResource {

    public static void call(AutoCloseableFactory factory, TryBody body) throws Throwable{
        try(AutoCloseable resources = factory.create() ) {
            body.runBody();
        }
    }

}
