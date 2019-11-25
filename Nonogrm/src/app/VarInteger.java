package app;

class VarInteger {
    String[] value;
    String id;

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VarInteger(String[] value, String id) {
        this.value = value;
        this.id = id;
    }
}