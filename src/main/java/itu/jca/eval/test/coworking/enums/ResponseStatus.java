package itu.jca.eval.test.coworking.enums;

public enum ResponseStatus {
    ERROR(400),
    SERVER_ERROR(500),
    SUCCESS(200);

    private final int status;
    ResponseStatus(int status){
        this.status = status;
    }
    public int getStatus() {
        return status;
    }
}
