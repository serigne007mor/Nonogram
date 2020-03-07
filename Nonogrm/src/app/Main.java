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
        String path = "instances/small/test-001-ternary.xml";
        String outputPath = "output/minisatFile";
        String output = "";
        String negation = "";
        String noSupports = "";
        int lineCount = 0;
        List<String> l = new ArrayList<>();
        XCSP3 nonogram = new XCSP3(path);
        Collection<VarInteger> xcsp3Var = nonogram.getMapVar();
        Set<VarInteger> cspVarSet = new HashSet<VarInteger>(xcsp3Var);
        Set<Extension> extensionsSet = nonogram.getExtensionSet();
        int n = extensionsSet.size();
        List<Extension> extensionList = new ArrayList<Extension>(n);
        for (Extension x : extensionsSet)
            extensionList.add(x);

        Map<String, List<String>> satTerm = new HashMap<String, List<String>>();
        Map<Integer, Integer> hel = new HashMap<Integer, Integer>();
        hel.put(1, 2);
        hel.put(3, 4);
        int j = 0;
        int satVariable = 1;
        // get variables
        for (VarInteger x : cspVarSet) {
            int lenght = x.getValue().length;
            List<String> sat = new ArrayList<>();
            for (int i = 0; i < lenght; i++) {
                // sat.add(i, "t" + j + "" + i);
                sat.add(""+satVariable);
                // negation += "t" + j + "" + i + " -t" + j + "" + i + " 0\n";
                // output += "t" + j + "" + i + " ";
                output += ""+ satVariable+ " ";
                satVariable++;
            }
            output += "0\n";
            lineCount++;
            satTerm.put(x.id, sat);
            j++;
        }
        satTerm.forEach((k, v) -> {
            // List<String> v = satTerm.get("q1");
            for (int i = 0; i < v.size(); i++) {
                for (int p = i+1; p < v.size(); p++) {
                    // negation += "-" + v.get(i) + " -" + v.get(p);
                    l.add("-" + v.get(i) + " -" + v.get(p)+ "\n");
                }
                
            }
        });
        for (int i = 0; i < l.size() ; i ++){
            negation+=l.get(i);
            lineCount++;
        }
        // System.out.println(satTerm.toString());
        output += negation;
        System.out.println(output);
        // System.out.println(negation);

        // get extentions
        boolean isSupport = false;
        Map<String, int[][]> allSupport = new HashMap<String, int[][]>();
        List<int[][]> supportList = new ArrayList<int [][]>(n);
        List<List<List<String>>> supportList2 = new ArrayList<List<List<String>>>(n);
        for (Extension extension : extensionList) {
                int extensionSupport[][] = new int[1000][3];
                List<List<String>> extentSupport = new ArrayList<List<String>>(); 
                int track = 0;
                for (int i = 0; i < extension.VarIntegerList[0].value.length; i++) {
                    for (int k = 0; k < extension.VarIntegerList[1].value.length; k++) {
                        for (int m = 0; m < extension.VarIntegerList[2].value.length; m++) {
                            List<String> supp = new ArrayList<String>();
                            supp.add(satTerm.get(extension.VarIntegerList[0].id).get(i)); supp.add(satTerm.get(extension.VarIntegerList[1].id).get(k)); supp.add(satTerm.get(extension.VarIntegerList[2].id).get(m));
                            int support[] = {extension.VarIntegerList[0].value[i],extension.VarIntegerList[1].value[k],extension.VarIntegerList[2].value[m]};
                            // System.out.println(support[0]);
                            for (int o = 0; o < extension.supports.length; o++) {
                                if (support == extension.supports[o]){
                                    isSupport = true;
                                }
                            }
                            if(!isSupport){
                                extensionSupport[track] = support; 
                                for (int o = 0; o < supp.size(); o++) {
                                    noSupports+= supp.get(o)+ " ";
                                }
                                noSupports+= "0\n";
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
        output+= noSupports;
        // System.out.println(supportList2.get(0).get(0).get(0));
        // System.out.println(lineCount);

        // write to the file
        PrintWriter satFile = new PrintWriter(outputPath, "UTF-8");
        satFile.println("c this is nonogram instance");
        satFile.println("p cnf 680 "+lineCount);
        satFile.print(output.trim());
        satFile.close();

    }

}