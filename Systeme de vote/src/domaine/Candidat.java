package domaine;

public class Candidat {

	private long numSocial;
	private String nom;
	private String prenom;
	private String sexe;
	private String dateNaiss;
	public long getNumSocial() {
		return numSocial;
	}
	public void setNumSocial(long numSocial) {
		this.numSocial = numSocial;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(String dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public Candidat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candidat(long numSocial, String nom, String prenom, String sexe, String dateNaiss) {
		super();
		this.numSocial = numSocial;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaiss = dateNaiss;
	}
	
	
}
