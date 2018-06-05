package co.com.edutva.ctl;

import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Toolbar;
import org.zkoss.zul.Window;

import co.com.edutva.bd.Recurso;
import co.com.edutva.bd.RolPermiso;
import co.com.edutva.bd.Usuario;
import co.com.edutva.ngc.IngresoNgc;
import co.com.edutva.utl.Constantes;

@SuppressWarnings("rawtypes")
public class InicioCtl extends GenericForwardComposer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 50126075672964371L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	/*----COMPONENTES DE LA VENTANA----*/
	/**
	 * Ventana de inicio
	 */
	protected Window winInicio;
	/**
	 * Men� principal
	 */
	//protected Menubar menubar;
	protected Toolbar toolbar;
	/**
	 * �tem del men� que permite crear contenido
	 */
	//protected Toolbarbutton menuCrearContenido;
	protected Button menuCrearContenido;
	/**
	 * �tem del men� que permite describir contenido
	 */
	//protected Toolbarbutton menuDescribirContenido;
	protected Button menuDescribirContenido;
	/**
	 * �tem del men� que permite compartir contenido
	 */
	//protected Toolbarbutton menuCompartirContenido;
	protected Button menuCompartirContenido;
	/**
	 * Item del menú que permitirá visualizar el nombre del usuario. 
	 */
	//protected Menuitem menuLblNmbrUsr;
	protected Label lblNmbrUsr;
	
	/**
	 * Contenedor para a�adir las ventanas abiertas por cada opci�n del men�
	 */
	protected Div divVentanas;
	/**
	 * Logo que hará parte de la página principal
	 */
	protected Image imgLogo;

	/*----ATRIBUTOS----*/
	/**
	 * Atributo que determina el ancho del escritorio.
	 */
	private int anchoEscritorio = 0;
	/**
	 * Atributo que determina el alto del escritorio.
	 */
	private int altoEscritorio = 0;
	/**
	 * Usuario de la sesión
	 */
	private Usuario usuario;
	
	/**
	 * Componente de negocio relacionado con el acceso.
	 */
	protected IngresoNgc ingresoNgc;

	public IngresoNgc getIngresoNgc() {
		return ingresoNgc;
	}

	public void setIngresoNgc(IngresoNgc ingresoNgc) {
		this.ingresoNgc = ingresoNgc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		usuario = (Usuario) session.getAttribute(Constantes.USUARIO);
	}

	public void onCreate$winInicio(Event event) {
		try {
			if (logger.isDebugEnabled())
				logger.debug(new StringBuilder("Ingresando a pagina de inicio ")
						.append(self.getId()));
			
			lblNmbrUsr.setValue(new StringBuilder(64).append(usuario.getNmbrUsuario()).append(" ").append(usuario.getAplldUsuario()).toString());
			
			//Obtener rol del usuario con sus permisos
			List <RolPermiso> permisos = (List<RolPermiso>) ingresoNgc.obtenerPermisosUsuario(usuario);
			
			//Habilitar menú permitido
			if(permisos !=null){
				for(RolPermiso permiso : permisos){
					String menu = permiso.getPermiso().getMnPermiso();
					switch (menu) {
					case Constantes.MENU_CREAR:
						menuCrearContenido.setVisible(true);
						break;
					case Constantes.MENU_COMPARTIR:
						menuCompartirContenido.setVisible(true);
						break;
					case Constantes.MENU_DESCRIBIR:
						menuDescribirContenido.setVisible(true);
						break;
					}
				}	
			}
			
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	
	/**
	 * Obtiene informaci&oacute;n del cliente con respecto a sus caracteristicas f&iacute;sicas, utilizado para obtener dimensiones del escritorio
	 * 
	 * @author angela
	 * @param event ClientInfoEvent evento ejecutado al cargar la interfaz
	 * @throws Exception 
	 */
	public void onClientInfo$winInicio(ClientInfoEvent event) throws Exception {
		
		anchoEscritorio = event.getDesktopWidth();
		altoEscritorio = event.getDesktopHeight();	

		session.setAttribute(Constantes.ANCHO_ESCRITORIO, new Integer(anchoEscritorio));
		session.setAttribute(Constantes.ALTO_ESCRITORIO, new Integer(altoEscritorio));

		////////menubar.setWidth(String.valueOf(anchoEscritorio).concat("px")); //necesario para firefox
		toolbar.setHeight(String.valueOf(altoEscritorio).concat("px")); //necesario para firefox
		
		StringBuilder style = new StringBuilder(64);
		style.append("position: absolute; right: -");//0px; top: ");
		style.append(anchoEscritorio-200);
		style.append("px; top: ");
		style.append(altoEscritorio-412);//412 es el tamaño en pixeles del alto de la imagen
		style.append("px;");
		imgLogo.setStyle(style.toString());	
	}
	
	/**
	 * Men&uacute; que abre la ventana que permite crear el subir un contenido a
	 * la plataforma.
	 * 
	 * @author angela
	 */
	public void onClick$menuCrearContenido() {
		try {
			crearVentana("crearContenido.zul");//,null);
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * Men&uacute; que abre la ventana que permitir&aacute; seleccioanr un
	 * contenido para marcarlo.
	 * 
	 * @author angela
	 */
	public void onClick$menuDescribirContenido() {
		try {	
			if (divVentanas.getFirstChild()==null || (divVentanas.getFirstChild()!=null && !divVentanas.getFirstChild().getId().equalsIgnoreCase("describirContenido.zul"))){
				session.setAttribute("parent", winInicio);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("parentWindow", winInicio);
				Window window = (Window)Executions.createComponents(
		                "resources.zul", null, map);
		        window.doModal();      	
				//crearVentana("describirContenido.zul");
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * Men&uacute; que abre la ventana que permitir&aacute; compartir un
	 * contenido ya marcado (que est&eacute; disponible para cualquier servicio)
	 * 
	 * @author angela
	 */ 
	public void onClick$menuCompartirContenido() {
		try {
			crearVentana("compartirContenido.zul");//,null);
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onSelectedResource(Event event){
		HashMap<String, Object> map = (HashMap<String, Object>) event.getData();
       // String resource = (String) map.get("resource");
        Recurso resource = (Recurso) map.get("resource");
        session.setAttribute(Constantes.RECURSO_POR_MARCAR, resource);
        crearVentana("describirContenido.zul");//, resource);
	}
	
	public void onFinishAnnotation(Event event){
		divVentanas.removeChild(divVentanas.getFirstChild());
	}

	/**
	 * M�todo que crea un include para colocarlo en el div disponible para las
	 * nuevas ventanas
	 * 
	 * @author angela
	 * @param zul
	 */
	public void crearVentana(String zul){//, Object obj) {		
		try {			
			if (divVentanas.getChildren().isEmpty()) {
				Include includeVentana = new Include();
				includeVentana.setWidth("100%");
				includeVentana.setHeight("100%");
				includeVentana.setSrc(zul);
				includeVentana.setParent(divVentanas);
				includeVentana.setId(zul);				
			}else if (divVentanas.getFirstChild().getId().equalsIgnoreCase(zul)) {
				logger.debug("La ventana ya está abierta");
			}else{
				Messagebox.show("Cierre la ventana que tiene abierta e inténtelo de nuevo.", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}	
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}		
	}

	/**
	 * M�todo para abrir la ventana de descarga de archivos. Se llama cuando hay
	 * varios archivos para descargar de manera consecutiva
	 * 
	 * @author Angela M Vargas Arcila
	 * @param String
	 *            destino ->ruta absoluta del archivo para descargar
	 * @param String
	 *            tipo -> tipo de archivo Ej: application/pdf
	 */
	/*
	 * private void descargarArchivo(String destino, String tipo) throws
	 * InterruptedException { final Map map = new HashMap(); map.put("is",
	 * destino); map.put("tipo", tipo);
	 * 
	 * try { final Window win = (Window)Executions.createComponents(
	 * "/panelActividades/actividades/descargar.zul", null, map); win.doModal();
	 * } catch (UiException ux) { ; } //Se ignora pues se debe a que se realizo
	 * mas de un clic a la vez }
	 */

}
