package JavaIO.SecondLab;

import java.io.IOException;

public interface EntityOutput {

    void writePerson(Person person) throws IOException;
    void writePoint(Point point) throws IOException;
}
