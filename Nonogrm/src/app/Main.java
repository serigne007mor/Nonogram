package app;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "Nonogrm/instances/small/test-001-ternary.xml";
        XCSP3 nonogram = new XCSP3(path);
        Collection<VarInteger> xcsp3Var = nonogram.getMapVar();
        Set<VarInteger> xcsp3Set = new HashSet<VarInteger>(xcsp3Var);
        Set<Extension> extensionsSet = nonogram.getExtensionSet();


    }

}