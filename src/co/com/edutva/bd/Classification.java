package co.com.edutva.bd;

import java.util.List;

public class Classification {

	private String termID;
	private String name;
	private String definition;
	private List<Classification> classifications;
	
	public Classification() {
		super();
	}

	public Classification(String termID, String name, String definition) {
		super();
		this.termID = termID;
		this.name = name;
		this.definition = definition;
	}
	
	public List<Classification> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<Classification> classifications) {
		this.classifications = classifications;
	}

	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getTermID() {
		return termID;
	}
	public void setTermID(String termID) {
		this.termID = termID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
