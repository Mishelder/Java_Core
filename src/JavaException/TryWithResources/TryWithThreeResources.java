package JavaException.TryWithResources;

import JavaException.AutoCloseableFactory;
import JavaException.TryBody;

public class TryWithThreeResources {

    public static void call(AutoCloseableFactory factoryA,
                            AutoCloseableFactory factoryB,AutoCloseableFactory factoryC, TryBody body) throws Throwable{

        try(AutoCloseable a = factoryA.create();
            AutoCloseable b = factoryB.create();
            AutoCloseable c = factoryC.create()){
            body.runBody();
        }

    }
}
