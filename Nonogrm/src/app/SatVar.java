package app;

class SatVar {

    String id;
    Boolean value;

    public SatVar(final String id, final Boolean value) {
        this.id = id;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(final Boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SatVar [SatId=" + id + ", SatValue=" + value + "]";
    }

}