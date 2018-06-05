package co.com.edutva.ctl;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

@SuppressWarnings("rawtypes")
public class AnnotationCtl extends GenericForwardComposer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791615501020009652L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	protected Window parentWindow;
	protected Textbox txtAnnotation;
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		parentWindow = (Window) execution.getArg().get("parentWindow");
	}
	
	public void onClick$btnCancelar() throws InterruptedException{
		self.detach();
	}
	
	public void onClick$btnAceptar() {
		try {
			if(txtAnnotation.getValue()!=null && !txtAnnotation.getValue().trim().isEmpty()){
				final HashMap<String, Object> map = new HashMap<String, Object>();
		        map.put("annotation", txtAnnotation.getValue().trim());
				Messagebox.show("Anotación creada exitosamente.", "Información", Messagebox.OK, Messagebox.INFORMATION);
				self.detach();
		        Events.sendEvent(new Event("onAnnotationCreated", parentWindow, map));
			}else {
				Messagebox.show("No ha ingresado la nueva anotación.", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
}
