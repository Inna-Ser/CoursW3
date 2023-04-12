package pro.sky.cw3.model;

public enum Type {
    ADD("receipt"),
    ISSUE("delivery"),
    REMOVE_DEFECTED("write-off");
    private final String operation;

    Type(String operation) {
        this.operation = operation;
    }
}
