package app;

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
        XCSP3 nonogram = new XCSP3(path);
        Collection<VarInteger> xcsp3Var = nonogram.getMapVar();
        Set<VarInteger> cspVarSet = new HashSet<VarInteger>(xcsp3Var);
        Set<Extension> extensionsSet = nonogram.getExtensionSet();

        Map<VarInteger, List<SatVar>> satTerm = new HashMap<VarInteger, List<SatVar>>();
        int j = 0;
        Map<Integer, Integer> hel = new HashMap<Integer, Integer>();
        hel.put(1, 2);
        hel.put(3, 4);
        for(VarInteger x: cspVarSet){
            int lenght = x.getValue().length;
            // System.out.println(x.id+" is:  "+lenght);
            List<SatVar> sat = new ArrayList<>();
            for(int i = 0; i <lenght ;i++){
            sat.add(i, new SatVar("t"+j+""+i, false));

            }
            // System.out.print(sat.toString());
            satTerm.put(x, sat);
            j++;
        }
        // System.out.println(extensionsSet.toString());
        System.out.println(satTerm.toString());
        // System.out.println(hel.toString());
    }

}