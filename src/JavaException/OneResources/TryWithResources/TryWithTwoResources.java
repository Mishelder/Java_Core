package JavaException.OneResources.TryWithResources;

import JavaException.AutoCloseableFactory;
import JavaException.TryBody;

public class TryWithTwoResources {

    public static void call(AutoCloseableFactory factoryA,
                            AutoCloseableFactory factoryB, TryBody body) throws Throwable{

        try(AutoCloseable a = factoryA.create();
            AutoCloseable b = factoryB.create()){
            body.runBody();
        }

    }
}
