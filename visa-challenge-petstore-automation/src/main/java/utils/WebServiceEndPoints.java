package utils;

public enum WebServiceEndPoints {

    PET("http://localhost:8085/api/v3/pet"),
    PETBYTAG("/pet/findByTags");


    private final String url;

    WebServiceEndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return "http://localhost:8085/api/v3" + url;
    }
}
