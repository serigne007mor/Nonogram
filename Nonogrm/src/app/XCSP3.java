package app;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.xcsp.common.Types.TypeFlag;
import org.xcsp.parser.callbacks.XCallbacks2;
import org.xcsp.parser.entries.XVariables.XVarInteger;

class XCSP3 implements XCallbacks2 {


    public static void main(String[] args) throws Exception {
        String path = "Nonogrm/instances/small/test-001-ternary.xml";
        XCSP3 x = new XCSP3(path);
    }

    private Implem implem = new Implem(this);

    @Override
    public Implem implem() {
        return implem;
    }

    private Map<XVarInteger, VarInteger> mapVar = new LinkedHashMap<>();

    @Override
    public void buildVarInteger(XVarInteger xx, int minValue, int maxValue) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    @Override
    public void buildVarInteger(XVarInteger xx, int[] values) {
        VarInteger x = new VarInteger(xx.id, values);
        mapVar.put(xx, x);
    }

    private VarInteger trVar(Object x) {
        return mapVar.get((XVarInteger) x);
    }

    private VarInteger[] trVars(Object vars) {
        return Arrays.stream((XVarInteger[]) vars).map(x -> mapVar.get(x)).toArray(VarInteger[]::new);
    }

    private VarInteger[][] trVars2D(Object vars) {
        return Arrays.stream((XVarInteger[][]) vars).map(t -> trVars(t)).toArray(VarInteger[][]::new);
    }

    @Override
    public void buildCtrExtension(String id, XVarInteger[] list, int[][] tuples, boolean positive,
            Set<TypeFlag> flags) {
        if (flags.contains(TypeFlag.STARRED_TUPLES)) {
            // Can you manage short tables ? i.e., tables with tuples containing symbol * ?
            // If not, throw an exception.

        }
        if (flags.contains(TypeFlag.UNCLEAN_TUPLES)) {
            // You have possibly to clean tuples here, in order to remove invalid tuples.
            // A tuple is invalid if it contains a value a for a variable x, not present in
            // dom(x)
            // Note that most of the time, tuples are already cleaned by the parser

        }
        new extension(trVars(list), tuples/*, positive*/);
    }

    public XCSP3(String fileName) throws Exception {
        loadInstance(fileName);
    }
}