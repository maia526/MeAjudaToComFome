package br.gov.finep.ToComFome.modelo;

import java.util.List;

public class Leg {
    private int index;
    private boolean hasTollRoad;
    private boolean hasHighway;
    private boolean hasBridge;
    private boolean hasUnpaved;
    private boolean hasTunnel;
    private boolean hasSeasonalClosure;
    private boolean hasFerry;
    private boolean hasCountryCross;
    private boolean hasTimedRestriction;
    private double distance;
    private int time;
    private String formattedTime;
    private int origIndex;
    private String origNarrative;
    private int destIndex;
    private String destNarrative;
    private List<Maneuver> maneuvers;
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean isHasTollRoad() {
		return hasTollRoad;
	}
	public void setHasTollRoad(boolean hasTollRoad) {
		this.hasTollRoad = hasTollRoad;
	}
	public boolean isHasHighway() {
		return hasHighway;
	}
	public void setHasHighway(boolean hasHighway) {
		this.hasHighway = hasHighway;
	}
	public boolean isHasBridge() {
		return hasBridge;
	}
	public void setHasBridge(boolean hasBridge) {
		this.hasBridge = hasBridge;
	}
	public boolean isHasUnpaved() {
		return hasUnpaved;
	}
	public void setHasUnpaved(boolean hasUnpaved) {
		this.hasUnpaved = hasUnpaved;
	}
	public boolean isHasTunnel() {
		return hasTunnel;
	}
	public void setHasTunnel(boolean hasTunnel) {
		this.hasTunnel = hasTunnel;
	}
	public boolean isHasSeasonalClosure() {
		return hasSeasonalClosure;
	}
	public void setHasSeasonalClosure(boolean hasSeasonalClosure) {
		this.hasSeasonalClosure = hasSeasonalClosure;
	}
	public boolean isHasFerry() {
		return hasFerry;
	}
	public void setHasFerry(boolean hasFerry) {
		this.hasFerry = hasFerry;
	}
	public boolean isHasCountryCross() {
		return hasCountryCross;
	}
	public void setHasCountryCross(boolean hasCountryCross) {
		this.hasCountryCross = hasCountryCross;
	}
	public boolean isHasTimedRestriction() {
		return hasTimedRestriction;
	}
	public void setHasTimedRestriction(boolean hasTimedRestriction) {
		this.hasTimedRestriction = hasTimedRestriction;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getFormattedTime() {
		return formattedTime;
	}
	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
	}
	public int getOrigIndex() {
		return origIndex;
	}
	public void setOrigIndex(int origIndex) {
		this.origIndex = origIndex;
	}
	public String getOrigNarrative() {
		return origNarrative;
	}
	public void setOrigNarrative(String origNarrative) {
		this.origNarrative = origNarrative;
	}
	public int getDestIndex() {
		return destIndex;
	}
	public void setDestIndex(int destIndex) {
		this.destIndex = destIndex;
	}
	public String getDestNarrative() {
		return destNarrative;
	}
	public void setDestNarrative(String destNarrative) {
		this.destNarrative = destNarrative;
	}
	public List<Maneuver> getManeuvers() {
		return maneuvers;
	}
	public void setManeuvers(List<Maneuver> maneuvers) {
		this.maneuvers = maneuvers;
	}

    
}
