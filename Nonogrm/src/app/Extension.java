package app;

import java.util.Arrays;

class Extension{
    String id;
    VarInteger []VarIntegerList;
    int [][] supports;

    public VarInteger[] getList() {
        return VarIntegerList;
    }

    public void setList(final VarInteger[] list) {
        this.VarIntegerList = list;
    }

    public int[][] getSupports() {
        return supports;
    }

    public void setSupports(final int[][] supports) {
        this.supports = supports;
    }



    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Extension [ExtensionId=" + id + ", ExtensionList=" + Arrays.toString(VarIntegerList) + ", ExtensionSupports=" + Arrays.deepToString(supports)
                + "]";
    }

    public Extension(final String id, final VarInteger[] list, final int[][] supports) {
        this.id = id;
        this.VarIntegerList = list;
        this.supports = supports;
    }

}