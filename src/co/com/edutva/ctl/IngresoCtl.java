package co.com.edutva.ctl;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.A;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.edutva.bd.Usuario;
import co.com.edutva.ngc.IngresoNgc;
import co.com.edutva.utl.Constantes;
import co.com.edutva.utl.Correo;

@SuppressWarnings("rawtypes")
public class IngresoCtl extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5889782040383449434L;
	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Campo de texto utilizado para el ingreso del nombre de usuario de acceso
	 * del usuario.
	 */
	private Textbox txtUsuario;
	/**
	 * Campo de texto utilizado para el ingreso de la clave de acceso del
	 * usuario.
	 */
	private Textbox txtClave;
	/**
	 * Boton para aceptar login e iniciar sesion en el sistema
	 */
	private Button btnAceptarLogin;
	/**
	 * Componente tipo link para aceptar cambiar login
	 */
	private A aCambiarPassword;
	/**
	 * Componente tipo link para aceptar Recuperar el login
	 */
	private A aRecuperarPassword;
	/**
	 * Temporizador para la autenticaci&oacute;n.
	 */
	// private Timer timer;

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

	/**
	 * Maneja el evento de dibujo de la pagina de inicio.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		// Definir el tiempo del timer con la variable del sistema especificada
		// int tiempoSeg =
		// Integer.parseInt(entornoSistemaNgc.obtenerVariableSistema(Constantes.ACCESO_LIMITE_SEGUNDOS).getValor())
		// * 1000;
		// timer.setDelay(tiempoSeg);
		// Propiedad para deshabilitar el botono aceptar al instante de hacer
		// click sobre el mismo
		btnAceptarLogin.setAutodisable("self");
	}

	/**
	 * M&eacute;todo que se ejecuta automaticamente al cargar la interfaz de
	 * usuario
	 */
	@SuppressWarnings({ "unchecked" })
	public void onCreate$winIngreso(Event event) {
		try {
			if (logger.isDebugEnabled())
				logger.debug(new StringBuilder("Ingresando a pagina de inicio ")
						.append(self.getId()));
			// usuario = (Usuario)
			// session.getAttribute(Constantes.SESION_USUARIO);
			txtUsuario.setFocus(true);
			// Adicionar un listener para el evento onClick
			aCambiarPassword.addEventListener(Events.ON_CLICK,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							onClick$aCambiarPassword(event);
						}
					});
			aRecuperarPassword.addEventListener(Events.ON_CLICK,
					new EventListener() {
						@Override
						public void onEvent(Event event) throws Exception {
							onClick$aRecuperarPassword(event);
						}
					});

		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
			self.detach();
		}
	}

	/**
	 * Maneja el evento de presionar la tecla ENTER en la ventana. Llama al
	 * m&eacute;todo que maneja el evento de hacer clic sobre el bot&oacute;n
	 * ingresar.
	 */
	public void onOK$winIngreso(Event event) throws InterruptedException {
		onClick$btnAceptarLogin(event);
	}

	/**
	 * C&oacute;digo ejecutado en el evento del bot&oacute;n aceptar para
	 * iniciar la autenticaci&oacute;n del usuario.
	 */
	public void onClick$btnAceptarLogin(Event event)
			throws InterruptedException {
		try {
			this.validarLogin();
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * C&aacute;digo ejecutado en el evento del boton cambiar clave, abre un
	 * nuevo formulario con la respectiva funcionalidad
	 */
	public void onClick$aCambiarPassword(Event event) throws Exception {
		try {
			//ventana de cambio de password
		    Window window = (Window)Executions.createComponents("cambiarClave.zul", null, null);
	        window.doModal();
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
			self.detach();
		}
	}

	/**
	 * Funcionalidad de recuperaci&oacute;n de clave, env&iacute;a un correo
	 * electr&oacute;nico con la nueva clave al mail configurado para el usuario
	 * proporcionado en la interfaz
	 */
	public void onClick$aRecuperarPassword(Event event) {
		try {
			if(!txtUsuario.getValue().trim().isEmpty()){
				//Obtengo el usuario
				Usuario usuario = ingresoNgc.obtenerUsuario(txtUsuario.getValue().trim());
				
				if(usuario !=null){
					if(usuario.getEmailUsuario()!=null){
						//Enviar clave al usuario		
						StringBuilder msg = new StringBuilder(64);
						msg.append(usuario.getNmbrUsuario()).append(" ")
								.append(usuario.getAplldUsuario())
								.append(",\n\n");
						msg.append("Su contraseña para ingresar a EduTVA es: ")
								.append(usuario.getPsswrdUsuario());
						
						new Correo().enviarMensaje(usuario.getEmailUsuario(), msg.toString(), "RECUPERACIÓN DE CONTRASEÑA EduTVA");

						StringBuilder info = new StringBuilder(64);
						info.append(
								"Se ha enviado un correo electrónico de recuperación de contraseña a ")
								.append(usuario.getEmailUsuario());
						Messagebox.show(info.toString(), "Información",
								Messagebox.OK, Messagebox.INFORMATION);
					} else {
						Messagebox.show("No es posible recuperar su contraseña, por favor contacte al administrador a labtdiunicauca@gmail.com", "Error", Messagebox.OK, Messagebox.ERROR);
					}	
				}else {
					Messagebox.show("Usuario incorrecto", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}else {
				Messagebox.show("Por favor ingrese su usuario.", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}		
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
			self.detach();
		}
	}

	/**
	 * C&oacute;digo ejecutado en el evento onOK (Enter) sobre el campo de texto
	 * de la clave de usuario. Ejecuta la acci&oacute;n del bot&oacute;n
	 * Ingresar
	 */
	public void onOK$txtClave(Event event) throws InterruptedException {
		this.onClick$btnAceptarLogin(event);
	}

	/**
	 * Valida usuario y password ingresados, desplegando ventanas o mensajes
	 * informativos
	 */
	@SuppressWarnings("unused")
	private void validarLogin() throws Exception {
		try {
			if (!txtUsuario.getValue().trim().isEmpty()
					&& !txtClave.getValue().trim().isEmpty()) {
				int respuestaLogin = ingresoNgc.validarIngresoUsuario(txtUsuario.getValue().trim(), txtClave.getValue().trim());

				switch (respuestaLogin) {
				case Constantes.EXITO: {
					if (logger.isDebugEnabled())
						logger.debug(new StringBuilder("Usuario ").append(txtUsuario.getValue()).append(" autenticado exitosamente."));
				
					//Obtengo el usuario
					Usuario usuario = ingresoNgc.obtenerUsuario(txtUsuario.getValue().trim());
					//Establezco el usuario de la sesión
					session.setAttribute(Constantes.USUARIO, usuario);
					//Abrir ventana menú
					Window window = (Window) Executions.createComponents("inicio.zul", null, null);
					self.getParent().detach(); // Se obtiene el componente padre que es el Vlayout y lo cierro.
				}
					break;
				case Constantes.ERROR: {
					Messagebox.show("Su usuario y/o clave no son correctos.", "Error", Messagebox.OK, Messagebox.ERROR);
					txtUsuario.setValue("");
					txtClave.setValue("");
				}
					break;
				}
			} else {
				Messagebox.show("Debe ingresar todos los campos", "Error", Messagebox.OK, Messagebox.ERROR);
				txtClave.setValue("");
			}

		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ").append(e.getMessage()), e);
		}
	}

}
