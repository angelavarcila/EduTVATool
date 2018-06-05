package co.com.edutva.ngc;

import co.com.edutva.bd.VrblSstm;
import co.com.edutva.dao.VrblSstmDAO;

public class VrblSstmNgcImpl implements VrblSstmNgc{

	private VrblSstmDAO vrblSstmDAO;

	public VrblSstmDAO getVrblSstmDAO() {
		return vrblSstmDAO;
	}

	public void setVrblSstmDAO(VrblSstmDAO vrblSstmDAO) {
		this.vrblSstmDAO = vrblSstmDAO;
	}

	@Override
	public String obtenerVrblSstm(String variable) throws Exception{
		VrblSstm vrblSstm = vrblSstmDAO.obtenerVrblrSstm(variable);
		if(vrblSstm!=null){
			return vrblSstm.getVlrVrblSstm();
		}else {
			return null;	
		}
	}
	
	
	
}
