package guiproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Dictionary {

	/*
	 * hMap is map of Events with the event name as a key (in all caps)
	 * reader and writer are used for holding the text file information
	 * 
	 * reader reads from the text file then closes.
	 * writer is created after reader is finished reading. it will then 
	 */
	private HashMap<String,Event> hMap;
	private String myFileName;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Scanner scan;

	Dictionary(String FileName){

		hMap = new HashMap<String,Event>();
		myFileName = FileName;
		openFile();
	}


	public void addEvent(Event event) {
		hMap.put(event.getName().toUpperCase(), event);
	}
	public Event removeEvent(String key) 
	{
		return hMap.remove(key);
	}
	
	//HashMap Management
	public Event get(String key){return hMap.get(key);}
	public HashMap<String,Event> getMap(){return hMap;}
	public Set<String> getMapKeys() {return hMap.keySet();}

	//String conversions
	public String toString() {
		String result = "";

		for(String key: hMap.keySet()) 
		{

			result = result + hMap.get(key) + "\n";
		}

		return result;
	}

	//File Management
	private void loadData() throws IOException
	{
		if (scan == null) {
			return;
		}

		String line;
		String type;		
		Event event;

		while(scan.hasNext()) 
		{
			try 
			{
				type = scan.nextLine().toUpperCase();
				line = scan.nextLine();

				String[] tokens = line.split(" : ");

				String name = tokens[0];
				String years = tokens[1];
				String details = scan.nextLine();

				switch (type) {
				case "WAREVENT":
					String[] aggressors = scan.nextLine().split(", ");
					String[] defenders = scan.nextLine().split(", ");
					ArrayList<String> myAggressors = new ArrayList<String>();
					ArrayList<String> myDefenders = new ArrayList<String>();

					for(String element: aggressors){myAggressors.add(element);}
					for(String element: defenders) {myDefenders.add(element);}
					event = new WarEvent(name, years, details, myAggressors, myDefenders);
					break;

				case "NATIONALEVENT":
					String nation = scan.nextLine();
					event = new NationalEvent(name, years, details, nation);
					break;

				default:
					event = null;
					break;
				}
				
				if(event != null) 
					hMap.put(name.toUpperCase(), event);
				
			} 
			catch (Exception e) {
				reader.close();
				return;
			}
		}
	}

	public void closeFile() {
		if (reader == null) return;

		//Write to text document
		this.saveFile();
		//Close writer
		try
		{
			writer.close();
		}
		catch (IOException e) { }
	}


	public void saveFile() 
	{
		//Iterate through hMap and write to file
		String content;
		Event event;

		try {

			writer = new BufferedWriter(new FileWriter(myFileName));
			
			for(String key : hMap.keySet()) 
			{
				event = hMap.get(key);
				content = event.getName() + " : " + event.getDate() + "\n" + event.getDetail() + "\n";

				//Write object label then generate content
				if(event instanceof WarEvent) 
				{
					writer.write("WAREVENT\n");
					String aggressors = "";
					String defenders = "";
					for(String element: ((WarEvent)event).getAggressors()) {aggressors = aggressors + element + ", ";}
					for(String element: ((WarEvent)event).getDefenders()) {defenders = defenders + element + ", ";}
					aggressors = aggressors.substring(0, aggressors.length()-2);
					defenders = defenders.substring(0, defenders.length()-2);
					content = content + aggressors + "\n" + defenders + "\n";
				}
				else if(event instanceof NationalEvent) 
				{
					writer.write("NATIONALEVENT\n");
					content = content + ((NationalEvent)event).getNation() + "\n";
				}

				//Write created content
				writer.write(content);
			}
			
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void openFile() 
	{

		try
		{
			/*
			 * Reader and scanner are created, loads data
			 */
			reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(myFileName)));
			scan = new Scanner (reader); 
			loadData();
			reader.close();
			/*
			 * writer is initialized
			 */
			//this.saveFile();

		}
		catch (IOException e) {e.printStackTrace();}

	}

}
