package br.gov.finep.ToComFome.modelo;

import java.util.List;
import java.util.Map;

public class YelpData {
	private List<Review> reviews;
    private int total;
    private List<String> possible_languages;
	
	
	
	public List<Review> getReviews() {
		return reviews;
	}
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<String> getPossible_languages() {
		return possible_languages;
	}
	public void setPossible_languages(List<String> possible_languages) {
		this.possible_languages = possible_languages;
	}
	
	
	
}
