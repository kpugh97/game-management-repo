package requests;

public class UpdateReviewRequest {
    Integer reviewID;
    String review;
    Integer rating;

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
