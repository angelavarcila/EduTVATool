package co.com.edutva.bd;

import java.util.Date;

public class Usuario implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022175954828694849L;
	
	private Integer idUsuario;
	private String nmbrUsuario;
	private String aplldUsuario;
	private String usuario;
	private String psswrdUsuario;
	private String emailUsuario;
	private Date fchrgUsuario;
	

	public Usuario() {
	}

	public Usuario(Integer idUsuario, String usuario, String psswrdUsario,
			Date fchrgUsuario) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.psswrdUsuario = psswrdUsario;
		this.fchrgUsuario = fchrgUsuario;
	}

	public Usuario(Integer idUsuario, String nmbrUsuario, String aplldUsuario,
			String usuario, String psswrdUsario, Date fchrgUsuario) {
		this.idUsuario = idUsuario;
		this.nmbrUsuario = nmbrUsuario;
		this.aplldUsuario = aplldUsuario;
		this.usuario = usuario;
		this.psswrdUsuario = psswrdUsario;
		this.fchrgUsuario = fchrgUsuario;
	}

	public Integer getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNmbrUsuario() {
		return this.nmbrUsuario;
	}

	public void setNmbrUsuario(String nmbrUsuario) {
		this.nmbrUsuario = nmbrUsuario;
	}

	public String getAplldUsuario() {
		return this.aplldUsuario;
	}

	public void setAplldUsuario(String aplldUsuario) {
		this.aplldUsuario = aplldUsuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPsswrdUsuario() {
		return this.psswrdUsuario;
	}

	public void setPsswrdUsuario(String psswrdUsuario) {
		this.psswrdUsuario = psswrdUsuario;
	}
	
	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Date getFchrgUsuario() {
		return this.fchrgUsuario;
	}

	public void setFchrgUsuario(Date fchrgUsuario) {
		this.fchrgUsuario = fchrgUsuario;
	}

}
