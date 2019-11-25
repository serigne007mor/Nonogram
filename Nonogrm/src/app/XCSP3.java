package app;

import java.util.LinkedHashMap;
import java.util.Map;

import org.xcsp.parser.callbacks.XCallbacks2;
import org.xcsp.parser.entries.XVariables.XVarInteger;

class XCSP3 implements XCallbacks2 {
    private Implem implem = new Implem(this);

    @Override
    public Implem implem() {
        return implem;
    }

    private Map<XVarInteger, VarInteger> mapVar = new LinkedHashMap<>();

    @Override
    public void buildVarInteger(XVarInteger xx, int minValue, int maxValue) {
        // VarInteger x = xx.id// Build your solver variable x here using xx.id,
        // minValue and maxValue
        // mapVar.put(xx, x);
    }

    @Override
    public void buildVarInteger(XVarInteger xx, int[] values) {
        // VarInteger x = // Build your solver variable x here using xx.id and values
        // mapVar.put(xx, x);
    }

    public XCSP3(String fileName) throws Exception {
        loadInstance(fileName);
    }
}