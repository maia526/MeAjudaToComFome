package br.gov.finep.ToComFome.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Business {
	private String id;
    private String alias;
    private String name;
    private String image_url;
    private boolean is_closed;
    private String url;
    private int review_count;
    private List<Category> categories;
    private double rating;
    private Coordinates coordinates;
    private List<String> transactions;
    private String price;
    private Location location;
    private String phone;
    private String display_phone;
    private double distance;
 

    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public boolean isIs_closed() {
		return is_closed;
	}
	public void setIs_closed(boolean is_closed) {
		this.is_closed = is_closed;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public List<String> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDisplay_phone() {
		return display_phone;
	}
	public void setDisplay_phone(String display_phone) {
		this.display_phone = display_phone;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	
}