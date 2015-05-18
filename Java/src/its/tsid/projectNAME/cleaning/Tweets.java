package its.tsid.projectNAME.cleaning;

public class Tweets {
	private String id;
	private String createdAt;
	private String text;
	private int favoriteCount;
	private String lang;
	private String coordinates;
	private int retweetCount;
	private String place;
	private String geo;
	private String[] hashtags;
	private boolean userGeoEnabled;
	private int userFriendsCount;
	private String userLang;
	private String userLocation;
	private String userTimeZone;
	private int userFollowersCount;

	public Tweets() {
	}

	public Tweets(Object[] raw) {
		this.id = (String) raw[0];
		this.createdAt = (String) raw[1];
		this.text = (String) raw[2];
		this.favoriteCount = (int) raw[3];
		this.favoriteCount = (int) raw[4];
		this.lang = (String) raw[5];
		this.coordinates = (String) raw[6];
		this.retweetCount = (int) raw[7];
		this.place = (String) raw[8];
		this.geo = (String) raw[9];
		this.hashtags = (String[]) raw[10];
		this.userGeoEnabled = (boolean) raw[11];
		this.userFriendsCount = (int) raw[12];
		this.userLang = (String) raw[13];
		this.userLocation = (String) raw[14];
		this.userTimeZone = (String) raw[15];
		this.userFollowersCount = (int) raw[16];
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public int getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String[] getHashtags() {
		return hashtags;
	}

	public void setHashtags(String[] hashtags) {
		this.hashtags = hashtags;
	}

	public boolean isUserGeoEnabled() {
		return userGeoEnabled;
	}

	public void setUserGeoEnabled(boolean userGeoEnabled) {
		this.userGeoEnabled = userGeoEnabled;
	}

	public int getUserFriendsCount() {
		return userFriendsCount;
	}

	public void setUserFriendsCount(int userFriendsCount) {
		this.userFriendsCount = userFriendsCount;
	}

	public String getUserLang() {
		return userLang;
	}

	public void setUserLang(String userLang) {
		this.userLang = userLang;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserTimeZone() {
		return userTimeZone;
	}

	public void setUserTimeZone(String userTimeZone) {
		this.userTimeZone = userTimeZone;
	}

	public int getUserFollowersCount() {
		return userFollowersCount;
	}

	public void setUserFollowersCount(int userFollowersCount) {
		this.userFollowersCount = userFollowersCount;
	}

}
