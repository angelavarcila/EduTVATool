package co.com.edutva.ctl;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

@SuppressWarnings("rawtypes")
public class CompartirContenidoCtl extends GenericForwardComposer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7281042103295924337L;

	// Log de la aplicaci&oacute;n (log4j).
	private Logger logger = Logger.getLogger(this.getClass());
	
	// Window
	protected Window winCompartirContenido;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	public void onCreate$winCompartirContenido(){
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Ingresando a compartir contenido ")
					.append(self.getId()));
	}
	
	/**
	 * Cerrar el elemento padre de la ventana cuando esta se cierra.
	 */
	public void onClose$winCompartirContenido(){
		winCompartirContenido.getParent().detach();
	}

}
