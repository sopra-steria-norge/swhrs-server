package no.steria.swhrs;

public class DatePeriod {
	private String fromDate;
	private String toDate;
	private String description;
	
	public static DatePeriod createPeriod(String fromDate, String toDate, String description){
		DatePeriod dp = new DatePeriod();
		dp.fromDate = fromDate;
		dp.toDate = toDate;
		dp.description = description;
		return dp;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
}
