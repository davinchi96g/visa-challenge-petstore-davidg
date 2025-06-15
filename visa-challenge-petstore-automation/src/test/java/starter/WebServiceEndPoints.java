package starter;

public enum WebServiceEndPoints {
    STATUS("http://localhost:9090/api/status"),
    TRADE("http://localhost:9090/api/trade"),
    PET("http://localhost:8085/api/v3/pet");

    private final String url;

    WebServiceEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
