package JavaException.OneResources.TryWithoutResources;

import JavaException.AutoCloseableFactory;
import JavaException.TryBody;

public class TryWithoutResource {

    public static void call(AutoCloseableFactory factory, TryBody body) throws Throwable{
        //create A
        AutoCloseable resource = factory.create();

        //run body
        try{
            body.runBody();
        }catch (Throwable bodyEx){
            try{
                resource.close();
            }catch(Throwable closeEx){
                bodyEx.addSuppressed(closeEx);
            }
            throw bodyEx;
        }

        resource.close();

    }
}
