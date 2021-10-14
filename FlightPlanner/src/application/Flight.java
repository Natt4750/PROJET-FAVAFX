package application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Flight 
{
	private String prenom;
	private String nom;
	private String depart;
	private String retour;
	
	
	//constructeur vide
	public Flight()
	{
		
	}
	
	//constructeur avec 2 parametres
	
	
		//Getters et Setters
		public Flight(String prenom, String nom)
		{
			this.prenom=prenom;
			this.nom=nom;
			this.depart="";
			this.retour="";
			
			
		    	this.idProperty = new SimpleIntegerProperty();
		        this.firstNameProperty = new SimpleStringProperty();
		        this.lastNameProperty = new SimpleStringProperty();
		        this.departureProperty = new SimpleStringProperty();
		        this.returnProperty= new SimpleStringProperty();	
		    
		}
	
	
	
	
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDepart() 
	{
		return depart;
	}
	
	public void setDepart(String depart) 
	{
		this.depart = depart;
	}
	public String getRetour() 
	{
		return retour;
	}
	public void setRetour(String retour) {
		this.retour = retour;
	
	
}
	
    private IntegerProperty idProperty;
    private StringProperty firstNameProperty;
    private StringProperty lastNameProperty;
    private StringProperty departureProperty;
    private StringProperty returnProperty;
  
    
    
    
   public int getId()
   {
	   return idProperty.get();
   }
   public void setId(int id)
   {
	   this.idProperty.set(id);
   }
    public IntegerProperty getPersonId() {
		return idProperty;
	}
	public void setIdProperty(IntegerProperty idProperty) {
		this.idProperty = idProperty;
	}
	public String getFirstName()
	{
		return firstNameProperty.get();
	}
	public void setFirstName(String firstName)
	{
		this.firstNameProperty.set(firstName);
	}
	public StringProperty getFirstNameProperty() {
		return firstNameProperty;
	}
	public void setFirstNameProperty(StringProperty firstNameProperty) {
		this.firstNameProperty = firstNameProperty;
	}

	public StringProperty getLastNameProperty() {
		return lastNameProperty;
	}

	public void setLastNameProperty(StringProperty lastNameProperty) {
		this.lastNameProperty = lastNameProperty;
	}

	public String getLastName()
	{
		return lastNameProperty.get();
	}
	public void setLastName(String lastName)
	{
		this.lastNameProperty.set(lastName);
	}
	
	public StringProperty getDepartureProperty() {
		return departureProperty;
	}

	public void setDepartmentProperty(StringProperty departureProperty) {
		this.departureProperty = departureProperty;
	}
	public String getDeparture()
	{
		return departureProperty.get();
	}
	public void setDeparture(String departure)
	{
		this.departureProperty.set(departure);
	}
	
	public StringProperty getReturnProperty() {
		return returnProperty;
		
	}
	public void setAgeProperty(StringProperty returnProperty) {
		this.returnProperty = returnProperty;
	}
	public String getAge()
	{
		return returnProperty.get();
	}
	public void setAge(String age)
	{
		this.returnProperty.set(age);
	}
	@Override
    public String toString() {
	 String nom=this.getFirstName() + " " +this.getLastName();
        return nom;  
    }

	

}



