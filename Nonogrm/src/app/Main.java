package app;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "Nonogrm/instances/small/test-001-ternary.xml";
        String outputPath = "Nonogrm/output/minisatFile";
        String output = "";
        String negation = "";
        XCSP3 nonogram = new XCSP3(path);
        Collection<VarInteger> xcsp3Var = nonogram.getMapVar();
        Set<VarInteger> cspVarSet = new HashSet<VarInteger>(xcsp3Var);
        Set<Extension> extensionsSet = nonogram.getExtensionSet();

        Map<VarInteger, List<String>> satTerm = new HashMap<VarInteger, List<String>>();
        Map<Integer, Integer> hel = new HashMap<Integer, Integer>();
        hel.put(1, 2);
        hel.put(3, 4);
        int j = 0;
        for (VarInteger x : cspVarSet) {
            int lenght = x.getValue().length;
            List<String> sat = new ArrayList<>();
            for (int i = 0; i < lenght; i++) {
                sat.add(i, "t" + j + "" + i);
                negation += "t" + j + "" + i + " -t" + j + "" + i + " 0\n";
                output += "t" + j + "" + i + " ";
            }
            output += "0\n";
            satTerm.put(x, sat);
            j++;
        }
        // System.out.println(satTerm.toString());
        output+= negation;
        System.out.println(output);
        // System.out.println(negation);

        // write to the file
        PrintWriter satFile = new PrintWriter(outputPath, "UTF-8");
        satFile.println("c this is nonogram instance");
        satFile.println("p cnf 680");
        satFile.print(output.trim());
        satFile.close();

    }

}