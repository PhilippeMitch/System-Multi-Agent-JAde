package domaine;

import java.util.Random;

public class Election {
	private int id;
	private String dateElection;
	private String statusElection;
	private String typeElection;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateElection() {
		return dateElection;
	}
	public void setDateElection(String dateElection) {
		this.dateElection = dateElection;
	}
	public String getStatusElection() {
		return statusElection;
	}
	public void setStatusElection(String statusElection) {
		this.statusElection = statusElection;
	}
	public String getTypeElection() {
		return typeElection;
	}
	public void setTypeElection(String typeElection) {
		this.typeElection = typeElection;
	}
	public int generateId(){
		Random random = new Random();
		int index = random.nextInt(100000000);
		return index;
	}
	public Election() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Election(String dateElection, String statusElection, String typeElection) {
		super();
		this.id=generateId();
		this.dateElection = dateElection;
		this.statusElection = statusElection;
		this.typeElection = typeElection;
	}
	public Election(int id, String dateElection, String statusElection, String typeElection) {
		super();
		this.id = id;
		this.dateElection = dateElection;
		this.statusElection = statusElection;
		this.typeElection = typeElection;
	}
	public Election(String dateElection) {
		super();
		this.dateElection = dateElection;
	}
	
	
}
