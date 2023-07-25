package br.gov.finep.ToComFome.modelo;

import java.util.List;

public class Maneuver {
    private int index;
    private double distance;
    private String narrative;
    private int time;
    private int direction;
    private String directionName;
    private List<String> signs;
    private List<String> maneuverNotes;
    private String formattedTime;
    private String transportMode;
    private Point startPoint;
    private int turnType;
    private int attributes;
    private String iconUrl;
    private List<String> streets;
    private String mapUrl;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getNarrative() {
		return narrative;
	}
	public void setNarrative(String narrative) {
		this.narrative = narrative;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public String getDirectionName() {
		return directionName;
	}
	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
	public List<String> getSigns() {
		return signs;
	}
	public void setSigns(List<String> signs) {
		this.signs = signs;
	}
	public List<String> getManeuverNotes() {
		return maneuverNotes;
	}
	public void setManeuverNotes(List<String> maneuverNotes) {
		this.maneuverNotes = maneuverNotes;
	}
	public String getFormattedTime() {
		return formattedTime;
	}
	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
	}
	public String getTransportMode() {
		return transportMode;
	}
	public void setTransportMode(String transportMode) {
		this.transportMode = transportMode;
	}
	public Point getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	public int getTurnType() {
		return turnType;
	}
	public void setTurnType(int turnType) {
		this.turnType = turnType;
	}
	public int getAttributes() {
		return attributes;
	}
	public void setAttributes(int attributes) {
		this.attributes = attributes;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public List<String> getStreets() {
		return streets;
	}
	public void setStreets(List<String> streets) {
		this.streets = streets;
	}
	public String getMapUrl() {
		return mapUrl;
	}
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}

    
}
