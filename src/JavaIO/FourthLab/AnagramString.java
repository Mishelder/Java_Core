package JavaIO.FourthLab;

import java.util.Arrays;

public class AnagramString {
    private final String str;

    public AnagramString(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        AnagramString myString = (AnagramString) that;
        char[] str = myString.str.toCharArray();
        char[] str1 = this.str.toCharArray();
        Arrays.sort(str);
        Arrays.sort(str1);
        return Arrays.equals(str1, str);
    }

    @Override
    public String toString() {
        return "MyString{" +
                "str='" + str + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (Character elem : str.toCharArray())
            sum += elem;
        return sum;
    }
}
