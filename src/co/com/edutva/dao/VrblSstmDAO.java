package co.com.edutva.dao;

import java.util.List;

import co.com.edutva.bd.VrblSstm;

public class VrblSstmDAO extends GenericDAO{

	public static final String CAMPO_NOMBRE_VARIABLE = "nmbrVrblSstm";
	
	@SuppressWarnings("unchecked")
	public VrblSstm obtenerVrblrSstm(String variable) {
		List<VrblSstm> vrblSstms = super.findWhere(VrblSstm.class, CAMPO_NOMBRE_VARIABLE, variable);
		if (vrblSstms.size() != 1) {
			return null;
		} else {
			return vrblSstms.get(0);
		}
	}

}
