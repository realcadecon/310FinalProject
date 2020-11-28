package database;

//Standard Library Imports
	import java.util.HashMap;
	import java.util.ArrayDeque;
	import java.util.ArrayList;
	import java.math.*;
	
//Manager Imports
	import error.ErrorManager;

//External Library Imports
//	import java.sql.Connection;
//	import java.sql.DriverManager;
//	import java.sql.SQLException;
//	import java.sql.ResultSet;
//	import java.sql.ResultSetMetaData;
	import java.sql.*;

public final class DatabaseManager {
	
	//A container to hold our 20 common queries (TODO:implement for part 3)
	private static HashMap<String, String> commonCommands = new HashMap<String, String>();
	private static Connection db = null;
	private static String[] customCommands = {"jdb-searchStorm","custom2"};
	
	public DatabaseManager() {
		try {
			//FIXME: I really don't think this is a good way to do it, but online tutorials do it?
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 	
			System.out.println("DatabaseManager is being called here.");
			db = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=" + System.getenv("MYSQL_PASSWORD")); //TODO: make sure this url is right
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO: populate HashMap with pairs like "Job candidates who were not hired", "SELECT * FROM jobcandidate WHERE EmployeeID IS NULL;"
		
	}
	
	//Neatly formats results into a string for printing. Could be an error, actual query, etc.
	@SuppressWarnings("unchecked")
	private static ArrayList<HashMap<String, Object>> interpretResultSet(ResultSet rs) {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>(100);
		try { //TODO:implement function
			ResultSetMetaData md = rs.getMetaData();
			int columns = md.getColumnCount();
			while (rs.next()){
				HashMap<String, Object> row = new HashMap<String, Object>(columns);
			    for(int i=1; i<=columns; i++){
			    	row.put(md.getColumnName(i), rs.getObject(i));
			    }
			    list.add(row);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	public static ResultSet queryDatabase(String command) {
		try {
			return db.createStatement().executeQuery(command);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String handleStormSearch(ArrayList<String> columns, HashMap<String, String> parameters) {
		String query = "";
		String columnsList = "";
		for(int i=0; i< columns.size(); i++) {
			if(i!=columns.size()-1) {
				columnsList+= columns.get(i)+", ";
			}
			else {
				columnsList+= columns.get(i);
			}
		}
		
		String parameterList = "";
		//search by State
		String stateParams;
		if((stateParams = parameters.get("State")) != null) {
			parameterList += "State = " + stateParams;
		}
		
		query = "select "+columnsList+" where "+parameterList+";";
		ArrayList<HashMap<String, Object>> results = interpretResultSet(queryDatabase(query));
		String output = results.toString();
		
		return output;
	}
	
	//Could also send it straight here to some intermediate function like "handleline" which makes the decision instead of main
	public static String handleCustomCommand(String command) {
		String[] parsedValues = command.split(" ");
		boolean validCommandPrefix = false;
		if(parsedValues.length == 0) {
			return "ERROR: Invalid arguments (parsedValues.length = 0)";
		}
		
		out:
		for(int i=0; i<customCommands.length;i++) {
			if(parsedValues[0].equals(customCommands[i])) {
				validCommandPrefix = true;
				break out;
			}		
		}
		if(!validCommandPrefix) {
			return "ERROR: Invalid command \"" + parsedValues[0] + "\"";
		}
		String output = "";
		
		ResultSet rs;
		output = "\n";
		ArrayList<HashMap <String, Object>> results;
		HashMap<String, Object> curr_row;
		String eventID = "";
		//PLACEHOLDER SWITCH TABLE TO CHOOSE COMMAND TO EXECUTE
		switch(parsedValues[0]) {
			case "jdb-searchStorm":
				String searchType = parsedValues[1];	//column name
				String search = parsedValues[2];
				results = interpretResultSet(queryDatabase("select * from storm where "+searchType+" = \'"+search.toUpperCase()+"\';"));
				for(int i=0; i<results.size(); i++) {
					curr_row = results.get(i);
					output+=results.get(i)+"\n";
				}
				break;
			case "custom2":
				break;
			case "custom3":
				break;
		}
		return output;
	}
	
	
	
	public static String handleSQLCommand(String command) {
		String output = "";
		if(command.equals("help")) {
			output+="Available Commands :: \n";
			for(int i=0; i<customCommands.length; i++) {
				output+="\t" + customCommands[i]+"\n";
			}
			output+= "\tOR you can enter a direct SQL query.";
		}
		else {
			ArrayList<HashMap <String, Object>> rsList = interpretResultSet(queryDatabase(command));
			for(int i=0; i<rsList.size(); i++) {
				output+= rsList.get(i) + "\n";
			}
		}
		return output;
	}
	
	public static void closeConnection() {
		try {
			db.close();
			System.out.println("Connection Closed.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
			System.out.println("Connection Opened.");
			db = DriverManager.getConnection("jdbc:mysql://localhost:3308/?user=root&password=" + System.getenv("MYSQL_PASSWORD")); //TODO: make sure this url is right
			
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
