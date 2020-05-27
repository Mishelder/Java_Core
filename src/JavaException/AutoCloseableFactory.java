package JavaException;

public interface AutoCloseableFactory {

    public AutoCloseable create() throws Throwable;
}
