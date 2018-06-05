package co.com.edutva.ctl;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

@SuppressWarnings("rawtypes")
public class CrearContenidoCtl extends GenericForwardComposer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5156174159291960266L;

	// Log de la aplicaci&oacute;n (log4j).
	private Logger logger = Logger.getLogger(this.getClass());
	
	//Window
	protected Window winCrearContenido;
	
	//Panel
	//protected Panel pnlCrearContenido;
	
	// Variables
	public String altoPantalla;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		altoPantalla = (String) session.getAttribute("altoPantalla");
		//logger.debug("el alto de pantalla es_"+altoPantalla);
		//winCrearContenido.setHeight(altoPantalla);
	}
	
	public void onCreate$winCrearContenido(){
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Ingresando a crear contenido ")
					.append(self.getId()));
	}
	
	/**
	 * Cerrar el elemento padre de la ventana cuando esta se cierra.
	 */
	public void onClose$winCrearContenido(){
		winCrearContenido.getParent().detach();
	}

}
