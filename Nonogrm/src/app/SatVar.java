package app;

class SatVar {

    String id;
    Boolean value;

    public SatVar(String id, Boolean value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SatVar [id=" + id + ", value=" + value + "]";
    }

}