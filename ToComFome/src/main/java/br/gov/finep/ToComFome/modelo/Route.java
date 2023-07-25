package br.gov.finep.ToComFome.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public class Route{
	 	private String sessionId;
	    private int realTime;
	    private double distance;
	    private int time;
	    private String formattedTime;
	    private boolean hasHighway;
	    private boolean hasTollRoad;
	    private boolean hasBridge;
	    private boolean hasSeasonalClosure;
	    private boolean hasTunnel;
	    private boolean hasFerry;
	    private boolean hasUnpaved;
	    private boolean hasTimedRestriction;
	    private boolean hasCountryCross;
	    private List<Leg> legs;
	    private Options options;
	    private BoundingBox boundingBox;
	    private List<String> routeWarnings;
	    private String name;
	    private String maxRoutes;
	    private List<LocationMapQuest> locations;
	    private List<Integer> locationSequence;
	    
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public int getRealTime() {
			return realTime;
		}
		public void setRealTime(int realTime) {
			this.realTime = realTime;
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
		public boolean isHasHighway() {
			return hasHighway;
		}
		public void setHasHighway(boolean hasHighway) {
			this.hasHighway = hasHighway;
		}
		public boolean isHasTollRoad() {
			return hasTollRoad;
		}
		public void setHasTollRoad(boolean hasTollRoad) {
			this.hasTollRoad = hasTollRoad;
		}
		public boolean isHasBridge() {
			return hasBridge;
		}
		public void setHasBridge(boolean hasBridge) {
			this.hasBridge = hasBridge;
		}
		public boolean isHasSeasonalClosure() {
			return hasSeasonalClosure;
		}
		public void setHasSeasonalClosure(boolean hasSeasonalClosure) {
			this.hasSeasonalClosure = hasSeasonalClosure;
		}
		public boolean isHasTunnel() {
			return hasTunnel;
		}
		public void setHasTunnel(boolean hasTunnel) {
			this.hasTunnel = hasTunnel;
		}
		public boolean isHasFerry() {
			return hasFerry;
		}
		public void setHasFerry(boolean hasFerry) {
			this.hasFerry = hasFerry;
		}
		public boolean isHasUnpaved() {
			return hasUnpaved;
		}
		public void setHasUnpaved(boolean hasUnpaved) {
			this.hasUnpaved = hasUnpaved;
		}
		public boolean isHasTimedRestriction() {
			return hasTimedRestriction;
		}
		public void setHasTimedRestriction(boolean hasTimedRestriction) {
			this.hasTimedRestriction = hasTimedRestriction;
		}
		public boolean isHasCountryCross() {
			return hasCountryCross;
		}
		public void setHasCountryCross(boolean hasCountryCross) {
			this.hasCountryCross = hasCountryCross;
		}
		public List<Leg> getLegs() {
			return legs;
		}
		public void setLegs(List<Leg> legs) {
			this.legs = legs;
		}
		public Options getOptions() {
			return options;
		}
		public void setOptions(Options options) {
			this.options = options;
		}
		public BoundingBox getBoundingBox() {
			return boundingBox;
		}
		public void setBoundingBox(BoundingBox boundingBox) {
			this.boundingBox = boundingBox;
		}
		public List<String> getRouteWarnings() {
			return routeWarnings;
		}
		public void setRouteWarnings(List<String> routeWarnings) {
			this.routeWarnings = routeWarnings;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMaxRoutes() {
			return maxRoutes;
		}
		public void setMaxRoutes(String maxRoutes) {
			this.maxRoutes = maxRoutes;
		}
		
		public List<LocationMapQuest> getLocations() {
			return locations;
		}
		public void setLocations(List<LocationMapQuest> locations) {
			this.locations = locations;
		}
		public List<Integer> getLocationSequence() {
			return locationSequence;
		}
		public void setLocationSequence(List<Integer> locationSequence) {
			this.locationSequence = locationSequence;
		}
	    
	    
}