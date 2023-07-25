package br.gov.finep.ToComFome.modelo;

import java.util.List;

public class BusinessList {
	private List<Business> businesses;
    private int total;
    private Region region;

	public List<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<Business> restaurante) {
		this.businesses = restaurante;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	
}
