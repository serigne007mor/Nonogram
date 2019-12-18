package app;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "Nonogrm/instances/small/test-001-ternary.xml";
        XCSP3 nonogram = new XCSP3(path);
        Collection<VarInteger> xcsp3Var = nonogram.getMapVar();
        Set<VarInteger> cspVarSet = new HashSet<VarInteger>(xcsp3Var);
        Set<Extension> extensionsSet = nonogram.getExtensionSet();

        Map<VarInteger, SatVar[]> satTerm = new HashMap<VarInteger, SatVar[]>();
        int j = 0;
        for(VarInteger x: cspVarSet){
            int lenght = x.getValue().length;
            System.out.println(x.id+" is:  "+lenght);
            SatVar[] sat = new SatVar[lenght];
            for(int i = 0; i <lenght ;i++){
            sat[i] = new SatVar("t"+j+""+i, false);

            }
            satTerm.put(x, sat);
            j++;
        }
        // System.out.println(satTerm.toString());

    }

}