package utils;

public enum WebServiceEndPoints {
    PET("/pet"),
    PETBYTAG("/pet/findByTags"),
    PETBYSTATUS("/pet/findByStatus"),
    PETIMAGE("/pet/%s/uploadImage") ;

    private final String path;

    WebServiceEndPoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
