package app;

import java.util.Arrays;

class VarInteger {
    String id;
    int[] value;

    public int[] getValue() {
        return value;
    }

    public void setValue(final int[] value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public VarInteger(final String id, final int[] value) {
        this.value = value;
        this.id = id;
    }

    @Override
    public String toString() {
        // return "VarInteger [VarId=" + id + ", VarValue=" + Arrays.toString(value) + "]";
        return "VarId=" + id + ", VarValue=" + Arrays.toString(value) ;
    }

}