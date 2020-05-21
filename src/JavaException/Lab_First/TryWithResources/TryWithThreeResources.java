package JavaException.Lab_First.TryWithResources;

import JavaException.Lab_First.AutoCloseableFactory;
import JavaException.Lab_First.TryBody;

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
