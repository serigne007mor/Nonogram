package app;

import java.util.Arrays;

class Extension{
    String id;
    VarInteger []list;
    int [][] supports;

    public VarInteger[] getList() {
        return list;
    }

    public void setList(VarInteger[] list) {
        this.list = list;
    }

    public int[][] getSupports() {
        return supports;
    }

    public void setSupports(int[][] supports) {
        this.supports = supports;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Extension [ExtensionId=" + id + ", ExtensionList=" + Arrays.toString(list) + ", ExtensionSupports=" + Arrays.deepToString(supports)
                + "]";
    }

    public Extension(String id, VarInteger[] list, int[][] supports) {
        this.id = id;
        this.list = list;
        this.supports = supports;
    }

}