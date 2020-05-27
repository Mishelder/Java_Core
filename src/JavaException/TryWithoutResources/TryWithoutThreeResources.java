package JavaException.TryWithoutResources;

import JavaException.AutoCloseableFactory;
import JavaException.TryBody;

public class TryWithoutThreeResources {

    public static void call(AutoCloseableFactory factoryA,
                            AutoCloseableFactory factoryB,
                            AutoCloseableFactory factoryC,
                            TryBody body) throws Throwable{
        //create A
        AutoCloseable resourceA = factoryA.create();
        AutoCloseable resourceB;
        AutoCloseable resourceC;

        //create B
        try{
            resourceB = factoryB.create();
        }catch (Throwable createBEx){
            try{
                resourceA.close();
            }catch (Throwable closeAEx){
                createBEx.addSuppressed(closeAEx);
            }
            throw createBEx;
        }

        //create C
        try{
            resourceC = factoryC.create();
        }catch (Throwable createCEx){
            try{
                resourceB.close();
            }catch (Throwable closeBEx){
                createCEx.addSuppressed(closeBEx);
                try{
                    resourceA.close();
                }catch (Throwable closeAEx){
                    createCEx.addSuppressed(closeAEx);
                }
            }
            throw createCEx;
        }

        //tryBody
        try{
            body.runBody();
        }catch(Throwable bodyEx){
            //close C
            try{
                resourceC.close();
            }catch (Throwable closeCEx){
                bodyEx.addSuppressed(closeCEx);
                try{
                    resourceB.close();
                }catch (Throwable closeBEx){
                    bodyEx.addSuppressed(closeBEx);
                    try{
                        resourceA.close();
                    }catch (Throwable closeAEx){
                        bodyEx.addSuppressed(closeAEx);
                    }
                }
            }
            //close B
            try{
                resourceB.close();
            }catch (Throwable closeBEx){
                bodyEx.addSuppressed(closeBEx);
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

        //close C
        try{
            resourceC.close();
        }catch (Throwable closeCEx){
            try{
                resourceB.close();
            }catch (Throwable closeBEx){
                closeCEx.addSuppressed(closeBEx);
                try{
                    resourceA.close();
                }catch (Throwable closeAEx){
                    closeCEx.addSuppressed(closeAEx);
                }
            }
            throw closeCEx;
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
        //close A;
        resourceA.close();

    }
}
