package app;

class extension{
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

    public extension(VarInteger[] extension, int[][] supports) {
        this.extension = extension;
        this.supports = supports;
    }

}