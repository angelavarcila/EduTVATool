package co.com.edutva.bd;

import java.io.Serializable;
import java.util.Date;

public class Recurso implements Serializable {
	
	private Integer idRecurso;
	private String nmbrRecurso;
	private String rtaRecurso;
	private String dscrpcnRecurso;
	private Boolean isDscrto;
	private Boolean isCmprtdo;
	private Date fchrgRecurso;
	private String usrrgRecurso;
	private String archvRecurso;
	private String imgnRecurso;

	public Recurso() {
		super();
	}
	
	public Recurso(Integer idRecurso, String nmbrRecurso, String rtaRecurso,
			String dscrpcnRecurso, Boolean isDscrto, Boolean isCmprtdo,
			Date fchrgRecurso, String usrrgRecurso, String archvRecurso) {
		super();
		this.idRecurso = idRecurso;
		this.nmbrRecurso = nmbrRecurso;
		this.rtaRecurso = rtaRecurso;
		this.dscrpcnRecurso = dscrpcnRecurso;
		this.isDscrto = isDscrto;
		this.isCmprtdo = isCmprtdo;
		this.fchrgRecurso = fchrgRecurso;
		this.usrrgRecurso = usrrgRecurso;
		this.archvRecurso = archvRecurso;
	}
	
	public Integer getIdRecurso() {
		return idRecurso;
	}
	
	public void setIdRecurso(Integer idRecurso) {
		this.idRecurso = idRecurso;
	}
	
	public String getNmbrRecurso() {
		return nmbrRecurso;
	}
	
	public void setNmbrRecurso(String nmbrRecurso) {
		this.nmbrRecurso = nmbrRecurso;
	}
	
	public String getRtaRecurso() {
		return rtaRecurso;
	}
	
	public void setRtaRecurso(String rtaRecurso) {
		this.rtaRecurso = rtaRecurso;
	}
	
	public String getDscrpcnRecurso() {
		return dscrpcnRecurso;
	}
	
	public void setDscrpcnRecurso(String dscrpcnRecurso) {
		this.dscrpcnRecurso = dscrpcnRecurso;
	}
	
	public Boolean getIsDscrto() {
		return isDscrto;
	}
	
	public void setIsDscrto(Boolean isDscrto) {
		this.isDscrto = isDscrto;
	}
	
	public Boolean getIsCmprtdo() {
		return isCmprtdo;
	}
	
	public void setIsCmprtdo(Boolean isCmprtdo) {
		this.isCmprtdo = isCmprtdo;
	}
	
	public Date getFchrgRecurso() {
		return fchrgRecurso;
	}
	
	public void setFchrgRecurso(Date fchrgRecurso) {
		this.fchrgRecurso = fchrgRecurso;
	}
	
	public String getUsrrgRecurso() {
		return usrrgRecurso;
	}
	
	public void setUsrrgRecurso(String usrrgRecurso) {
		this.usrrgRecurso = usrrgRecurso;
	}
	
	public String getArchvRecurso() {
		return archvRecurso;
	}
	
	public void setArchvRecurso(String archvRecurso) {
		this.archvRecurso = archvRecurso;
	}

	public String getImgnRecurso() {
		return imgnRecurso;
	}

	public void setImgnRecurso(String imgnRecurso) {
		this.imgnRecurso = imgnRecurso;
	}
	
}
