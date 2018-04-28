package guiproject;

import java.util.ArrayList;

public class WarEvent extends Event{

	private ArrayList<String> aggressors;
	private ArrayList<String> defenders;
	
	
	/*
	 * War Event extends Event, adds aggressors and defenders.

	 */
	
	
	WarEvent(String name, String date, String description, ArrayList<String> aggressors, ArrayList<String> defenders){
		super(name, date, description);
		this.aggressors = aggressors;
		this.defenders = defenders;
	}


	public ArrayList<String> getAggressors() {
		return aggressors;
	}


	public void setAggressors(ArrayList<String> aggressors) {
		this.aggressors = aggressors;
	}


	public ArrayList<String> getDefenders() {
		return defenders;
	}


	public void setDefenders(ArrayList<String> defenders) {
		this.defenders = defenders;
	}
	
	public boolean hasNation(String nation) 
	{
		
		for(String element : aggressors) 
		{
			if(element.toLowerCase().equals(nation.toLowerCase())) {return true;}
		}
		for(String element : defenders) 
		{
			if(element.toLowerCase().equals(nation.toLowerCase())) {return true;}
		}
		return false;
	}
	
	public boolean containsNation(String input) 
	{
		for(String element : aggressors) 
		{
			if(input.toLowerCase().contains(element.toLowerCase())) {return true;}
		}
		for(String element : defenders) 
		{
			if(input.toLowerCase().contains(element.toLowerCase())) {return true;}
		}
		return false;
	}
	
	public String toString() 
	{
		return super.toString() + "Aggressors: " + this.getAggressors() + "\nDefenders: " + this.getDefenders() + "\n";
	}

}
