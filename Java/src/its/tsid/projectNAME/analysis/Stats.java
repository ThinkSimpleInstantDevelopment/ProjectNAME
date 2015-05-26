package its.tsid.projectNAME.analysis;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Stats {
	private Date period;
	private String nation;
	private String progrLang;
	private int value;

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getProgrLang() {
		return progrLang;
	}

	public void setProgrLang(String progrLang) {
		this.progrLang = progrLang;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Map<String, Object> toMap(){
		Map<String, Object> map = new TreeMap<>();
		
		map.put("date", period.getTime()/1000);
		map.put("nation", nation);
		map.put("programmingLanguage", progrLang);
		map.put("value", value);
				
		return map;
	}
}
