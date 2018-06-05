package co.com.edutva.bd;

import java.util.Date;

public class VrblSstm implements java.io.Serializable {

	private Integer idVrblSstm;
	private String nmbrVrblSstm;
	private String vlrVrblSstm;
	private String dscrpcnVrblSstm;
	private Date fchrgVrblSstm;

	public VrblSstm() {

	}

	public VrblSstm(Integer idVrblSstm, String nmbrVrbl, String vlrVrbl,
			Date fchrgVrbl) {
		this.idVrblSstm = idVrblSstm;
		this.nmbrVrblSstm = nmbrVrbl;
		this.vlrVrblSstm = vlrVrbl;
		this.fchrgVrblSstm = fchrgVrbl;
	}
	
	public VrblSstm(Integer idVrblSstm, String nmbrVrbl, String vlrVrbl, String dscrpcnVrbl,
			Date fchrgVrbl) {
		this.idVrblSstm = idVrblSstm;
		this.nmbrVrblSstm = nmbrVrbl;
		this.vlrVrblSstm = vlrVrbl;
		this.dscrpcnVrblSstm = dscrpcnVrbl;
		this.fchrgVrblSstm = fchrgVrbl;
	}

	public Integer getIdVrblSstm() {
		return idVrblSstm;
	}

	public void setIdVrblSstm(Integer idVrblSstm) {
		this.idVrblSstm = idVrblSstm;
	}

	public String getNmbrVrblSstm() {
		return nmbrVrblSstm;
	}

	public void setNmbrVrblSstm(String nmbrVrblSstm) {
		this.nmbrVrblSstm = nmbrVrblSstm;
	}

	public String getVlrVrblSstm() {
		return vlrVrblSstm;
	}

	public void setVlrVrblSstm(String vlrVrblSstm) {
		this.vlrVrblSstm = vlrVrblSstm;
	}

	public String getDscrpcnVrblSstm() {
		return dscrpcnVrblSstm;
	}

	public void setDscrpcnVrblSstm(String dscrpcnVrblSstm) {
		this.dscrpcnVrblSstm = dscrpcnVrblSstm;
	}

	public Date getFchrgVrblSstm() {
		return fchrgVrblSstm;
	}

	public void setFchrgVrblSstm(Date fchrgVrblSstm) {
		this.fchrgVrblSstm = fchrgVrblSstm;
	}
	
}
