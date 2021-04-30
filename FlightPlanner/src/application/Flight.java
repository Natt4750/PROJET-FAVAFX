package application;

public class Flight 
{
	private String prenom;
	private String nom;
	private String depart;
	private String retour;
	
	
	//constructeur vide
	public Flight()
	{
		this(null,null);
	}
	
	//constructeur avec 2 parametres
	
	
		//Getters et Setters
		public Flight(String prenom, String nom)
		{
			this.prenom=prenom;
			this.nom=nom;
			this.depart="";
			this.retour="";
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
	
	}



