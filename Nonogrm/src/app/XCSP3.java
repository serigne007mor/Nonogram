package app;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.xcsp.common.Types.TypeFlag;
import org.xcsp.parser.callbacks.XCallbacks2;
import org.xcsp.parser.entries.XVariables.XVarInteger;

class XCSP3 implements XCallbacks2 {

    // public static void main(String[] args) throws Exception {
    //     // this main fuction is just to test the funcions in this class
    //     String path = "Nonogrm/instances/small/test-001-ternary.xml";
    //     XCSP3 x = new XCSP3(path);
    //     Collection<VarInteger> xcsp3Var = x.getMapVar();
    //     Set<VarInteger> xcsp3Set = new HashSet<VarInteger>(xcsp3Var);
    //     Set<Extension> extensionsSet = x.extensionSet;


    //     System.out.println(extensionsSet.toString()+""+xcsp3Set);
    // }

    private Implem implem = new Implem(this);

    @Override
    public Implem implem() {
        return implem;
    }

    private Map<XVarInteger, VarInteger> mapVar = new LinkedHashMap<>();
    private Set<Extension> extensionSet = new HashSet<Extension>();

    @Override
    public void buildVarInteger(XVarInteger xx, int minValue, int maxValue) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void buildVarInteger(XVarInteger xx, int[] values) {
        VarInteger x = new VarInteger(xx.id, values);
        mapVar.put(xx, x);
    }

    // private VarInteger trVar(Object x) {
    //     return mapVar.get((XVarInteger) x);
    // }

    private VarInteger[] trVars(Object vars) {
        return Arrays.stream((XVarInteger[]) vars).map(x -> mapVar.get(x)).toArray(VarInteger[]::new);
    }

    // Uncomment this if needed
    // private VarInteger[][] trVars2D(Object vars) {
    //     return Arrays.stream((XVarInteger[][]) vars).map(t -> trVars(t)).toArray(VarInteger[][]::new);
    // }

    @Override
    public void buildCtrExtension(String id, XVarInteger[] list, int[][] tuples, boolean positive,
            Set<TypeFlag> flags) {

        if (positive != true) {
            try {
                throw new Exception("Condition not implimented");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (flags.contains(TypeFlag.STARRED_TUPLES)) {
            // Can you manage short tables ? i.e., tables with tuples containing symbol * ?
            // If not, throw an exception.
            try {
                throw new Exception("Condition not implimented");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (flags.contains(TypeFlag.UNCLEAN_TUPLES)) {
            try {
                throw new Exception("Condition not implimented");
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Extension extension = new Extension(id, trVars(list), tuples/*, positive*/);
        extensionSet.add(extension);
    }

    public XCSP3(String fileName) throws Exception {
        loadInstance(fileName);
    }

	public Collection<VarInteger> getMapVar() {
		return mapVar.values();
	}

    public Set<Extension> getExtensionSet() {
        return extensionSet;
    }

}