package co.com.edutva.utl;


public class Constantes {

	//ATRIBUTOS DE LA SESIÓN
	/**
	 * Nombre del atributo para establecer/obtener el ancho del escritorio.
	 */
	public static final String ANCHO_ESCRITORIO = "anchoEscritorio";

	/**
	 * Nombre del atributo para establecer/obtener el alto del escritorio.
	 */
	public static final String ALTO_ESCRITORIO = "altoEscritorio";
	
	public static final String USUARIO = "usuario";
	
	public static final String RECURSO_POR_MARCAR = "resource";
	//////////////
	
	///MENÚ
	public static final String MENU_CREAR = "crearContenido.zul";
	public static final String MENU_DESCRIBIR = "describirContenido.zul";
	public static final String MENU_COMPARTIR = "compartirContenido.zul";
	
	
	/////77
	
	/**
	 * Tipos de prioridad para las palabras clave
	 */
	public static final String MAIN_KEYWORD = "main";
	public static final String SECOND_KEYWORD = "secondary";
	public static final String OTHER_KEYWORD = "other";
	
	/**
	 * Rutas de servicio
	 */
	public static final String URL_EDUTVA = "url_edutva";
	public static final String URL_VIDEOS_EDUTVA = "url_videos";
	public static final String URL_DSCRPCN = "url_dscrpcn";
	public static final String CRID_EDUTVA = "CRID";
	public static final String RUTA_LOGO_VIDEO = "/imgs/video-tv-logo.jpg";
	public static final String RUTA_CLASSIFICATION_SCHEMES = "ruta_ClassificationScheme";
	public static final String RUTA_EDUTVA_XSD ="ruta_xsd";
	
	/**
	 * Esquemas de clasificación utilizados
	 */
	public static final String CS_INTENDED_EDUCATIONAL_USER = "intended_educational_user";
	public static final String CS_EDUCATIONAL_USE = "educational_use";
	public static final String CS_ROLE = "role";
	public static final String CS_CONTENT = "content";
	public static final String CS_PLACE = "place";
	public static final String CS_SEMANTIC_DENSITY = "semantic_density";
	public static final String CS_EDUCATIONAL_CONTEXT = "educational_context";
	public static final String CS_ABILITY = "ability";
	public static final String CS_INTERACTIVITY_TYPE = "interactivity_type";
	public static final String CS_EDUCATIONAL_AUDIENCE = "educational_audience";
	public static final String CS_AGE_RANGE = "age_range";
	public static final String CS_FILE_FORMAT = "file_format";
	
	
	/**
	 * USUARIO DE LA SESIÓN
	 */
	public static final String SESION_USUARIO = "sesionUsuario";
	
	/**
	 * MENSAJES DE ERROR               
	 */
	public static final int EXITO = 200;
	public static final int ERROR = 404;
}
