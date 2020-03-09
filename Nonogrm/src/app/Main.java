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
        // path of the input and output
        String path = "instances/small/test-001-ternary.xml";
        String outputPath = "output/minisatFile";
        // variable containing the output String
        String output = "";
        // variable containing the XOR in order to select only one option per box
        String negation = "";
        String noSupports = "";
        int lineCount = 0;
        // Just a holder
        List<String> l = new ArrayList<>();
        // Import the monogram 27 to 35
        XCSP3 nonogram = new XCSP3(path);
        Collection<VarInteger> xcsp3Var = nonogram.getMapVar();
        Set<VarInteger> cspVarSet = new HashSet<VarInteger>(xcsp3Var);
        Set<Extension> extensionsSet = nonogram.getExtensionSet();
        int n = extensionsSet.size();
        List<Extension> extensionList = new ArrayList<Extension>(n);
        for (Extension x : extensionsSet)
            extensionList.add(x);

        // this is the Map from the id of each variable to their
        Map<String, List<String>> satTerm = new HashMap<String, List<String>>();
        Map<Integer, Integer> hel = new HashMap<Integer, Integer>();
        hel.put(1, 1);

        // this variable is used to name the sat variables
        int satVariable = 1;
        // get the sat variables
        for (VarInteger x : cspVarSet) {
            int lenght = x.getValue().length;
            List<String> sat = new ArrayList<>();
            for (int i = 0; i < lenght; i++) {
                // sat.add(i, "t" + j + "" + i);
                sat.add("" + satVariable);
                // negation += "t" + j + "" + i + " -t" + j + "" + i + " 0\n";
                // output += "t" + j + "" + i + " ";
                output += "" + satVariable + " ";
                satVariable++;
            }
            output += "0\n";
            lineCount++;
            satTerm.put(x.id, sat);
        }
        satTerm.forEach((k, v) -> {
            // List<String> v = satTerm.get("q1");
            for (int i = 0; i < v.size(); i++) {
                for (int p = i + 1; p < v.size(); p++) {
                    // negation += "-" + v.get(i) + " -" + v.get(p);
                    l.add("-" + v.get(i) + " -" + v.get(p) + "\n");
                }

            }
        });
        for (int i = 0; i < l.size(); i++) {
            negation += l.get(i);
            lineCount++;
        }
        // System.out.println(satTerm.toString());
        output += "c the next lines contains the XOR";
        output += negation;
        System.out.println(output);
        // System.out.println(negation);

        // get extentions
        boolean isSupport = false;
        // Map<String, int[][]> allSupport = new HashMap<String, int[][]>();

        // DONT TRY TO UNDERSTAND THIS BECAUSE IN HERE LIES MADNESS JUST TRUST THAT IT
        // WORKS AND GETS THE NO GOODS (81 - 115)
        List<int[][]> supportList = new ArrayList<int[][]>(n);
        List<List<List<String>>> supportList2 = new ArrayList<List<List<String>>>(n);
        for (Extension extension : extensionList) {
            int extensionSupport[][] = new int[1000][3];
            List<List<String>> extentSupport = new ArrayList<List<String>>();
            int track = 0;
            for (int i = 0; i < extension.VarIntegerList[0].value.length; i++) {
                for (int k = 0; k < extension.VarIntegerList[1].value.length; k++) {
                    for (int m = 0; m < extension.VarIntegerList[2].value.length; m++) {
                        List<String> supp = new ArrayList<String>();
                        supp.add(satTerm.get(extension.VarIntegerList[0].id).get(i));
                        supp.add(satTerm.get(extension.VarIntegerList[1].id).get(k));
                        supp.add(satTerm.get(extension.VarIntegerList[2].id).get(m));
                        int support[] = { extension.VarIntegerList[0].value[i], extension.VarIntegerList[1].value[k],
                                extension.VarIntegerList[2].value[m] };
                        for (int o = 0; o < extension.supports.length; o++) {
                            if (support == extension.supports[o]) {
                                isSupport = true;
                            }
                        }
                        if (!isSupport) {
                            extensionSupport[track] = support;
                            for (int o = 0; o < supp.size(); o++) {
                                noSupports += supp.get(o) + " ";
                            }
                            noSupports += "0\n";
                            lineCount++;
                            extentSupport.add(supp);
                            track++;
                        }
                    }
                }
            }
            supportList.add(extensionSupport);
            supportList2.add(extentSupport);
        }
        output += "c The next lines contain the noSupports";
        output += noSupports;
        output += "c EOF";
        // System.out.println(supportList2.get(0).get(0).get(0));
        // System.out.println(lineCount);

        // write to the file
        PrintWriter satFile = new PrintWriter(outputPath, "UTF-8");
        satFile.println("c this is nonogram instance");
        satFile.println("p cnf 680 " + lineCount);
        satFile.print(output.trim());
        satFile.close();

    }

}