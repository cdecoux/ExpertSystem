package guiproject;

public class NationalEvent extends Event {

	String nation;
	
	NationalEvent(String name, String date, String description, String nation) {
		super(name, date, description);
		this.nation = nation;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public boolean hasNation(String nation) 
	{
		return (this.nation.toLowerCase().equals(nation.toLowerCase()));
	}
	
	public boolean containsNation(String input) 
	{
		return (input.toLowerCase().contains(this.nation.toLowerCase()));
	}
	
	public String toString() 
	{
		return this.getName() + ":\nNation: " + this.nation + "\nDate: " + this.getDate() + "\n" + this.getDetail();
	}
	


}
