package app;

import java.util.Arrays;

class Extension{
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

    public Extension(VarInteger[] list, int[][] supports) {
        this.list = list;
        this.supports = supports;
    }

	@Override
	public String toString() {
		return "Extension [extension=" + Arrays.toString(list) + ", supports=" + Arrays.toString(supports) + "]";
	}

}