package JavaIO.FourthLab;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Task {

    public static void main(String[] args) throws IOException {
        Map<AnagramString, Set<String>> set = new HashMap<>(100000);
        BufferedReader reader = new BufferedReader(new FileReader(new File("words3.txt")));
        BufferedWriter writer1 = new BufferedWriter(new FileWriter(new File("result.txt")));
        BufferedWriter writer2 = new BufferedWriter(new FileWriter(new File("Max.txt")));
        String[] str = reader.lines().toArray(String[]::new);
        AnagramString chars;
        Set<String> set1;
        for (String elem : str) {
            chars = new AnagramString(elem);
            set1 = new HashSet<>(50);
            set1.add(elem);
            if (set.containsKey(chars)) {
                set.get(chars).add(elem);
            } else {
                set.put(chars, set1);
            }
        }
        reader.close();
        String maxString = "";
        String maxSet = "";
        int sizeMax = 0;
        for (Map.Entry<AnagramString, Set<String>> elem : set.entrySet()) {
            if (elem.getValue().size() > sizeMax) {
                sizeMax = elem.getValue().size();
                maxString = elem.getKey().toString();
                maxSet = elem.getValue().toString();
            }
            writer1.write("Key: " + elem.getKey() + "\nValues: " + elem.getValue() + "\n");
        }
        writer2.write("Key: " + maxString + "\nValues: " + maxSet + "\n");
        writer1.close();
        writer2.close();

    }
}
