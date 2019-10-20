package app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        String path = "instances/small/test-001-ternary.xml";
        String nonogram = Utilities.readFileAsString(path);
        String ternaryFile = Utilities.XMLReader(path);
        System.out.println(nonogram);
        System.out.println(ternaryFile);
        Map<String, String[]> variable = new HashMap<String, String[]>();
        variable.put("q0", new String[] { "1" });
        variable.put("q1", new String[] { "0", "1", "2", "3", "4" });
        variable.put("x_0_0", new String[] { "0", "1" });
        String[] diff = Utilities.setDifference(variable.get("q1"), variable.get("x_0_0"));
        System.out.println(Arrays.toString(diff));
    }
}