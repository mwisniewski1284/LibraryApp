package pl.programistaodzera.LibraryApp.review;

public class SimplifiedReviewDTO {
    private String displayName;
    private String body;
    private Integer rating;

    public SimplifiedReviewDTO(String displayName, String body, Integer rating) {
        this.displayName = displayName;
        this.body = body;
        this.rating = rating;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
