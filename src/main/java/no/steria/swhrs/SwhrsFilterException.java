package no.steria.swhrs;

public class SwhrsFilterException extends RuntimeException {
    private int statusCode;
    
    public SwhrsFilterException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
