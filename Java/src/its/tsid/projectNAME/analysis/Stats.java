package its.tsid.projectNAME.analysis;

public class Stats {
	private String timePeriod;
	private String language;
	private String nation;
	private int value;

	public Stats() {
	}

	public Stats(Object[] raw) {
		this.timePeriod = (String) raw[0];
		this.language = (String) raw[1];
		this.nation = (String) raw[2];
		this.value = (int) raw[3];
	}

	public String getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(String timePeriod) {
		this.timePeriod = timePeriod;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
