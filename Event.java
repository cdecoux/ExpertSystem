package guiproject;

public abstract class Event {
	
	private String name;
	private String date;
	private String description;
	
	/*
	 * Event has a name, date, and description
	 * 
	 */

	Event(String name, String date, String description){
		this.name = name;
		this.date = date;
		this.description = description;
		
		
	}
	
	//Name Management
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	//Date Management
	public String getDate() {return date;}
	public void setDate(String date) {this.date = date;}
	public boolean isInDate(String date)
	{
		//Snips date down to only a number, tokenizes this.date to year(s)
		date = date.replaceAll("[^\\d]", "");
		String[] years = this.date.split("[^\\d]");
		
		//If date is (larger or equal to start date) and (lesser or equal to end date)
		//If Date is just one year, only the exact year will be true
		if(date.compareTo(years[0]) >= 0 && date.compareTo(years[years.length-1]) <= 0) 
		{
			return true;
		}
		
		return false;
	}
	
	//Description Management
	public String getDetail() {return description;}
	public void setDetail(String description) {this.description = description;}
	
	//String conversions
	public String toString() {
		return (this.name + ": \nDate: " + this.date + "\n" + this.description + "\n");
	}

	//Abstract
	public abstract boolean hasNation(String nation);
	public abstract boolean containsNation(String input);
	
}
