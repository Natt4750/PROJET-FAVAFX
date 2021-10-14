package application;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FlightDAO {
	
	public static void insertFlight(String LastName, String FirstName, String Departure, String ReturnD) throws ClassNotFoundException, SQLException
	{
		String sql="insert into flightplanner(LastName,FirstName,Departure, ReturnD) values('"+LastName+"','"+FirstName+"','"+Departure+"','"+ReturnD+"')";
		try
		{ 
			DBUtilitaires.dbExecuteQuery(sql);
		}
		catch(SQLException e)
		{
			System.out.println("Erreur à l'insertion de données " + e);
			e.printStackTrace();
			throw e;
		}
	}
	public static void updateFlight(int ID, String LastName, String FirstName, String Departure, String Return) throws ClassNotFoundException, SQLException
	{
		String sql="update flightplanner set LastName='"+LastName+"', FirstName='"+FirstName+"', Departure='"+Departure+"', ReturnD='"+ReturnD+"' where ID= "+ ID;
		
		try
		{
			DBUtilitaires.dbExecuteQuery(sql);
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la mise à jour");
			e.printStackTrace();
			throw e;
		}
		
	}
	public static void deleteFlightById(int id) throws ClassNotFoundException, SQLException
	{
		String sql="delete from flightplanner where ID= "+ id;
		try
		{
			DBUtilitaires.dbExecuteQuery(sql);
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la suppression de données");
			e.printStackTrace();
			throw e;
		}
	}
	public static ObservableList<Flight> getAllRecords() throws ClassNotFoundException, SQLException
	{
		String sql="select * from flightplanner";
		try
		{
			ResultSet rsSet=DBUtilitaires.dbExecute(sql);
			
			
			ObservableList<Flight> FlightList=getFlightObjects(rsSet);
			return FlightList;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur lors de la recupération de données à afficher"+e);
			e.printStackTrace();
			throw e;
		}
		
	}
	private static ObservableList<Flight> getFlightObjects(ResultSet rsSet) throws ClassNotFoundException, SQLException
	{
		try
		{
			
			ObservableList<Flight> FlightList=FXCollections.observableArrayList();
			while(rsSet.next())
			{
				Flight flight=new Flight();
				flight.setId(rsSet.getInt("ID"));
				flight.setFirstName(rsSet.getString("FirstName"));
				flight.setLastName(rsSet.getString("LastName"));
				flight.setDeparture(rsSet.getString("Departure"));
				flight.setAge(rsSet.getString("ReturnD"));
				FlightList.add(flight);
			}
			return FlightList;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur au moment de l'affichage de données "+ e);
			e.printStackTrace();
			throw e;
		}
	}
	public static ObservableList<Flight> searchFlight(String flightId) throws ClassNotFoundException, SQLException
	{
		String sql="select * from flight where id="+flightId;
		try
		{
		 ResultSet rsSet=DBUtilitaires.dbExecute(sql)	;
		 ObservableList<Flight> list=getFlightObjects(rsSet);
		 return list;
		}
		catch(SQLException e)
		{
			System.out.println("Erreur pendant la recherche de données " +e);
			e.printStackTrace();
			throw e;
		}
	}	
	
	
	
	
	
	
	

}
