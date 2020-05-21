package JavaException.Lab_First.TryWithoutResources;

import JavaException.Lab_First.AutoCloseableFactory;
import JavaException.Lab_First.TryBody;

public class TryWithoutTwoResources {

    public static void call(AutoCloseableFactory factoryA, AutoCloseableFactory factoryB, TryBody body) throws Throwable{
        AutoCloseable resourceA = factoryA.create();

        AutoCloseable resourceB ;
        try{
            resourceB = factoryB.create();
        }catch (Throwable resourceBEx){
            try{
                resourceA.close();
            }catch(Throwable closeAEx){
                resourceBEx.addSuppressed(closeAEx);
            }
            throw resourceBEx;
        }
        //tryBody
        try{
            body.runBody();
        }catch (Throwable bodyEx){
            //close B
            try{
                resourceB.close();
            }catch (Throwable closeBEx){
                bodyEx.addSuppressed(closeBEx);
                //close A
                try{
                   resourceA.close();
                }catch (Throwable closeAEx){
                    bodyEx.addSuppressed(closeAEx);
                }
            }
            //close A
            try{
                resourceA.close();
            }catch (Throwable closeAEx){
                bodyEx.addSuppressed(closeAEx);
            }
            throw bodyEx;
        }

        //close B
        try{
            resourceB.close();
        }catch (Throwable closeBEx){
            try{
              resourceA.close();
            }catch (Throwable closeAEx){
                closeBEx.addSuppressed(closeAEx);
            }
            throw closeBEx;
        }

        //close A
        resourceA.close();
    }
}
