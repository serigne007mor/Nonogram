package app;

import java.util.Arrays;

class Extension{
    VarInteger []extension;
    int [][] supports;

    public VarInteger[] getExtension() {
        return extension;
    }

    public void setExtension(VarInteger[] extension) {
        this.extension = extension;
    }

    public int[][] getSupports() {
        return supports;
    }

    public void setSupports(int[][] supports) {
        this.supports = supports;
    }

    public Extension(VarInteger[] extension, int[][] supports) {
        this.extension = extension;
        this.supports = supports;
    }

	@Override
	public String toString() {
		return "Extension [extension=" + Arrays.toString(extension) + ", supports=" + Arrays.toString(supports) + "]";
	}

}