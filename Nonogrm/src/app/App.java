package app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String path = "instances/small/test-001-ternary.xml";
        Scanner s = new Scanner(new File(path));
        String line;
        while (s.hasNext()) {
            line = s.nextLine();
            if (line.contains("<var")) {
                System.out.println(line);
            }
        }
        s.close();
        Map<String, String[]> variable = new HashMap<String, String[]>();
        variable.put("q0", new String[] { "1" });
        variable.put("q1", new String[] { "0", "1", "2", "3", "4" });
        variable.put("x_0_0", new String[] { "0", "1" });
        // String[] diff = Utilities.setDifference(variable.get("q1"), variable.get("x_0_0"));
    }
}