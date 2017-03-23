package dataMapper;

public class Review {
	private int review_id;
	private int score;
	private String description;
	private int property_id;
	private int user_id;
	
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getProperty_id() {
		return property_id;
	}
	public void setProperty_id(int property_id) {
		this.property_id = property_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public Review(int review_id, int score, String description, int property_id, int user_id) {
		this.review_id = review_id;
		this.score = score;
		this.description = description;
		this.property_id = property_id;
		this.user_id = user_id;
	}
	

}
