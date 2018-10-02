import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Sample {
    public static void printArray(String name, ArrayList arr) {
        if (arr.isEmpty()) {
            System.out.println(name + " is empty");
        } else {
            for (DataGen<?> t : (ArrayList<DataGen<?>>)arr) {
                System.out.println(t);
            }
        }
        System.out.println("---------------------------------------------------");
    }
    public static void main(String args[]) throws FileNotFoundException {
        Scanner in = new Scanner(new File("text.txt"));
        Pattern hex = Pattern.compile("[+-]?0[Xx][0-9A-Fa-f]+");
        Pattern dec = Pattern.compile("^[+-]?\\d+$");
        Pattern exp = Pattern.compile("[+-]?[1-9]\\.[0-9]+[eE][+-]?[0-9]+");
        Pattern sail = Pattern.compile("[+-]?[0-9]+\\.[0-9]+"); // AWOLNATION :D
        ArrayList<DataGen<Integer>> decArray = new ArrayList<DataGen<Integer>>();
        ArrayList<DataGen<Integer>> hexArray = new ArrayList<DataGen<Integer>>();
        ArrayList<DataGen<Float>> expArray = new ArrayList<DataGen<Float>>();
        ArrayList<DataGen<Float>> sailArray = new ArrayList<DataGen<Float>>();
        ArrayList<DataGen<String>> strArray = new ArrayList<DataGen<String>>();

        Comparator<DataGen<Integer>> intComparator = (a, b) -> b.getData() - a.getData();

        Comparator<DataGen<Float>> floatComparator = (a, b) -> (int)Math.signum(a.getData() - b.getData());

        Comparator<DataGen<String>> stringComparator = (a, b) -> b.getData().compareTo(a.getData());

        int lineNumber = 0;
        while (in.hasNextLine()) {
            StringTokenizer s = new StringTokenizer(in.nextLine(), "\t\n ");
            while (s.hasMoreTokens()) {
                String word = s.nextToken();
                if (dec.matcher(word).matches()) {
                    decArray.add(new DataGen(Integer.parseInt(word), lineNumber));
                } else if (hex.matcher(word).matches()) {
                    hexArray.add(new DataGen(Integer.decode(word), lineNumber));
                } else if (sail.matcher(word).matches()) {
                    expArray.add(new DataGen(Float.parseFloat(word), lineNumber));
                } else if (exp.matcher(word).matches()) {
                    sailArray.add(new DataGen(Float.parseFloat(word),lineNumber));
                } else {
                    strArray.add(new DataGen(word, lineNumber));
                }
            }
            lineNumber++;
        }
        long n = Double.valueOf("7.6E+7").longValue();
        System.out.println(n+"==========");
       String e = "7.6e+7";
        double b = Double.parseDouble(e) + 1;
        System.out.println(b+"==================");
        Collections.sort(decArray, intComparator);
        Collections.sort(hexArray, intComparator);
        Collections.sort(expArray, floatComparator);
        Collections.sort(sailArray, floatComparator);
        Collections.sort(strArray, stringComparator);
        System.out.println("\nPrint all :");
        System.out.println("\ndec :");
        printArray("decArray",decArray);
        System.out.println("\nhex :");
        printArray("hexArray",hexArray);
        System.out.println("\nexp :");
        printArray("expArray",expArray);
        System.out.println("\ndouble :");
        printArray("sailArray",sailArray);
        System.out.println("\nstr :");
        printArray("strArray",strArray);
    }
}
// for test
// 123 adfas 0.123 gbfn 0x12F 1.23e4
// -123 bwe4 -0.123 -0x123A -1.23e1
// -12.3 23g 0xF23A 300 -11.123 2222 -1.23e+5
// -9999.1 zzzz 0xFfff 911.123