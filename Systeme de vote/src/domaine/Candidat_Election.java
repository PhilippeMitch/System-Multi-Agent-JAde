package domaine;

public class Candidat_Election {
	private long numSocial;
	private String dateElection;
	private long nombreVoix;
	private String partiPolitique;
	private String posteCandidat;
	public long getNumSocial() {
		return numSocial;
	}
	public void setNumSocial(long numSocial) {
		this.numSocial = numSocial;
	}
	public String getDateElection() {
		return dateElection;
	}
	public void setDateElection(String dateElection) {
		this.dateElection = dateElection;
	}
	public long getNombreVoix() {
		return nombreVoix;
	}
	public void setNombreVoix(long nombreVoix) {
		this.nombreVoix = nombreVoix;
	}
	public String getPartiPolitique() {
		return partiPolitique;
	}
	public void setPartiPolitique(String partiPolitique) {
		this.partiPolitique = partiPolitique;
	}
	public String getPosteCandidat() {
		return posteCandidat;
	}
	public void setPosteCandidat(String posteCandidat) {
		this.posteCandidat = posteCandidat;
	}
	public Candidat_Election() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Candidat_Election(long numSocial, String dateElection, long nombreVoix, String partiPolitique,
			String posteCandidat) {
		super();
		this.numSocial = numSocial;
		this.dateElection = dateElection;
		this.nombreVoix = nombreVoix;
		this.partiPolitique = partiPolitique;
		this.posteCandidat = posteCandidat;
	}
	public Candidat_Election(long numSocial, String partiPolitique, String posteCandidat) {
		super();
		this.numSocial = numSocial;
		this.partiPolitique = partiPolitique;
		this.posteCandidat = posteCandidat;
	}
	public Candidat_Election(long numSocial, String dateElection, String partiPolitique, String posteCandidat) {
		super();
		this.numSocial = numSocial;
		this.dateElection = dateElection;
		this.partiPolitique = partiPolitique;
		this.posteCandidat = posteCandidat;
	}
	public Candidat_Election(String partiPolitique) {
		super();
		this.partiPolitique = partiPolitique;
	}
	
}
