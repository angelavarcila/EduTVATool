package co.com.edutva.ctl;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import co.com.edutva.bd.Usuario;
import co.com.edutva.ngc.IngresoNgc;
import co.com.edutva.utl.Constantes;
import co.com.edutva.utl.Correo;

@SuppressWarnings("rawtypes")
public class CambiarClaveCtl extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6098153910917047910L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	private Textbox txtUsuario;
	private Textbox txtClaveActual;
	private Textbox txtClaveNueva;
	private Textbox txtClaveConfirmar;
	
	
	/**
	 * Componente de negocio relacionado con el acceso.
	 */
	protected IngresoNgc ingresoNgc;

	/**
	 * Obtiene la instancia del objeto especificado.
	 * 
	 * @return el bean de negocio relacionado con el ingreso
	 */
	public IngresoNgc getIngresoNgc() {
		return ingresoNgc;
	}

	/**
	 * Asigna una instancia del tipo especificado a un atributo de clase.
	 * 
	 * @param ingresoNgc
	 *            el bean de negocio a establecer relacionado con el ingreso
	 */
	public void setIngresoNgc(IngresoNgc ingresoNgc) {
		this.ingresoNgc = ingresoNgc;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}
	
	/**
	 * Método encargado de cambiar la clave de un usuario.
	 * 
	 * @author angela
	 */
	public void onClick$btnAceptarClave() {
		try {
		if(!txtUsuario.getValue().trim().isEmpty() && !txtClaveActual.getValue().trim().isEmpty() && !txtClaveNueva.getValue().trim().isEmpty() && !txtClaveConfirmar.getValue().trim().isEmpty()){
						
				int respuestaLogin = ingresoNgc.validarIngresoUsuario(txtUsuario.getValue().trim(), txtClaveActual.getValue().trim());

				switch (respuestaLogin) {
				case Constantes.EXITO: {
					if (logger.isDebugEnabled())
						logger.debug(new StringBuilder("Usuario ").append(txtUsuario.getValue()).append(" autenticado exitosamente."));
				
					//Obtengo el usuario
					Usuario usuario = ingresoNgc.obtenerUsuario(txtUsuario.getValue().trim());
					
					if(txtClaveNueva.getValue().trim().equalsIgnoreCase(txtClaveConfirmar.getValue().trim())){
						//Actualizar clave y enviar correo
						usuario.setPsswrdUsuario(txtClaveNueva.getValue().trim());
						ingresoNgc.actualizarUsuario(usuario);
						
						StringBuilder msg = new StringBuilder(64);
						msg.append(usuario.getNmbrUsuario()).append(" ")
								.append(usuario.getAplldUsuario())
								.append(",\n\n");
						msg.append("Su nueva contraseña para ingresar a EduTVA es: ")
								.append(usuario.getPsswrdUsuario());
						
						if(new Correo().enviarMensaje(usuario.getEmailUsuario().trim(), msg.toString(), "CAMBIO DE CONTRASEÑA EduTVA")){
							StringBuilder info = new StringBuilder(64);
							info.append("Se ha enviado un correo electrónico de cambio de contraseña a ")
									.append(usuario.getEmailUsuario());
							Messagebox.show(info.toString(), "Información", Messagebox.OK, Messagebox.INFORMATION);
						}else {
							Messagebox.show("Cambio exitoso de contraseña.", "Información", Messagebox.OK, Messagebox.INFORMATION);
						}
						
						self.detach();
						
					}else{
						Messagebox.show("Las claves nuevas no coinciden.", "Error", Messagebox.OK, Messagebox.ERROR);
						txtClaveNueva.setValue("");
						txtClaveConfirmar.setValue("");
					}
				}
					break;
				case Constantes.ERROR: {
					Messagebox.show("Su usuario y/o clave no son correctos.", "Error", Messagebox.OK, Messagebox.ERROR);
					txtUsuario.setValue("");
					txtClaveActual.setValue("");
				}
					break;
				}

		}else {
			Messagebox.show("Por favor ingrese todos los campos.", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		
		} catch (WrongValueException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
}
