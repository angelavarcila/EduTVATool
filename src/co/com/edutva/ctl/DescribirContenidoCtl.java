package co.com.edutva.ctl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.zkoss.chart.Charts;
import org.zkoss.chart.ChartsEvent;
import org.zkoss.chart.ChartsEvents;
import org.zkoss.chart.model.DefaultXYModel;
import org.zkoss.chart.model.XYModel;
import org.zkoss.chart.plotOptions.DataLabels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.A;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Html;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import co.com.edutva.bd.Classification;
import co.com.edutva.bd.Recurso;
import co.com.edutva.bd.Usuario;
import co.com.edutva.ngc.ResourceNgc;
import co.com.edutva.ngc.VrblSstmNgc;

//import co.com.edutva.tva2v616.AVAttributesType;
//import co.com.edutva.tva2v616.BasicSegmentDescriptionType;
//import co.com.edutva.tva2v616.ClassificationSchemeTableType;
//import co.com.edutva.tva2v616.ContentPropertiesType;
//import co.com.edutva.tva2v616.ControlledTermType;
//import co.com.edutva.tva2v616.ControlledTermUseType;
//import co.com.edutva.tva2v616.CreditsItemType;
//import co.com.edutva.tva2v616.CreditsListType;
//import co.com.edutva.tva2v616.DepictedCoordinatesType;
//import co.com.edutva.tva2v616.EducationalContextAttributesType;
//import co.com.edutva.tva2v616.ExtendedContentDescriptionType;
//import co.com.edutva.tva2v616.ExtendedLanguageType;
//import co.com.edutva.tva2v616.ExtendedTVAMainType;
//import co.com.edutva.tva2v616.GenreType;
//import co.com.edutva.tva2v616.KeywordType;
//import co.com.edutva.tva2v616.MediaRelTimePointType;
//import co.com.edutva.tva2v616.MetadataOriginationInformationTableType;
//import co.com.edutva.tva2v616.MetadataOriginationInformationType;
//import co.com.edutva.tva2v616.ObjectFactory;
//import co.com.edutva.tva2v616.OrganizationNameType;
//import co.com.edutva.tva2v616.PersonNameType;
//import co.com.edutva.tva2v616.ProgramDescriptionType;
//import co.com.edutva.tva2v616.ProgramInformationTableType;
//import co.com.edutva.tva2v616.ProgramInformationType;
//import co.com.edutva.tva2v616.SegmentInformationTableType;
//import co.com.edutva.tva2v616.SegmentInformationType;
//import co.com.edutva.tva2v616.SegmentListType;
//import co.com.edutva.tva2v616.ShortTitleType;
//import co.com.edutva.tva2v616.SynopsisType;
//import co.com.edutva.tva2v616.TVAClassificationSchemeType;
//import co.com.edutva.tva2v616.TVAMainType;
//import co.com.edutva.tva2v616.TVAMediaTimeType;
//import co.com.edutva.tva2v616.TVAPlaceType;
//import co.com.edutva.tva2v616.TVATimeType;
//import co.com.edutva.tva2v616.TermNameType;
//import co.com.edutva.tva2v616.TextualType;
//import co.com.edutva.tva2v616.TitleType;

import co.com.edutva.edutvav1.AVAttributesType;
import co.com.edutva.edutvav1.ClassificationSchemeTableType;
import co.com.edutva.edutvav1.ContentPropertiesType;
import co.com.edutva.edutvav1.ControlledTermType;
import co.com.edutva.edutvav1.ControlledTermUseType;
import co.com.edutva.edutvav1.CreditsItemType;
import co.com.edutva.edutvav1.CreditsListType;
import co.com.edutva.edutvav1.DepictedCoordinatesType;
import co.com.edutva.edutvav1.EducationalAudienceType;
import co.com.edutva.edutvav1.EducationalContextAttributesType;
import co.com.edutva.edutvav1.EducationalResourceType;
import co.com.edutva.edutvav1.EducationalResultsType;
import co.com.edutva.edutvav1.ExtendedContentDescriptionType;
import co.com.edutva.edutvav1.ExtendedLanguageType;
import co.com.edutva.edutvav1.ExtendedTVAMainType;
import co.com.edutva.edutvav1.GenreType;
import co.com.edutva.edutvav1.KeywordType;
import co.com.edutva.edutvav1.LanguageType;
import co.com.edutva.edutvav1.MetadataOriginationInformationTableType;
import co.com.edutva.edutvav1.MetadataOriginationInformationType;
import co.com.edutva.edutvav1.ObjectFactory;
import co.com.edutva.edutvav1.ProgramDescriptionType;
import co.com.edutva.edutvav1.ProgramInformationTableType;
import co.com.edutva.edutvav1.ProgramInformationType;
import co.com.edutva.edutvav1.SegmentInformationTableType;
import co.com.edutva.edutvav1.SegmentInformationType;
import co.com.edutva.edutvav1.SegmentListType;
import co.com.edutva.edutvav1.ShortTitleType;
import co.com.edutva.edutvav1.SynopsisType;
import co.com.edutva.edutvav1.TVAClassificationSchemeType;
import co.com.edutva.edutvav1.TVAMainType;
import co.com.edutva.edutvav1.TVAPlaceType;
import co.com.edutva.edutvav1.TVATimeType;
import co.com.edutva.edutvav1.TermNameType;
import co.com.edutva.edutvav1.TextualType;
import co.com.edutva.edutvav1.TitleType;
import co.com.edutva.utl.Constantes;
import co.com.edutva.utl.LectorCS;
import co.com.edutva.utl.LocaleComparator;
import co.com.edutva.utl.code.CountryCode;

/**
 * Clase controladora para la ventana que permite ingresar los metadatos que
 * describir&aacute;n el contenido.
 * 
 * @author root
 *
 */
@SuppressWarnings("rawtypes")
public class DescribirContenidoCtl extends GenericForwardComposer implements
		ComboitemRenderer, ListitemRenderer, TreeitemRenderer {

	// public int seg = 0;

	/**
	 * 
	 */
	private static final long serialVersionUID = -4223591400583838041L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	/*----COMPONENTES GENERALES DE LA VENTANA----*/
	/**
	 * Ventana que contiene las funciones para describir un recurso por medio de
	 * EduTVA
	 */
	protected Window winDescribirContenido;

	/*----COMPONENTES DEL PRIMER CONTENEDOR----*/
	/**
	 * Contenedor de los formularios de ingreso de metadatos
	 */
	protected Div divFormularios;
	/**
	 * Groupbox que contiene el formulario con los elementos generales de EduTVA
	 * que debe ingresar el usuario (metadatos)
	 */
	protected Groupbox grpFormGnrl;
	/**
	 * Div que contiene el formulario con los elementos generales de EduTVA que
	 * debe ingresar el usuario (metadatos)
	 */
	protected Div divFormGnrl;
	/**
	 * Groupbox que contiene el formulario con los elementos educativos de
	 * EduTVA que debe ingresar el usuario (metadatos)
	 */
	protected Groupbox grpFormEdu;
	/**
	 * Div que contiene el formulario con los elementos educativos de EduTVA que
	 * debe ingresar el usuario (metadatos)
	 */
	protected Div divFormEdu;

	/**
	 * T&iacute;tulo del recurso
	 */
	protected Textbox txtTitulo;
	/**
	 * T&iacute;tulo corto del recurso
	 */
	protected Textbox txtTituloCorto;
	/**
	 * Sinopsis del recurso
	 */
	protected Textbox txtDescr;

	/**
	 * Idiomas del recurso
	 */
	protected Listbox lstbxIdiomas;
	protected Listheader lsthdNmbrIdioma;
	protected Listheader lsthdPaisIdioma;

	protected Datebox dbxFechaProd;
	//protected Textbox txtProdCountry;
	protected Listbox lstbxProdCountry;
	protected Textbox txtFiltroCountry;
	//protected Textbox txtEpocaRepresentada;
	protected Bandbox cbxSitiosRepresentados;
	protected Grid gridLugaresRepresentados;
	protected Tree treeSitiosRepresentados;
	protected Intbox ibxDurationH;
	protected Intbox ibxDurationM;
	protected Intbox ibxDurationS;

	protected Bandbox cbxIdioma;

	/**
	 * Tabla que contiene los campos de texto para ingresar las palabras clave
	 * que considere el usuario
	 */
	protected Grid gridPalabrasClave;
	/**
	 * 
	 */
	protected Tree treeGenre;
	/**
	 * 
	 */
	protected Listbox lstbxCreditos;
	protected Textbox txtCreditsNmbrOrgNm;
	protected Combobox cbxCreditsSelRol;
	protected Textbox txtCreditsPersonaje;
	protected Combobox cbxCreditsPersonaOrg;
	protected Bandbox cbxProdCountry;

	// /.----------educativo------------
	protected Groupbox grpMetadataEduResource;
	protected Groupbox grpMetadataEduContext;
	protected Groupbox grpMetadataEduAudience;
	protected Groupbox grpMetadataEduAnotation;
	protected Groupbox grpMetadataEduResults;

	protected Combobox cbxTipoInteractividad;
	protected Bandbox cbxTipoRecurso;
	protected Listbox lstbxTipoRecurso;
	protected Textbox txtEducationalUse;

	protected Bandbox cbxEducationalContext;
	// protected Tree treeEducationalContext;
	protected Listbox lstbxEducationalContext;

	protected Bandbox cbxEducationalRole;
	protected Listbox lstbxEducationalRole;
	protected Tree treeAgeRange;
	protected Bandbox cbxEduIdioma;
	protected Listbox lstbxEduIdiomas;
	protected Textbox txtFiltroIdiomas;
	protected Textbox txtEduFiltroIdiomas;

	protected Listbox lstbxAnnotations;
	protected Textbox txtAnnotation;
	protected Tree treeAbility;

	// **educ segmento
	protected Listbox lstbxSgmts;

	/**
	 * Groupbox que contiene la descripción de los segmentos
	 */
	protected Groupbox grpSegmentos;
	/**
	 * Div que contiene la descripción de los segmentos
	 */
	protected Div divSegmentos;

	/*----COMPONENTES DEL SEGUNDO CONTENEDOR----*/
	/**
	 * Groupbox que muestra el recurso audiovisual y permite realizar marcas de
	 * tiempo para marcar segmentos de contenido
	 */
	protected Groupbox grpGrafico;
	/**
	 * Contenedor que muestra el recurso audiovisual y permite realizar marcas
	 * de tiempo para marcar segmentos de contenido
	 */
	protected Div divGrafico;
	/**
	 * Div que contiene el video correspondiente al recurso a describir
	 */
	protected Div divVideo;
	/**
	 * Gráfico indicador de tiempo para los segmentos marcados
	 */
	protected Charts chartSegmentos;

	private Div divImgIniSegMovimiento;
	private Image imgInicioSegmento;
	// private Image imgFinalSegmento;

	/**
	 * Tag html para insertar video
	 */
	protected Html videoHtml;

	/*----COMPONENTES DEL TERCER CONTENEDOR----*/
	// protected Div divVerXML;
	// protected Textbox txtVerXML;

	/*----VARIABLES----*/
	// public BasicContentDescriptionType bDescriptionTypeGnrl;//para cargar la
	// info general.
	public List<SegmentInformationType> segmentos;
	public List<CreditsItemType> lstCreditos;
	public List<TextualType> lstEduAnnotations;
	public XYModel model;
	public boolean winSegmentIsOpen;

	public List<Locale> localesLst;//lista todos los idiomas
	public List<Locale> localesLstSelect; //lista de los idiomas seleccionados en descrp general
	public List<Locale> localesEduLstSelect; //lista de los idiomas seleccionados en caract educativas
	public List<CountryCode> countryLstSelect;
	
	private Recurso rcrs;
	private ResourceNgc resourceNgc;

	public ResourceNgc getResourceNgc() {
		return resourceNgc;
	}

	public void setResourceNgc(ResourceNgc resourceNgc) {
		this.resourceNgc = resourceNgc;
	}

	private VrblSstmNgc vrblSstmNgc;

	public VrblSstmNgc getVrblSstmNgc() {
		return vrblSstmNgc;
	}

	public void setVrblSstmNgc(VrblSstmNgc vrblSstmNgc) {
		this.vrblSstmNgc = vrblSstmNgc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		divImgIniSegMovimiento = new Div();
		imgInicioSegmento = new Image();
		// imgFinalSegmento = new Image();

		localesLstSelect = new ArrayList<>();
		localesEduLstSelect = new ArrayList<>();
		countryLstSelect = new ArrayList<>();
		
		segmentos = new ArrayList<SegmentInformationType>();
		lstCreditos = new ArrayList<CreditsItemType>();
		lstEduAnnotations = new ArrayList<TextualType>();
		model = new DefaultXYModel();

		// Ajuste de componentes que contienen las marcas de tiempo para 
		// un segmento
		divImgIniSegMovimiento.setParent(divVideo);
		divImgIniSegMovimiento.setVisible(false);
		imgInicioSegmento.setSrc("/imgs/marca.png");
		imgInicioSegmento.setParent(divImgIniSegMovimiento);

		// Ajuste de las dimensiones de los componentes de la ventana
		dimensionarVentana();

		// Agrego fila para ingreso de palabras clave
		Rows rowsPalabrasClave = new Rows();
		rowsPalabrasClave.setParent(gridPalabrasClave);
		nuevaFilaPalabrasClave(gridPalabrasClave);

		// Agrego fila para ingreso de lugares representados
		Rows rowsLugaresRepr = new Rows();
		rowsLugaresRepr.setParent(gridLugaresRepresentados);
		nuevaFilaPalabrasClave(gridLugaresRepresentados);

		// Lectura de classification schemes
		LectorCS lector = new LectorCS();

		// Datos de EducationalUseCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc
						.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_USE))
				.toString());
		ListModelList tiposRecEduModel = new ListModelList(lector.getElements());
		tiposRecEduModel.setMultiple(true);
		lstbxTipoRecurso.setModel(tiposRecEduModel);
		lstbxTipoRecurso.setItemRenderer(this);

		// Datos de EducationalContextCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc
						.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_CONTEXT))
				.toString());
		ListModelList classificationModelContext = new ListModelList(
				lector.getElements());
		classificationModelContext.setMultiple(true);
		lstbxEducationalContext.setModel(classificationModelContext);
		lstbxEducationalContext.setItemRenderer(this);

		// Datos IntendedEducationalUserCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc
						.obtenerVrblSstm(Constantes.CS_INTENDED_EDUCATIONAL_USER))
				.toString());
		ListModelList educationalRoleModel = new ListModelList(
				lector.getElements());
		educationalRoleModel.setMultiple(true);
		lstbxEducationalRole.setModel(educationalRoleModel);
		lstbxEducationalRole.setItemRenderer(this);

		// Datos de AbilityCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_ABILITY))
				.toString());
		ClassificationTreeNode rootNodeAbility = new ClassificationTreeNode(
				null);
		extractClasificationTreeNode(lector.getElements(), rootNodeAbility);
		DefaultTreeModel<Classification> classificationModelAbility = new DefaultTreeModel<Classification>(
				rootNodeAbility);
		classificationModelAbility.setMultiple(true);
		treeAbility.setModel(classificationModelAbility);
		treeAbility.setItemRenderer(this);

		// Datos de AgeRangeCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_AGE_RANGE))
				.toString());
		ClassificationTreeNode rootNodeAgeRange = new ClassificationTreeNode(
				null);
		extractClasificationTreeNode(lector.getElements(), rootNodeAgeRange);
		DefaultTreeModel<Classification> classificationModelAgeRange = new DefaultTreeModel<Classification>(
				rootNodeAgeRange);
		classificationModelAgeRange.setMultiple(true);
		treeAgeRange.setModel(classificationModelAgeRange);
		treeAgeRange.setItemRenderer(this);

		// Datos InteractivityCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc
						.obtenerVrblSstm(Constantes.CS_INTERACTIVITY_TYPE))
				.toString());
		ListModelList interactivityType = new ListModelList(
				lector.getElements());
		cbxTipoInteractividad.setModel(interactivityType);
		cbxTipoInteractividad.setItemRenderer(this);

		// Árbol que contiene los géneros definidos en el estándar TVA
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_CONTENT))
				.toString());
		ClassificationTreeNode rootNodeGenre = new ClassificationTreeNode(null);
		extractClasificationTreeNode(lector.getElements(), rootNodeGenre);
		DefaultTreeModel<Classification> classificationModelGenre = new DefaultTreeModel<Classification>(
				rootNodeGenre);
		classificationModelGenre.setMultiple(true);
		treeGenre.setModel(classificationModelGenre);
		treeGenre.setItemRenderer(this);

		// Combobox que contiene los lugares definidos en PlaceTypeCS
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_PLACE))
				.toString());
		ClassificationTreeNode rootNodeSitios = new ClassificationTreeNode(null);
		extractClasificationTreeNode(lector.getElements(), rootNodeSitios);
		DefaultTreeModel<Classification> classificationModelSitios = new DefaultTreeModel<Classification>(
				rootNodeSitios);
		classificationModelSitios.setMultiple(true);
		treeSitiosRepresentados.setModel(classificationModelSitios);
		treeSitiosRepresentados.setItemRenderer(this);

		// Combobox que contiene los idiomas
		Locale locales[] = SimpleDateFormat.getAvailableLocales();
		//List<Locale> localesLst = new ArrayList<Locale>();
		localesLst = new ArrayList<Locale>();
		for (int i = 1; i < locales.length; i++) {// el primero viene vacío
			localesLst.add(locales[i]);
		}

		localesLst.sort(new LocaleComparator(true, 1));

		ListModelList idiomasModel = new ListModelList(localesLst);
		idiomasModel.setMultiple(true);
		lstbxIdiomas.setModel(idiomasModel);
		lstbxIdiomas.setItemRenderer(this);
		lstbxIdiomas.renderAll();

		ListModelList eduIdiomasModel = new ListModelList(localesLst);
		eduIdiomasModel.setMultiple(true);
		lstbxEduIdiomas.setModel(eduIdiomasModel);
		lstbxEduIdiomas.setItemRenderer(this);
		lstbxEduIdiomas.renderAll();

		Comparator cNameAsc = new LocaleComparator(true, 1);
		Comparator cNameDsc = new LocaleComparator(false, 1);
		lsthdNmbrIdioma.setSortAscending(cNameAsc);
		lsthdNmbrIdioma.setSortDescending(cNameDsc);

		//Listbox que contiene la lista de países y sus códigos
		ListModelList countryModel = new ListModelList(CountryCode.values());
		countryModel.setMultiple(true);
		lstbxProdCountry.setModel(countryModel);
		lstbxProdCountry.setItemRenderer(this);
		lstbxProdCountry.renderAll();
		// cargarRecursoPorMarcar();
	}

	/*
	 * ------SECCIÓN EVENTOS-----
	 */
	public void onCreate$winDescribirContenido() {
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Ingresando a describir contenido ")
					.append(self.getId()));
		try {
			// // Ajuste de componentes que contienen las marcas de tiempo para
			// 
			// // un segmento
			// divImgIniSegMovimiento.setParent(divVideo);
			// divImgIniSegMovimiento.setVisible(false);
			// imgInicioSegmento.setSrc("/imgs/marca.png");
			// imgInicioSegmento.setParent(divImgIniSegMovimiento);
			//
			// // Ajuste de las dimensiones de los componentes de la ventana
			// dimensionarVentana();
			//
			// // Agrego fila para ingreso de palabras clave
			// Rows rowsPalabrasClave = new Rows();
			// rowsPalabrasClave.setParent(gridPalabrasClave);
			// nuevaFilaPalabrasClave(gridPalabrasClave);
			//
			// // Agrego fila para ingreso de lugares representados
			// Rows rowsLugaresRepr = new Rows();
			// rowsLugaresRepr.setParent(gridLugaresRepresentados);
			// nuevaFilaPalabrasClave(gridLugaresRepresentados);
			//
			// // Lectura de classification schemes
			// LectorCS lector = new LectorCS();
			//
			// // Datos de EducationalUseCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_USE)).toString());
			// ListModelList tiposRecEduModel = new
			// ListModelList(lector.getElements());
			// tiposRecEduModel.setMultiple(true);
			// lstbxTipoRecurso.setModel(tiposRecEduModel);
			// lstbxTipoRecurso.setItemRenderer(this);
			//
			// // Datos de EducationalContextCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_CONTEXT)).toString());
			// // ClassificationTreeNode rootNodeEducationalContext = new
			// ClassificationTreeNode(null);
			// // extractClasificationTreeNode(lector.getElements(),
			// rootNodeEducationalContext);
			// // DefaultTreeModel<Classification> classificationModelContext =
			// new DefaultTreeModel<Classification>(rootNodeEducationalContext);
			// ListModelList classificationModelContext = new
			// ListModelList(lector.getElements());
			// classificationModelContext.setMultiple(true);
			// // treeEducationalContext.setModel(classificationModelContext);
			// // treeEducationalContext.setItemRenderer(this);
			// lstbxEducationalContext.setModel(classificationModelContext);
			// lstbxEducationalContext.setItemRenderer(this);
			//
			// // Datos IntendedEducationalUserCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_INTENDED_EDUCATIONAL_USER)).toString());
			// ListModelList educationalRoleModel = new
			// ListModelList(lector.getElements());
			// educationalRoleModel.setMultiple(true);
			// lstbxEducationalRole.setModel(educationalRoleModel);
			// lstbxEducationalRole.setItemRenderer(this);
			//
			// // Datos de AbilityCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_ABILITY)).toString());
			// ClassificationTreeNode rootNodeAbility = new
			// ClassificationTreeNode(null);
			// extractClasificationTreeNode(lector.getElements(),
			// rootNodeAbility);
			// DefaultTreeModel<Classification> classificationModelAbility = new
			// DefaultTreeModel<Classification>(rootNodeAbility);
			// classificationModelAbility.setMultiple(true);
			// treeAbility.setModel(classificationModelAbility);
			// treeAbility.setItemRenderer(this);
			//
			// // Datos de AgeRangeCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_AGE_RANGE)).toString());
			// ClassificationTreeNode rootNodeAgeRange = new
			// ClassificationTreeNode(null);
			// extractClasificationTreeNode(lector.getElements(),
			// rootNodeAgeRange);
			// DefaultTreeModel<Classification> classificationModelAgeRange =
			// new DefaultTreeModel<Classification>(rootNodeAgeRange);
			// classificationModelAgeRange.setMultiple(true);
			// treeAgeRange.setModel(classificationModelAgeRange);
			// treeAgeRange.setItemRenderer(this);
			//
			// // Datos InteractivityCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_INTERACTIVITY_TYPE)).toString());
			// ListModelList interactivityType = new
			// ListModelList(lector.getElements());
			// cbxTipoInteractividad.setModel(interactivityType);
			// cbxTipoInteractividad.setItemRenderer(this);
			//
			// // Árbol que contiene los géneros definidos en el estándar TVA
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_CONTENT)).toString());
			// ClassificationTreeNode rootNodeGenre = new
			// ClassificationTreeNode(null);
			// extractClasificationTreeNode(lector.getElements(),
			// rootNodeGenre);
			// DefaultTreeModel<Classification> classificationModelGenre = new
			// DefaultTreeModel<Classification>(rootNodeGenre);
			// classificationModelGenre.setMultiple(true);
			// treeGenre.setModel(classificationModelGenre);
			// treeGenre.setItemRenderer(this);
			//
			// // Combobox que contiene los lugares definidos en PlaceTypeCS
			// lector.setClassificationScheme(new
			// StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_PLACE)).toString());
			// ClassificationTreeNode rootNodeSitios = new
			// ClassificationTreeNode(null);
			// extractClasificationTreeNode(lector.getElements(),
			// rootNodeSitios);
			// DefaultTreeModel<Classification> classificationModelSitios = new
			// DefaultTreeModel<Classification>(rootNodeSitios);
			// classificationModelSitios.setMultiple(true);
			// treeSitiosRepresentados.setModel(classificationModelSitios);
			// treeSitiosRepresentados.setItemRenderer(this);
			//
			// // Combobox que contiene los idiomas
			// Locale locales[] = SimpleDateFormat.getAvailableLocales();
			// List<Locale> localesLst = new ArrayList<Locale>();
			// for (int i = 1; i < locales.length; i++) {// el primero viene
			// vacío
			// localesLst.add(locales[i]);
			// }
			//
			// localesLst.sort(new LocaleComparator(true, 1));
			//
			// ListModelList idiomasModel = new ListModelList(localesLst);
			// idiomasModel.setMultiple(true);
			// lstbxIdiomas.setModel(idiomasModel);
			// lstbxIdiomas.setItemRenderer(this);
			//
			// ListModelList eduIdiomasModel = new ListModelList(localesLst);
			// eduIdiomasModel.setMultiple(true);
			// lstbxEduIdiomas.setModel(eduIdiomasModel);
			// lstbxEduIdiomas.setItemRenderer(this);
			//
			// Comparator cNameAsc = new LocaleComparator(true, 1);
			// Comparator cNameDsc = new LocaleComparator(false, 1);
			// lsthdNmbrIdioma.setSortAscending(cNameAsc);
			// lsthdNmbrIdioma.setSortDescending(cNameDsc);

			// Inicializar gráfico de segmentos
			pintarGraficoSegmentos();
			chartSegmentos.setVisible(false);

			cargarRecursoPorMarcar();
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * Cerrar el elemento padre de la ventana cuando esta se cierra.
	 * 
	 * @author Angela Vargas
	 */
	public void onClose$winDescribirContenido() {
		winDescribirContenido.getParent().detach();
	}

	public void onClick$btnAyuda() {
		Window window = (Window) Executions.createComponents("ayuda.zul", null,
				null);
		window.doModal();
	}

	public void onOpen$grpFormGnrl() {
		grpFormEdu.setOpen(false);
		grpSegmentos.setOpen(false);
	}

	public void onOpen$grpFormEdu() {
		grpFormGnrl.setOpen(false);
		grpSegmentos.setOpen(false);
	}

	public void onOpen$grpMetadataEduResource() {
		changeGroupboxCaptionArrow(grpMetadataEduResource);
	}

	public void onOpen$grpMetadataEduContext() {
		changeGroupboxCaptionArrow(grpMetadataEduContext);
	}

	public void onOpen$grpMetadataEduAudience() {
		changeGroupboxCaptionArrow(grpMetadataEduAudience);
	}

	public void onOpen$grpMetadataEduAnotation() {
		changeGroupboxCaptionArrow(grpMetadataEduAnotation);
	}

	public void onOpen$grpMetadataEduResults() {
		changeGroupboxCaptionArrow(grpMetadataEduResults);
	}

	public void onOpen$grpSegmentos() {
		grpFormGnrl.setOpen(false);
		grpFormEdu.setOpen(false);
	}

	/**
	 * M&eacute;todo que se encarga de agregar una fila para permitir el ingreso
	 * de m&aacute palabras clave
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnAgregarFilaPalabrasClave() {
		nuevaFilaPalabrasClave(gridPalabrasClave);
	}

	/**
	 * M&eacute;todo que se encarga de agregar una fila para permitir el ingreso
	 * de m&aacute lugares representados
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnAgregarFilaLugaresRepresentados() {
		nuevaFilaPalabrasClave(gridLugaresRepresentados);
	}

	/**
	 * M&eacute;todo que se encarga de eliminar una fila de ingreso de palabras
	 * clave
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnEliminarFilaPalabrasClave() {
		if (gridPalabrasClave.getRows().getChildren().size() != 1) {
			gridPalabrasClave.getRows().removeChild(
					gridPalabrasClave.getRows().getLastChild());
		}
	}

	/**
	 * M&eacute;todo que se encarga de eliminar una fila de ingreso de lugares
	 * representados
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnEliminarFilaLugaresRepresentados() {
		if (gridLugaresRepresentados.getRows().getChildren().size() != 1) {
			gridLugaresRepresentados.getRows().removeChild(
					gridLugaresRepresentados.getRows().getLastChild());
		}
	}

	public void onChange$txtFiltroIdiomas(){	
		String lang = txtFiltroIdiomas.getText().toLowerCase();
		renderIdiomasTmp(lang, lstbxIdiomas);   		
		//seleccionar los que ya estaban seleccionados
		if (localesLstSelect != null && !localesLstSelect.isEmpty()) {
			for (Locale languageSelected : localesLstSelect) {
				for (Listitem listitem : lstbxIdiomas.getItems()) {
					if (((Locale) listitem.getValue()).toString()
							.equalsIgnoreCase(
									languageSelected.toString())) {
						listitem.setSelected(true);
					}
				}
			}
			onSelect$lstbxIdiomas();
		}
	}
	
	public void onChange$txtEduFiltroIdiomas(){	
		String lang = txtEduFiltroIdiomas.getText().toLowerCase();
		renderIdiomasTmp(lang, lstbxEduIdiomas);   		
		//seleccionar los que ya estaban seleccionados
		if (localesEduLstSelect != null && !localesEduLstSelect.isEmpty()) {
			for (Locale languageSelected : localesEduLstSelect) {
				for (Listitem listitem : lstbxEduIdiomas.getItems()) {
					if (((Locale) listitem.getValue()).toString()
							.equalsIgnoreCase(
									languageSelected.toString())) {
						listitem.setSelected(true);
					}
				}
			}
			onSelect$lstbxEduIdiomas();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onChange$txtFiltroCountry(){	
		String lang = txtFiltroCountry.getText().toLowerCase();
		
		List<CountryCode> countryLstTmp = new ArrayList<CountryCode>();

		for (CountryCode codCountry : CountryCode.values()) {
			if (codCountry.getName().toLowerCase().contains(lang)) {
            	countryLstTmp.add(codCountry);
            }	
		}
		onSelect$lstbxProdCountry();

        ListModelList countryModel = new ListModelList(countryLstTmp);
		countryModel.setMultiple(true);
		lstbxProdCountry.setModel(countryModel);
		lstbxProdCountry.setItemRenderer(this);
		lstbxProdCountry.renderAll();
		
		//seleccionar los que ya estaban seleccionados
		if (countryLstSelect != null && !countryLstSelect.isEmpty()) {
			for (CountryCode countrySelected : countryLstSelect) {
				for (Listitem listitem : lstbxProdCountry.getItems()) {
					if (((CountryCode) listitem.getValue()).getName().equalsIgnoreCase(
							countrySelected.getName())) {
						listitem.setSelected(true);
					}
				}
			}
			onSelect$lstbxProdCountry();
		}
	}
	
	/**
	 * 
	 */
	public void onSelect$lstbxIdiomas() {	
		//setVisibleLstbxValuesOnCombobox(lstbxIdiomas, cbxIdioma);
		setVisibleLstbxValuesOnCombobox(localesLstSelect, cbxIdioma);
	}
	
	/**
	 * 
	 */
	public void onSelect$lstbxProdCountry() {
//		setVisibleLstbxValuesOnCombobox(lstbxProdCountry, cbxProdCountry);
		setVisibleLstbxValuesOnCombobox(countryLstSelect, cbxProdCountry);
	}

	/**
	 * 
	 */
	public void onSelect$lstbxEduIdiomas() {
//		setVisibleLstbxValuesOnCombobox(lstbxEduIdiomas, cbxEduIdioma);
		setVisibleLstbxValuesOnCombobox(localesEduLstSelect, cbxEduIdioma);
	}
	

	/**
	 * 
	 */
	public void onSelect$lstbxTipoRecurso() {
		setVisibleLstbxValuesOnCombobox(lstbxTipoRecurso, cbxTipoRecurso);
	}

	/**
	 * 
	 */
	public void onSelect$lstbxEducationalRole() {
		setVisibleLstbxValuesOnCombobox(lstbxEducationalRole,
				cbxEducationalRole);
	}

	/**
	 * 
	 */
	public void onSelect$treeSitiosRepresentados() {
		StringBuilder sitioVisible = new StringBuilder(64);
		if (treeSitiosRepresentados.getSelectedItems() != null
				&& !treeSitiosRepresentados.getSelectedItems().isEmpty()) {
			sitioVisible.append(((Classification) treeSitiosRepresentados
					.getSelectedItem().getValue()).getName());
			if (treeSitiosRepresentados.getSelectedItems().size() > 1) {
				sitioVisible.append(",...");
			}
		}
		cbxSitiosRepresentados.setValue(sitioVisible.toString());
	}

	@SuppressWarnings("unchecked")
	public void onClick$aCredits() {
		try {
			HashMap map = new HashMap<String, List<SegmentInformationType>>();
			map.put("credits", lstCreditos);
			map.put("parentWindow", winDescribirContenido);
			Window window = (Window) Executions.createComponents(
					"creditos.zul", null, map);
			window.doModal();
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	@SuppressWarnings("unchecked")
	public void onClick$btnAddAnnotation() {
		try {
//			HashMap map = new HashMap<String, List<SegmentInformationType>>();
//			map.put("parentWindow", winDescribirContenido);
//			Window window = (Window) Executions.createComponents(
//					"annotation.zul", null, map);
//			window.doModal();		
			if(txtAnnotation.getValue()!=null && !txtAnnotation.getValue().trim().isEmpty()){
				String annotation = txtAnnotation.getText();

				TextualType annotationTT = new TextualType();
				annotationTT.setValue(annotation);
				annotationTT.setLang("es");

				lstEduAnnotations.add(annotationTT);

				ListModelList annotationsModel = new ListModelList(lstEduAnnotations);
				annotationsModel.setMultiple(true);
				lstbxAnnotations.setModel(annotationsModel);
				lstbxAnnotations.setItemRenderer(this);	
				
				txtAnnotation.setText(null);
				lstbxAnnotations.setVisible(true);
			}else {
				Messagebox.show("No ha ingresado la nueva anotación.", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	@SuppressWarnings("unchecked")
	public void onClick$btnDeleteAnnotation() {
		try {
			if (!lstbxAnnotations.getSelectedItems().isEmpty()) {
				for (Listitem listitem : lstbxAnnotations.getSelectedItems()) {
					TextualType annotation = listitem.getValue();
					// ELIMINAR DEL ARCHIVO DESCRIPTOR
					lstEduAnnotations.remove(annotation);
				}

				// VOLVER A RENDERIZAR
				ListModelList annotationsModel = new ListModelList(
						lstEduAnnotations);
				annotationsModel.setMultiple(true);
				lstbxAnnotations.setModel(annotationsModel);
				lstbxAnnotations.setItemRenderer(this);
				
				if(lstEduAnnotations.isEmpty()){
					lstbxAnnotations.setVisible(false);
				}
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * Reconoce las coordenadas donde al usuario da click para ubicar la marca
	 * de tiempo
	 * 
	 * @param e
	 */
	/*
	 * public void onClick$divVideo(MouseEvent e) {
	 * divImgIniSegMovimiento.setVisible(true); StringBuilder style = new
	 * StringBuilder(64); style.append("position: absolute; top: ");
	 * style.append("140"); style.append("px; left: ");
	 * style.append(e.getPageX()); style.append("px;");
	 * divImgIniSegMovimiento.setStyle(style.toString());
	 * 
	 * // ------------COORDENADAS------------// //logger.info("Page X=" +
	 * e.getPageX() + " Page Y=" + e.getPageY()); }
	 */

	/**
	 * Evento que agrega un nuevo formulario para describir un segmento del
	 * recurso
	 * 
	 * @author Angela Vargas
	 */
	@SuppressWarnings("unchecked")
	public void onClick$btnAgregarSegmento() {
		try {
			if(!winSegmentIsOpen){
				HashMap map = new HashMap<String, List<SegmentInformationType>>();
				map.put("segmentos", segmentos);
				map.put("parentWindow", winDescribirContenido);
				map.put("generos", treeGenre.getSelectedItems());
				map.put("idiomas", localesEduLstSelect);
				Window window = (Window) Executions.createComponents(
						"segmento.zul", null, map);
				window.doOverlapped();//Modal();
				window.setLeft("200px");
				window.setTop("20px");
				winSegmentIsOpen = true;
				
				window.addEventListener(Events.ON_CLOSE, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						winSegmentIsOpen = false;
					}
				});
			}	
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * Elimina los segmentos seleccionados tanto de la interfaz gráfica
	 * (groupbox y gráfico) como del archivo descriptor
	 * 
	 * @author Angela Vargas
	 */
	@SuppressWarnings("unchecked")
	public void onClick$btnEliminarSegmento() {
		try {
			if (!lstbxSgmts.getSelectedItems().isEmpty()) {
				for (Listitem listitem : lstbxSgmts.getSelectedItems()) {
					SegmentInformationType sgmt = listitem.getValue();
					// ELIMINAR DEL ARCHIVO DESCRIPTOR
					segmentos.remove(sgmt);
					// ELIMINAR DEL GRÁFICO
					model.removeSeries(sgmt.getDescription().getTitle().get(0)
							.getValue());
				}

				// VOLVER A RENDERIZAR
				ListModelList segmentosModel = new ListModelList(segmentos);
				segmentosModel.setMultiple(true);
				lstbxSgmts.setModel(segmentosModel);
				lstbxSgmts.setItemRenderer(this);
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/*
	 * public void onTimer$tmpVerXML(){ if(TVAMainType!=null){
	 * 
	 * } }
	 */

	/**
	 * M&eacute;todo que se encarga de guardar la descripci&oacute;n del recurso
	 * cuando el usuario da clic en Terminar.
	 * 
	 * @author Angela Vargas
	 */
	@SuppressWarnings("unchecked")
	public void onClick$btnTerminar() {
		if(!winSegmentIsOpen){			
			// 1. VALIDAR QUE LOS CAMPOS INGRESADOR POR EL USUARIO SEAN DE TIPO ADECUADO
			String validador = validarCamposObligatorios();
			
			if(validador==null){
				Messagebox.show(
					"Su descripción del recurso se almacenará. ¿Desea continuar?",
					"Mensaje de confirmación", Messagebox.OK | Messagebox.NO,
					Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt) throws InterruptedException {
							if (evt.getName().equals("onOK")) {
								finishAnnotation(true);
							}
						}
					});
			}else {
				Messagebox.show(new StringBuilder("Los siguientes campos obligatorios no han sido ingresados: \n")
				.append(validador).append("\n¿Desea guardar esta descripción para terminarla después?").toString(),
					"Mensaje de confirmación", Messagebox.OK | Messagebox.NO,
					Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
						public void onEvent(Event evt) throws InterruptedException {
							if (evt.getName().equals("onOK")) {
								finishAnnotation(false);
							}
						}
					});
			}
//		Messagebox.show(
//				"Su descripción del recurso se almacenará. ¿Desea continuar?",
//				"Mensaje de confirmación", Messagebox.OK | Messagebox.NO,
//				Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
//					public void onEvent(Event evt) throws InterruptedException {
//						if (evt.getName().equals("onOK")) {
//							finishAnnotation();
//						}
//					}
//				});
		}
	}

	/*
	 * ------SECCIÓN MÉTODOS-----
	 */

	/**
	 * Método que carga el video correspondiente al recurso a marcar y
	 * seleccionado por el usuario.
	 */
	private void cargarRecursoPorMarcar() {
		try {
			rcrs = (Recurso) session
					.getAttribute(Constantes.RECURSO_POR_MARCAR);
			StringBuilder video = new StringBuilder();
			video.append("<video id=\"video\" width=\"500px\" height=\"300px\" controls=\"controls\" autoplay=\"autoplay\" ");
			video.append("poster=\"");
			video.append(vrblSstmNgc.obtenerVrblSstm(Constantes.URL_EDUTVA));
			video.append(Constantes.RUTA_LOGO_VIDEO);
			video.append("\" ><source src=\"");
			video.append(vrblSstmNgc
					.obtenerVrblSstm(Constantes.URL_VIDEOS_EDUTVA));
			video.append("/");
			video.append(rcrs.getArchvRecurso());
			video.append("\"");
			video.append(" type=\"video/mp4\"/>");
			video.append("<p>Tu navegador no soporta HTML5</p></video>");
			videoHtml.setContent(video.toString());
			logger.info(videoHtml.getContent());

			// Obtener el archivo descriptor por defecto para llenar campos predeterminados
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(new File(vrblSstmNgc
					.obtenerVrblSstm(Constantes.RUTA_EDUTVA_XSD)));

			JAXBContext jaxbContext = JAXBContext
					.newInstance(ExtendedTVAMainType.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			jaxbUnmarshaller.setSchema(schema);

			Source source = new StreamSource(new File(rcrs.getDscrpcnRecurso()));
			JAXBElement<TVAMainType> tvaMainTypeRoot = jaxbUnmarshaller
					.unmarshal(source, TVAMainType.class);
			TVAMainType tvaMainType = tvaMainTypeRoot.getValue();

			//------DESCRIPCIÒN GENERAL Y EDUCATIVA DEL RECURSO
			
			//BasicContentDescriptionType bDescriptionTypeGnrl = tvaMainType
			ExtendedContentDescriptionType bDescriptionTypeGnrl = (ExtendedContentDescriptionType) tvaMainType
					.getProgramDescription().getProgramInformationTable()
					.getProgramInformation().get(0).getBasicDescription();

			// título
			if(bDescriptionTypeGnrl.getTitle()!=null && !bDescriptionTypeGnrl.getTitle().isEmpty()){
				txtTitulo.setValue(bDescriptionTypeGnrl.getTitle().get(0)
						.getValue());	
			}
			
			// título corto
			if (bDescriptionTypeGnrl.getShortTitle() != null
					&& !bDescriptionTypeGnrl.getShortTitle().isEmpty()) {
				txtTituloCorto.setValue(bDescriptionTypeGnrl.getShortTitle()
						.get(0).getValue());
			}
			// sinopsis
			if(bDescriptionTypeGnrl.getSynopsis()!=null && !bDescriptionTypeGnrl.getSynopsis().isEmpty()){
				txtDescr.setValue(bDescriptionTypeGnrl.getSynopsis().get(0)
						.getValue());	
			}
			
			// palabras clave
			if(bDescriptionTypeGnrl.getKeyword()!=null &&!bDescriptionTypeGnrl.getKeyword().isEmpty()){
				int row = 0;
				int column = 0;
				for (KeywordType keywordType : bDescriptionTypeGnrl.getKeyword()) {
					((Textbox) gridPalabrasClave.getRows().getChildren().get(row)
							.getChildren().get(column)).setValue(keywordType
							.getValue());
					column++;
					if (column == 3) {
						row++;
						column = 0;
						nuevaFilaPalabrasClave(gridPalabrasClave);
					}
				}	
			}

			// género
			if(bDescriptionTypeGnrl.getGenre()!=null && !bDescriptionTypeGnrl.getGenre().isEmpty()){
				for (GenreType gType : bDescriptionTypeGnrl.getGenre()) {
					for (Treeitem treeitem : treeGenre.getItems()) {
						if (((Classification) treeitem.getValue())
								.getName()
								.trim()
								.equalsIgnoreCase(gType.getName().getValue().trim())) {
							treeitem.setSelected(true);
						}
					}
				}	
			}
			
			// idioma
			if (bDescriptionTypeGnrl.getLanguage() != null
					&& !bDescriptionTypeGnrl.getLanguage().isEmpty()) {
				for (ExtendedLanguageType languageType : bDescriptionTypeGnrl
						.getLanguage()) {
					for (Listitem listitem : lstbxIdiomas.getItems()) {
						if (((Locale) listitem.getValue()).toString()
								.equalsIgnoreCase(
										languageType.getValue().replace("-",
												"_"))) {
							listitem.setSelected(true);
							localesLstSelect.add((Locale) listitem.getValue());
						}
					}
				}
				onSelect$lstbxIdiomas();
			}

			// fecha de producción
			if (bDescriptionTypeGnrl.getProductionDate() != null) {
				String date = bDescriptionTypeGnrl.getProductionDate().getTimePoint().replace("T00:00:00+00:00", "");
				int year = Integer.valueOf(date.substring(0,4));
				int month = Integer.valueOf(date.substring(5, 7))-1;
				int day = Integer.valueOf(date.substring(8, 10));
				Calendar c = Calendar.getInstance();
				c.set(year, month, day);
				dbxFechaProd.setValue(c.getTime());
			}
			// país de producción
			if (bDescriptionTypeGnrl.getProductionLocation() != null
					&& !bDescriptionTypeGnrl.getProductionLocation().isEmpty()) {
				for (String codCountry : bDescriptionTypeGnrl.getProductionLocation()) {
					for (Listitem listitem : lstbxProdCountry.getItems()) {
						if (((CountryCode) listitem.getValue()).getAlpha2()
								.equalsIgnoreCase(codCountry)) {
							listitem.setSelected(true);
							countryLstSelect.add((CountryCode) listitem.getValue());
						}
					}
				}
				onSelect$lstbxProdCountry();
			}
			
			// lugares representados
			if (bDescriptionTypeGnrl.getDepictedCoordinates() != null
					&& !bDescriptionTypeGnrl.getDepictedCoordinates().isEmpty()
					&& bDescriptionTypeGnrl.getDepictedCoordinates().get(0)
							.getDepictedLocation() != null) {

				if (bDescriptionTypeGnrl.getDepictedCoordinates().get(0)
						.getDepictedLocation().getName() != null
						&& !bDescriptionTypeGnrl.getDepictedCoordinates()
								.get(0).getDepictedLocation().getName()
								.isEmpty()) {
					int rowL = 0;
					int columnL = 0;
					for (TextualType textualType : bDescriptionTypeGnrl
							.getDepictedCoordinates().get(0)
							.getDepictedLocation().getName()) {
						((Textbox) gridLugaresRepresentados.getRows()
								.getChildren().get(rowL).getChildren()
								.get(columnL)).setValue(textualType.getValue());
						columnL++;
						if (columnL == 3) {
							rowL++;
							columnL = 0;
							nuevaFilaPalabrasClave(gridLugaresRepresentados);
						}
					}
				}

				if (bDescriptionTypeGnrl.getDepictedCoordinates().get(0).getDepictedLocation().getNameTerm() != null
						&& !bDescriptionTypeGnrl.getDepictedCoordinates().get(0).getDepictedLocation().getNameTerm().isEmpty()) {
					// sitios representados
					for (ControlledTermUseType termType : bDescriptionTypeGnrl
							.getDepictedCoordinates().get(0)
							.getDepictedLocation().getNameTerm()) {
						for (Treeitem treeitem : treeSitiosRepresentados.getItems()) {
							if (((Classification) treeitem.getValue()).getTermID().trim().equalsIgnoreCase(termType.getHref().replace("urn:tva:metadata:extended:cs:PlaceTypeCS:2011:",""))) {
								treeitem.setSelected(true);
							}
						}
					}
					onSelect$treeSitiosRepresentados();
				}
			}

			// duración
			if (bDescriptionTypeGnrl.getDuration() != null) {
				ibxDurationH.setValue(bDescriptionTypeGnrl.getDuration()
						.getHours());
				ibxDurationM.setValue(bDescriptionTypeGnrl.getDuration()
						.getMinutes());
				ibxDurationS.setValue(bDescriptionTypeGnrl.getDuration()
						.getSeconds());
			}

			// créditos
			if(bDescriptionTypeGnrl.getCreditsList()!=null 
					&& bDescriptionTypeGnrl.getCreditsList().getCreditsItem()!=null 
					&& !bDescriptionTypeGnrl.getCreditsList().getCreditsItem().isEmpty()){
				lstCreditos = bDescriptionTypeGnrl.getCreditsList().getCreditsItem();	
			}
			
			
			// METADATOS EDUCATIVOS
			if(bDescriptionTypeGnrl.getContentProperties()!=null 
					&& bDescriptionTypeGnrl.getContentProperties().getContextAttributes()!=null
					&& !bDescriptionTypeGnrl.getContentProperties().getContextAttributes().isEmpty()){
			
				EducationalContextAttributesType eAttributesType = (EducationalContextAttributesType) bDescriptionTypeGnrl.getContentProperties().getContextAttributes().get(0);
				
				//-- EducationalResource --
				if(eAttributesType.getEducationalResource()!=null){
					EducationalResourceType educationalResourceType = eAttributesType.getEducationalResource();
					
					// tipo de interactividad
					if(educationalResourceType.getInteractivityType()!=null){
						for(Comboitem comboitem : cbxTipoInteractividad.getItems()){
							if(((Classification)comboitem.getValue()).getTermID().trim()
									.equalsIgnoreCase(((ControlledTermType)educationalResourceType.getInteractivityType()).getHref().replace("urn:tva:metadata:edutva:cs:InteractivityCS:2014:","").trim())){
								cbxTipoInteractividad.setSelectedItem(comboitem);
							}
						}
							
					}
					
					// tipo de recurso
					if(educationalResourceType.getEducationalType()!=null && !educationalResourceType.getEducationalType().isEmpty()){
						for(ControlledTermType controlledTermType : educationalResourceType.getEducationalType()){
							for(Listitem listitem : lstbxTipoRecurso.getItems()){
								if(((Classification)listitem.getValue()).getTermID().trim()
										.equalsIgnoreCase(controlledTermType.getHref().replace("urn:tva:metadata:extended:cs:EducationalUseCS:2005:","").trim())){
									listitem.setSelected(true);
									break;
								}	
							}
							
						}
						onSelect$lstbxTipoRecurso();
					}
					
					// uso educativo
					if(educationalResourceType.getEducationalUse()!=null && !educationalResourceType.getEducationalUse().isEmpty()){
						TextualType textualType = educationalResourceType.getEducationalUse().get(0);
						txtEducationalUse.setValue(textualType.getValue());
					}
				}
				
				//-- Educational Context --
				if(eAttributesType.getEducationalContext()!=null && !eAttributesType.getEducationalContext().isEmpty()){
					for(ControlledTermType controlledTermType : eAttributesType.getEducationalContext()){
						for(Listitem listitem : lstbxEducationalContext.getItems()){
							if(((Classification)listitem.getValue()).getTermID().trim()
									.equalsIgnoreCase(controlledTermType.getHref().replace("urn:tva:metadata:edutva:cs:EducationalContextCS:2015:","").trim())){
								listitem.setSelected(true);
								break;
							}
						}
					}
				}
				
				//-- Educational Audience --
				if(eAttributesType.getEducationalAudience()!=null){
					EducationalAudienceType audienceType = eAttributesType.getEducationalAudience();
					
					// rol del usuario objetivo
					if(audienceType.getEducationalRole()!=null && !audienceType.getEducationalRole().isEmpty()){
						for(ControlledTermType controlledTermType : audienceType.getEducationalRole()){
							for(Listitem listitem : lstbxEducationalRole.getItems()){
								if(((Classification)listitem.getValue()).getTermID().trim()
										.equalsIgnoreCase(controlledTermType.getHref().replace("urn:tva:metadata:extended:cs:IntendedEducationalUserCS:2011:", "").trim())){
									listitem.setSelected(true);
									break;
								}
							}
						}
						onSelect$lstbxEducationalRole();
					}
					
					// rango de edad típico
					if(audienceType.getTypicalAgeRange()!=null && !audienceType.getTypicalAgeRange().isEmpty()){
						for(ControlledTermType controlledTermType : audienceType.getTypicalAgeRange()){
							for(Treeitem treeitem : treeAgeRange.getItems()){
								if(((Classification)treeitem.getValue()).getTermID().trim()
										.equalsIgnoreCase(controlledTermType.getHref().replace("urn:tva:metadata:edutva:cs:AgeRangeCS:2015:", "").trim())){
									treeitem.setSelected(true);
									break;
								}
							}
						}
					}
					
					// idioma		TODO			
					if (audienceType.getLanguage()!=null && !audienceType.getLanguage().isEmpty()) {
						for (LanguageType languageType : audienceType.getLanguage()) {
							for (Listitem listitem : lstbxEduIdiomas.getItems()) {
								if (((Locale) listitem.getValue()).toString()
										.equalsIgnoreCase(
												languageType.getValue().replace("-",
														"_"))) {
									listitem.setSelected(true);
									localesEduLstSelect.add((Locale) listitem.getValue());
								}
							}
						}
						onSelect$lstbxEduIdiomas();
					}	
					
				}
				
				//-- Anotaciones --
				if(eAttributesType.getAnnotation()!=null && !eAttributesType.getAnnotation().isEmpty()){
					lstEduAnnotations.addAll(eAttributesType.getAnnotation());
					ListModelList annotationsModel = new ListModelList(lstEduAnnotations);
					annotationsModel.setMultiple(true);
					lstbxAnnotations.setModel(annotationsModel);
					lstbxAnnotations.setItemRenderer(this);	
					lstbxAnnotations.setVisible(true);
				}

				//-- Resultados --
				if(eAttributesType.getEducationalResults()!=null){
					EducationalResultsType educationalResultsType = eAttributesType.getEducationalResults();
					
					// competencias	
					if(educationalResultsType.getAbility()!=null && !educationalResultsType.getAbility().isEmpty()){
						for(ControlledTermType controlledTermType : educationalResultsType.getAbility()){
							for(Treeitem treeitem : treeAbility.getItems()){
								if(((Classification)treeitem.getValue()).getTermID().trim()
										.equalsIgnoreCase(controlledTermType.getHref().replace("urn:tva:metadata:edutva:cs:AbilityCS:2014:", "").trim())){
									treeitem.setSelected(true);
									break;
								}
							}
						}
					}		
				}	
			}
			
			//SEGMENTOS EDUCATIVOS DEL RECURSO
			SegmentInformationTableType segmentInformationTableType = tvaMainType.getProgramDescription().getSegmentInformationTable();
			if(segmentInformationTableType.getSegmentList()!=null && 
					segmentInformationTableType.getSegmentList().getSegmentInformation()!=null &&
					!segmentInformationTableType.getSegmentList().getSegmentInformation().isEmpty()){		
					segmentos.addAll(segmentInformationTableType.getSegmentList().getSegmentInformation());
					ListModelList segmentosModel = new ListModelList(segmentos);
					segmentosModel.setMultiple(true);
					lstbxSgmts.setModel(segmentosModel);
					lstbxSgmts.setItemRenderer(this);
			
			}
			
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	@SuppressWarnings("unchecked")
	public void renderIdiomasTmp(String filter, Listbox lstbx) {
		List<Locale> localesLstTmp = new ArrayList<Locale>();
   
        for (Iterator<Locale> i = localesLst.iterator(); i.hasNext();) {
        	Locale tmp = i.next();
            if (tmp.getDisplayName().toLowerCase().contains(filter)) {
            	localesLstTmp.add(tmp);
            }
        }
        
        localesLstTmp.sort(new LocaleComparator(true, 1));

		ListModelList idiomasModel = new ListModelList(localesLstTmp);
		idiomasModel.setMultiple(true);
		lstbx.setModel(idiomasModel);
		lstbx.setItemRenderer(this);
		lstbx.renderAll();
	}
	
	/**
	 * M&eacute;todo que se encarga de guardar la descripci&oacute;n del
	 * recurso.
	 */
	@SuppressWarnings("unchecked")
	private void finishAnnotation(boolean isDscrpcnFnl) {
		try {
			// 1. VALIDAR QUE LOS CAMPOS INGRESADOR POR EL USUARIO SEAN DE TIPO
			// ADECUADO
			//String validador = validarCamposObligatorios();

			
//			if (validador == null) {
				// 2. OBTENER INFORMACIÓN DEL CREADOR DE LOS METADATOS
				MetadataOriginationInformationTableType mInformationTableType = getMetadataOriginationTable();

				// 3. OBTENER LOS ESQUEMAS DE CLASIFICACIÓN UTILIZADOS
				ClassificationSchemeTableType cSchemeTableType = getClassificationSchemeTable();

				// 4. OBTENER LA DESCRIPCIÓN DEL PROGRAMA
				ProgramDescriptionType programDescriptionType = getProgramDescription();

				// 5.  EL ELEMENTO RAIZ DEL ARCHIVO DESCRIPTOR
				TVAMainType TVAMainType = new TVAMainType();

				// Par&aacute;metros generales del elemento principal
				TVAMainType.setPublisher("EduTVAcompany");
				TVAMainType.setLang("es");

				// 6. ASIGNACIÓN DE LA INFORMACIÓN DEL CREADOR DE LOS METADATOS
				// AL ELEMENTO RAIZ
				TVAMainType
						.setMetadataOriginationInformationTable(mInformationTableType);

				// 7. ASIGNACIÓN DE LOS ESQUEMAS DE CLASIFICACIÓN UTILIZADOS AL
				// ELEMENTO RAIZ
				TVAMainType.setClassificationSchemeTable(cSchemeTableType);

				// 8. ASIGNACIÓN DE LA DESCRIPCIÓN DEL PROGRAMA AL ELEMENTO RAIZ
				TVAMainType.setProgramDescription(programDescriptionType);

				// 9. MARSHALLER DE LA DESCRIPCIÓN (CREACIÓN DEL XML)
				JAXBContext jaxbContext = JAXBContext
						.newInstance(ExtendedTVAMainType.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
						true);
				ObjectFactory objectFactory = new ObjectFactory();
				JAXBElement<TVAMainType> je = objectFactory
						.createTVAMain(TVAMainType);

				// jaxbMarshaller.marshal(je, System.out);

				// COD TEMPO: Descargo el archivo descriptor para visualizar el
				// resultado
				final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				jaxbMarshaller.marshal(je, outputStream);

				// Guardar archivo descriptor en servidor
				final StringBuilder fileName = new StringBuilder();
				fileName.append(vrblSstmNgc
						.obtenerVrblSstm(Constantes.URL_DSCRPCN));
				fileName.append(rcrs.getNmbrRecurso()).append("_");
				fileName.append(((Usuario) session
						.getAttribute(Constantes.USUARIO)).getUsuario());
				fileName.append(".xml");

				File fileAnnotation = new File(fileName.toString());
				FileOutputStream fop = new FileOutputStream(fileAnnotation);
				// if file doesnt exists, then create it
				if (!fileAnnotation.exists()) {
					fileAnnotation.createNewFile();
				}
				fop.write(outputStream.toByteArray());
				fop.flush();
				fop.close();

				// Guardar relación del archivo con el usuario
				resourceNgc.saveResourceDescription(
						(Usuario) session.getAttribute(Constantes.USUARIO),
						rcrs, fileName.toString(),isDscrpcnFnl);

				Messagebox
						.show("La descripción se ha almacenado exitosamente. ¿Desea descargar el archivo descriptor del recurso?",
								"Mensaje de confirmación", Messagebox.OK
										| Messagebox.NO, Messagebox.QUESTION,
								new org.zkoss.zk.ui.event.EventListener() {
									public void onEvent(Event evt)
											throws InterruptedException {
										if (evt.getName().equals("onOK")) {
											Filedownload.save(
													outputStream.toByteArray(),
													"application/xml",
													fileName.toString());
										}
									}
								});
				Window window = (Window) Executions.createComponents(
						"encuesta.zul", null, null);
				window.doModal();

				limpiarFormulario();
				self.detach();
				Events.sendEvent(new Event("onFinishAnnotation",
						(Window) session.getAttribute("parent"), null));
//			} else {
//				Messagebox
//						.show(new StringBuilder()
//								.append("Su descripción del recurso NO ha sido almacenada porque los siguientes campos obligatorios no han sido ingresados: \n")
//								.append(validador).toString(), "Error",
//								Messagebox.OK, Messagebox.ERROR);
//			}
		} catch (MarshalException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		} catch (JAXBException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}

	/**
	 * Método que obtiene los esquemas de clasificación utilizados por la
	 * herramienta desde el directorio que los aloja y los agrega a la tabla de
	 * esquemas de clasificación.
	 * 
	 * @return ClassificationSchemeTableType cSchemeTableType
	 * @throws Exception
	 * @author Angela Vargas
	 */
	private ClassificationSchemeTableType getClassificationSchemeTable()
			throws Exception {
		ClassificationSchemeTableType cSchemeTableType = new ClassificationSchemeTableType();
		cSchemeTableType.setLang("es");
		File classificationSchemesDir = new File(
				vrblSstmNgc
						.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES));
		File classificationSchemes[] = classificationSchemesDir.listFiles();

		for (File fileCS : classificationSchemes) {
			if (fileCS
					.getName()
					.substring(fileCS.getName().length() - 4,
							fileCS.getName().length()).equalsIgnoreCase(".xml")) {
				XMLInputFactory xif = XMLInputFactory.newFactory();
				StreamSource xml = new StreamSource(fileCS);
				XMLStreamReader xsr = xif.createXMLStreamReader(xml);
				while (xsr.hasNext()) {
					if (xsr.isStartElement()
							&& xsr.getLocalName()
									.equals("ClassificationScheme")) {
						break;
					}
					xsr.next();
				}

				JAXBContext jc = JAXBContext
						.newInstance(TVAClassificationSchemeType.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				JAXBElement<TVAClassificationSchemeType> jb = unmarshaller
						.unmarshal(xsr, TVAClassificationSchemeType.class);
				TVAClassificationSchemeType tvaClassificationSchemeType = jb
						.getValue();
				cSchemeTableType.getClassificationScheme().add(
						tvaClassificationSchemeType);

				xsr.close();
				// Marshaller marshaller = jc.createMarshaller();
				// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
				// true);
				// marshaller.marshal(jb, System.out);
			}
		}
		return cSchemeTableType;
	}

	/**
	 * 
	 * @return
	 */
	private MetadataOriginationInformationTableType getMetadataOriginationTable() {

		// Editor de los metadatos.
		MetadataOriginationInformationTableType mOriginationInformationTableType = new MetadataOriginationInformationTableType();
		// Solo hay una persona que realiza la marcación en este prototipo:
		MetadataOriginationInformationType metaOriginInf = new MetadataOriginationInformationType();
		// ID obligatorio, utilido el identificador único que ha creado java
		// para el objeto
		metaOriginInf.setOriginID(String.valueOf(metaOriginInf.hashCode()));
		// Solo se agregará la informació relacionada con el usuario.
		StringBuilder nmbrUsuario = new StringBuilder();
		nmbrUsuario.append(
				((Usuario) session.getAttribute(Constantes.USUARIO))
						.getNmbrUsuario()).append(" ");
		nmbrUsuario.append(((Usuario) session.getAttribute(Constantes.USUARIO))
				.getAplldUsuario());
		TextualType publisherTT = new TextualType();
		publisherTT.setValue(nmbrUsuario.toString());
		metaOriginInf.getPublisher().add(publisherTT);
		// Propietario de los derechos de los metadatos
		TextualType rightsOwnerTT = new TextualType();
		rightsOwnerTT.setValue("Universidad del Cauca");
		metaOriginInf.getRightsOwner().add(rightsOwnerTT);
		// Información de copyright
		TextualType copyrightTT = new TextualType();
		copyrightTT.setValue("Con libertad de distribución de forma gratuita");
		metaOriginInf.getCopyrightNotice().add(copyrightTT);

		mOriginationInformationTableType.getMetadataOriginationInformation()
				.add(metaOriginInf);
		return mOriginationInformationTableType;
	}

	/**
	 * M&eacute;todo que obtiene los par&aacute;metros ingresador por el usuario
	 * y crea la descripci&oacute;n del recurso
	 * 
	 * @author Angela Vargas
	 * @return ProgramDescriptionType proDescriptionType
	 */
	private ProgramDescriptionType getProgramDescription() throws Exception {
		/*
		 * UTILIZADOS: ProgramInformationTable SegmentInformationTable
		 * 
		 * OPCIONALES: GroupInformationTable CreditsInformationTable
		 * 
		 * ELIMINADOS: ProgramLocatorTable, ServiceInformationTable,
		 * ProgramReviewTable, PurchaseInformationTable
		 */

		// Instancia de ProgramDescriptionType
		ProgramDescriptionType proDescriptionType = new ProgramDescriptionType();

		// ------a. ProgramInformationTable------
		proDescriptionType
				.setProgramInformationTable(getProgramInformationTable());

		// ------b. SegmentInformationTable------
		proDescriptionType
				.setSegmentInformationTable(getSegmentInformationTable());

		return proDescriptionType;
	}

	/**
	 * 
	 * @return
	 */
	private SegmentInformationTableType getSegmentInformationTable() {
		SegmentInformationTableType sInformationTableType = new SegmentInformationTableType();
		SegmentListType segmentListType = new SegmentListType();
		segmentListType.getSegmentInformation().addAll(segmentos);
		sInformationTableType.setSegmentList(segmentListType);
		return sInformationTableType;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private ProgramInformationTableType getProgramInformationTable()
			throws Exception {

		// Instancia de ProgramInformationTableType
		ProgramInformationTableType proInformationTableType = new ProgramInformationTableType();

		// Instancia de ProgramInformationType y asignaci�n de la descripci�n
		// del contenido.
		// Solo manejar� una instancia... pueden ser varios tiposde informaci�n.
		ProgramInformationType proInformationType = new ProgramInformationType();

		// Identificador del programa (su estructura está acorde a The
		// TV-Anytime Content Reference Identifier (CRID)- rfc4078)
		// El CRID que aquí se establece solo se utilizará en un ambiente
		// educativo y por lo tanto no se registra
		StringBuilder crid = new StringBuilder("crid://");
		crid.append(vrblSstmNgc.obtenerVrblSstm(Constantes.CRID_EDUTVA));
		crid.append(rcrs.getIdRecurso());

		proInformationType.setProgramId(crid.toString());
		proInformationType.setBasicDescription(getContentDescription());
		proInformationType.setAVAttributes(getAVAttributes());

		// Asignaci�n de la informaci�n obtenida
		proInformationTableType.getProgramInformation().add(proInformationType);

		return proInformationTableType;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 **/
	private ExtendedContentDescriptionType getContentDescription()
			throws Exception {
		// 1. OBTENER LA DESCRIPCIÓN BÁSICA DEL RECURSO (versión extendida TVA2)
		ExtendedContentDescriptionType basicContentDescriptionType = new ExtendedContentDescriptionType();

		// Título ingresado por el usuario
		if (!txtTitulo.getValue().trim().isEmpty()) {
			TitleType titleType = new TitleType();
			titleType.setValue(txtTitulo.getValue().trim());
			basicContentDescriptionType.getTitle().add(titleType);	
		}

		// Versión corta del título
		if (!txtTituloCorto.getValue().trim().isEmpty()) {
			ShortTitleType shortTitleType = new ShortTitleType();
			shortTitleType.setLength(txtTituloCorto.getValue().trim().length());
			shortTitleType.setValue(txtTituloCorto.getValue().trim());
			basicContentDescriptionType.getShortTitle().add(shortTitleType);
		}

		// Descripción o resumen del recurso, ingresado por el usuario (synopsis)
		if (!txtDescr.getValue().trim().isEmpty()) {
			SynopsisType synopsisType = new SynopsisType();
			synopsisType.setValue(txtDescr.getValue().trim());
			basicContentDescriptionType.getSynopsis().add(synopsisType);
		}
		
		// Palabras clave
		for (Component rowKey : gridPalabrasClave.getRows().getChildren()) {
			for (Component txbKey : rowKey.getChildren()) {
				if (!((Textbox) txbKey).getValue().isEmpty()) {
					KeywordType keywordType = new KeywordType();
					keywordType.setValue(((Textbox) txbKey).getValue());
					if (basicContentDescriptionType.getKeyword().isEmpty()) {
						keywordType.setType(Constantes.MAIN_KEYWORD);
					} else if (basicContentDescriptionType.getKeyword().size() == 1) {
						keywordType.setType(Constantes.SECOND_KEYWORD);
					} else {
						keywordType.setType(Constantes.OTHER_KEYWORD);
					}
					basicContentDescriptionType.getKeyword().add(keywordType);
				}
			}
		}

		// Género
		if (treeGenre.getSelectedItems() != null && !treeGenre
				.getSelectedItems().isEmpty()) {
			Set<Treeitem> genreSelectedItems = (Set<Treeitem>) treeGenre.getSelectedItems();
			for (Treeitem item : genreSelectedItems) {
				GenreType newGenre = new GenreType();
				StringBuilder href = new StringBuilder(64)
						.append("urn:tva:metadata:cs:ContentCS:2011:");
				href.append(((Classification) item.getValue()).getTermID());
				newGenre.setHref(href.toString());
				TermNameType genreName = new TermNameType();
				genreName.setValue(((Classification) item.getValue()).getName());
				newGenre.setName(genreName);
				basicContentDescriptionType.getGenre().add(newGenre);
			}
		}

		// Idiomas
		/*
		 * ExtendedLaguageType extiende de language (MPEG-7) <xs:simpleType
		 * name="language" id="language"> <xs:annotation> <xs:documentation
		 * source="http://www.w3.org/TR/xmlschema-2/#language"/>
		 * </xs:annotation> <xs:restriction base="xs:token"> <xs:pattern
		 * value="[a-zA-Z]{1,8}(-[a-zA-Z0-9]{1,8})*" id="language.pattern">
		 * <xs:annotation> <xs:documentation
		 * source="http://www.ietf.org/rfc/rfc3066.txt"> pattern specifies the
		 * content of section 2.12 of XML 1.0e2 and RFC 3066 (Revised version of
		 * RFC 1766). </xs:documentation> </xs:annotation> </xs:pattern>
		 * </xs:restriction> </xs:simpleType>
		 */
		// http://www.iana.org/assignments/language-subtag-registry/language-subtag-registry
		/*if (lstbxIdiomas.getSelectedItems() != null
				&& !lstbxIdiomas.getSelectedItems().isEmpty()) {
			for (Listitem item : lstbxIdiomas.getSelectedItems()) {
				ExtendedLanguageType languaje = new ExtendedLanguageType();
				languaje.setValue(((Locale) item.getValue()).toString()
						.replace("_", "-"));
				basicContentDescriptionType.getLanguage().add(languaje);
			}
		}*/
		if (localesLstSelect != null
				&& !localesLstSelect.isEmpty()) {
			for (Locale locale : localesLstSelect) {
				ExtendedLanguageType languaje = new ExtendedLanguageType();
				languaje.setValue(locale.toString()
						.replace("_", "-"));
				basicContentDescriptionType.getLanguage().add(languaje);
			}
		}

		// Fecha de producción
		if (dbxFechaProd.getValue() != null) {
			TVATimeType fechaProd = new TVATimeType();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			StringBuilder dateFormat = new StringBuilder(sdf.format(dbxFechaProd.getValue())).append("T00:00:00+00:00");
			fechaProd.setTimePoint(dateFormat.toString());
			basicContentDescriptionType.setProductionDate(fechaProd);
		}

		// Lugar de producción
		/*if (lstbxProdCountry.getSelectedItems() != null
				&& !lstbxProdCountry.getSelectedItems().isEmpty()) {
			for (Listitem item : lstbxProdCountry.getSelectedItems()) {
				basicContentDescriptionType.getProductionLocation().add(
						((CountryCode)item.getValue()).getAlpha2());
			}
		}*/
		if (countryLstSelect != null
				&& !countryLstSelect.isEmpty()) {
			for (CountryCode country : countryLstSelect) {
				basicContentDescriptionType.getProductionLocation().add(country.getAlpha2());
			}
		}

		// Depicted coordinates (Lugar y época representada)
		DepictedCoordinatesType depictedCoordinatesType = new DepictedCoordinatesType();

		// Lugares representados
		TVAPlaceType placeType = new TVAPlaceType();
		for (Component rowPlace : gridLugaresRepresentados.getRows()
				.getChildren()) {
			for (Component txbPlace : rowPlace.getChildren()) {
				if (!((Textbox) txbPlace).getValue().isEmpty()) {
					TextualType lugar = new TextualType();
					lugar.setValue(((Textbox) txbPlace).getValue());
					placeType.getName().add(lugar);
				}
			}
		}
		// Sitios representados
		if (treeSitiosRepresentados.getSelectedItems() != null
				&& !treeSitiosRepresentados.getSelectedItems().isEmpty()) {
			for (Treeitem item : treeSitiosRepresentados.getSelectedItems()) {
				ControlledTermUseType controlledTermUseType = new ControlledTermUseType();
				StringBuilder href = new StringBuilder(64)
						.append("urn:tva:metadata:extended:cs:PlaceTypeCS:2011:");
				href.append(((Classification) item.getValue()).getTermID());
				controlledTermUseType.setHref(href.toString());
				placeType.getNameTerm().add(controlledTermUseType);
			}
		}

		// Agrego las ubicaciones representadas solo si han sido ingresadas
		if ((placeType.getName() != null && !placeType.getName().isEmpty())
				|| (placeType.getNameTerm() != null && !placeType.getNameTerm()
						.isEmpty())) {
			depictedCoordinatesType.setDepictedLocation(placeType);
		}

		// Epoca representada
//		if (!txtEpocaRepresentada.getValue().isEmpty()) {
//			TVATimeType epoca = new TVATimeType();
//			epoca.setTimePoint(txtEpocaRepresentada.getValue());
//			depictedCoordinatesType.setDepictedDate(epoca);
//		}

		// Agrego época y ubicación representada solo si han sido ingresadas
		if (depictedCoordinatesType.getDepictedDate() != null
				|| depictedCoordinatesType.getDepictedLocation() != null) {
			basicContentDescriptionType.getDepictedCoordinates().add(
					depictedCoordinatesType);
		}

		// Duración
		java.time.Duration jduration = java.time.Duration.ZERO;
		if (ibxDurationH.getValue() != null) {
			jduration = jduration.plus(java.time.Duration.ofHours(ibxDurationH
					.getValue().longValue()));
		}
		if (ibxDurationM.getValue() != null) {
			jduration = jduration.plus(java.time.Duration
					.ofMinutes(ibxDurationM.getValue().longValue()));
		}
		if (ibxDurationS.getValue() != null) {
			jduration = jduration.plus(java.time.Duration
					.ofSeconds(ibxDurationS.getValue().longValue()));
		}

		if (!jduration.isZero()) {
			Duration duration = DatatypeFactory.newInstance().newDuration(
					jduration.toString());
			basicContentDescriptionType.setDuration(duration);
		}

		// Créditos
		if (lstCreditos != null && !lstCreditos.isEmpty()) {
			CreditsListType creditos = new CreditsListType();
			creditos.getCreditsItem().addAll(lstCreditos);
			basicContentDescriptionType.setCreditsList(creditos);
		}
		// 2. LA INFORMACIÓN EDUCATIVA ES UN ATRIBUTO DE CONTEXTO QUE HACE PARTE
		// DE LAS PROPIEDADES DEL CONTENIDO
		ContentPropertiesType contentPropertiesType = new ContentPropertiesType();
		contentPropertiesType.getContextAttributes().add(infoEducativa());

		// 3. LAS PROPIEDADES DEL CONTENIDO HACEN PARTE DE LA DESCRIPCIÓN BÁSICA
		// DEL CONTENIDO (EN LA VERSIÓN EXTENDIDA)
		// extiende de tva:BasicContentDescriptionType
		basicContentDescriptionType.setContentProperties(contentPropertiesType);
		return basicContentDescriptionType;
	}

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private AVAttributesType getAVAttributes() throws Exception {
		// Se proporcionarán los metadatos básicos
		AVAttributesType avAttributesType = new AVAttributesType();

		// Formato del archivo AV
		StringBuilder pathAVresource = new StringBuilder(rcrs.getRtaRecurso());
		pathAVresource.append(rcrs.getArchvRecurso());
		File fileAVresource = new File(pathAVresource.toString());
		String ext = FilenameUtils.getExtension(pathAVresource.toString());

		LectorCS lector = new LectorCS(); // Lector de classification schemes
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
				.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
				.append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_FILE_FORMAT))
				.toString());
		for (Classification classification : lector.getElements()) {
			if (ext.trim().equalsIgnoreCase(classification.getName().trim())) {
				ControlledTermType fileFormatCT = new ControlledTermType();
				StringBuilder href = new StringBuilder(lector.getUri())
						.append(":");
				href.append(classification.getTermID());
				fileFormatCT.setHref(href.toString());
				TermNameType nmbFileFormat = new TermNameType();
				nmbFileFormat.setValue(classification.getName());
				fileFormatCT.setName(nmbFileFormat);
				avAttributesType.setFileFormat(fileFormatCT);
				break;
			}
		}
		// Tamaño en bytes
		BigInteger fileSize = new BigInteger(String.valueOf(fileAVresource
				.length()));
		avAttributesType.setFileSize(fileSize);

		return avAttributesType;
	}

	/**
	 * Método que obtiene los metadatos educativos ingresados por el usuario
	 * 
	 * @author Angela Vargas
	 * @return EducationalContextAttributesType eAttributesType
	 */
	private EducationalContextAttributesType infoEducativa()
			throws Exception {
		/*
		 * EJEMPLO PARA: EducationalUse: <Term termID="2"> <Name
		 * xml:lang="en">Animation</Name> </Term>
		 * 
		 * IntendedUser: <Term termID="1"> <Name xml:lang="en">Teachers</Name>
		 * </Term>
		 */

		EducationalContextAttributesType eAttributesType = new EducationalContextAttributesType();

		// Lector de classification schemes
		LectorCS lector = new LectorCS();

		// Instancia de EducationalResourceType para agregar los metadatos
		// relacionados con el recurso educativo
		EducationalResourceType educationalResourceType = new EducationalResourceType();
		eAttributesType.setEducationalResource(educationalResourceType);

		// PARA AGREGAR EL TIPO DE INTERACTIVIDAD
		// if(interactividad.getSelectedItem()!=null){
		if (cbxTipoInteractividad.getSelectedItem() != null) {
			ControlledTermType interactivityTypeCT = new ControlledTermType();
			lector.setClassificationScheme(new StringBuilder(vrblSstmNgc
					.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
					.append(vrblSstmNgc
							.obtenerVrblSstm(Constantes.CS_INTERACTIVITY_TYPE))
					.toString());
			StringBuilder href = new StringBuilder(lector.getUri()).append(":");
			href.append(((Classification) cbxTipoInteractividad
					.getSelectedItem().getValue()).getTermID());
			interactivityTypeCT.setHref(href.toString());
			TermNameType nmbInteractivity = new TermNameType();
			nmbInteractivity.setValue(((Classification) cbxTipoInteractividad
					.getSelectedItem().getValue()).getName());
			interactivityTypeCT.setName(nmbInteractivity);
			// eAttributesType.setInteractivityType(interactivityTypeCT);
			educationalResourceType.setInteractivityType(interactivityTypeCT);
		}

		// PARA AGREGAR EL TIPO DE RECURSO
		if (lstbxTipoRecurso.getSelectedItems() != null
				&& !lstbxTipoRecurso.getSelectedItems().isEmpty()) {
			for (Listitem item : lstbxTipoRecurso.getSelectedItems()) {
				ControlledTermType educationalUseCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(
						vrblSstmNgc
								.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
						.append(vrblSstmNgc
								.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_USE))
						.toString());
				StringBuilder href = new StringBuilder(lector.getUri())
						.append(":");
				href.append(((Classification) item.getValue()).getTermID());
				educationalUseCT.setHref(href.toString());
				TermNameType nmbEducationalUse = new TermNameType();
				nmbEducationalUse.setValue(((Classification) item.getValue())
						.getName());
				educationalUseCT.setName(nmbEducationalUse);
				// eAttributesType.getEducationalType().add(educationalUseCT);
				educationalResourceType.getEducationalType().add(
						educationalUseCT);
			}
		}

		// PARA AGREGAR EL USO EDUCATIVO
		if (!txtEducationalUse.getValue().trim().isEmpty()) {
			TextualType educationalUseTT = new TextualType();
			educationalUseTT.setValue(txtEducationalUse.getValue().trim());
			educationalUseTT.setLang("es");
			educationalResourceType.getEducationalUse().add(educationalUseTT);
		}

		// PARA AGREGAR EL CONTEXTO EDUCATIVO
		// if(treeEducationalContext.getSelectedItems()!=null &&
		// !treeEducationalContext.getSelectedItems().isEmpty()){
		if (lstbxEducationalContext.getSelectedItems() != null
				&& !lstbxEducationalContext.getSelectedItems().isEmpty()) {
			// for(Treeitem item : treeEducationalContext.getSelectedItems()){
			for (Listitem item : lstbxEducationalContext.getSelectedItems()) {
				ControlledTermType eduContextCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(
						vrblSstmNgc
								.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
						.append(vrblSstmNgc
								.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_CONTEXT))
						.toString());
				StringBuilder href = new StringBuilder(lector.getUri())
						.append(":");
				href.append(((Classification) item.getValue()).getTermID());
				eduContextCT.setHref(href.toString());
				TermNameType nmbContext = new TermNameType();
				nmbContext.setValue(((Classification) item.getValue())
						.getName());
				eduContextCT.setName(nmbContext);
				eAttributesType.getEducationalContext().add(eduContextCT);
			}
		}

		// Instancia de EducationalAudienceType para agregar los metadatos
		// relacionados con la audiencia educativa
		EducationalAudienceType educationalAudienceType = new EducationalAudienceType();
		eAttributesType.setEducationalAudience(educationalAudienceType);

		// PARA AGREGAR EL ROL DEL USUARIO
		if (lstbxEducationalRole.getSelectedItems() != null
				&& !lstbxEducationalRole.getSelectedItems().isEmpty()) {
			for (Listitem item : lstbxEducationalRole.getSelectedItems()) {
				ControlledTermType educationalRoleCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(
						vrblSstmNgc
								.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
						.append(vrblSstmNgc
								.obtenerVrblSstm(Constantes.CS_INTENDED_EDUCATIONAL_USER))
						.toString());
				StringBuilder href = new StringBuilder(lector.getUri())
						.append(":");
				href.append(((Classification) item.getValue()).getTermID());
				educationalRoleCT.setHref(href.toString());
				TermNameType nmbEducationalRole = new TermNameType();
				nmbEducationalRole.setValue(((Classification) item.getValue())
						.getName());
				educationalRoleCT.setName(nmbEducationalRole);
				educationalAudienceType.getEducationalRole().add(
						educationalRoleCT);
			}
		}

		// PARA AGREGAR EL RANGO DE EDAD TÍPICO
		if (treeAgeRange.getSelectedItems() != null
				&& !treeAgeRange.getSelectedItems().isEmpty()) {
			for (Treeitem item : treeAgeRange.getSelectedItems()) {
				ControlledTermType ageRangeCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(
						vrblSstmNgc
								.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
						.append(vrblSstmNgc
								.obtenerVrblSstm(Constantes.CS_AGE_RANGE))
						.toString());
				StringBuilder href = new StringBuilder(lector.getUri())
						.append(":");
				href.append(((Classification) item.getValue()).getTermID());
				ageRangeCT.setHref(href.toString());
				TermNameType nmbAgeRange = new TermNameType();
				nmbAgeRange.setValue(((Classification) item.getValue())
						.getName());
				ageRangeCT.setName(nmbAgeRange);
				educationalAudienceType.getTypicalAgeRange().add(ageRangeCT);
			}
		}

		// PARA AGREGAR EL IDIOMA DEL USUARIO OBJETIVO
		/*if (lstbxEduIdiomas.getSelectedItems() != null
				&& !lstbxEduIdiomas.getSelectedItems().isEmpty()) {
			for (Listitem item : lstbxEduIdiomas.getSelectedItems()) {
				LanguageType languaje = new LanguageType();
				languaje.setValue(((Locale) item.getValue()).toString()
						.replace("_", "-"));
				educationalAudienceType.getLanguage().add(languaje);
			}
		}*/
		if (localesEduLstSelect != null
				&& !localesEduLstSelect.isEmpty()) {
			for (Locale locale : localesEduLstSelect) {
				LanguageType languaje = new LanguageType();
				languaje.setValue(locale.toString()
						.replace("_", "-"));
				educationalAudienceType.getLanguage().add(languaje);
			}
		}

		// PARA AGREGAR LA DESCRIPCIÓN EDUCATIVA (anotaciones)
		if (lstEduAnnotations != null && !lstEduAnnotations.isEmpty()) {
			eAttributesType.getAnnotation().addAll(lstEduAnnotations);
		}

		// Instancia de EducationalResultsType para agregar los metadatos
		// relacionados con la audiencia educativa
		EducationalResultsType educationalResultsType = new EducationalResultsType();
		eAttributesType.setEducationalResults(educationalResultsType);

		// PARA AGREGAR LAS HABILIDADES
		if (treeAbility.getSelectedItems() != null
				&& !treeAbility.getSelectedItems().isEmpty()) {
			for (Treeitem item : treeAbility.getSelectedItems()) {
				ControlledTermType abilityCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(
						vrblSstmNgc
								.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES))
						.append(vrblSstmNgc
								.obtenerVrblSstm(Constantes.CS_ABILITY))
						.toString());
				StringBuilder href = new StringBuilder(lector.getUri())
						.append(":");
				href.append(((Classification) item.getValue()).getTermID());
				abilityCT.setHref(href.toString());
				TermNameType nmbAbility = new TermNameType();
				nmbAbility.setValue(((Classification) item.getValue())
						.getName());
				abilityCT.setName(nmbAbility);
				educationalResultsType.getAbility().add(abilityCT);
			}
		}

		return eAttributesType;
	}

	/**
	 * M&eacute;todo que valida que los campos obligatorios sean ingresados por
	 * el usuario
	 * 
	 * @author Angela Vargas
	 * @return
	 */
	private String validarCamposObligatorios() {
		boolean validador = true;
		boolean validadorGnr = true;
		boolean validadorCrcEdu = true;
		StringBuilder campos = new StringBuilder();

		/*
		 * Valores obligatorios en descripción general del recurso
		 */
		StringBuilder camposDscrpGenr = new StringBuilder();
		// Título obligatorio
		if (txtTitulo.getValue() == null
				|| txtTitulo.getValue().trim().isEmpty()) {
			camposDscrpGenr.append("- Título\n");
			validadorGnr = false;
		}

		// Descripción obligatoria
		if (txtDescr.getValue() == null || txtDescr.getValue().trim().isEmpty()) {
			camposDscrpGenr.append("- Sinopsis\n");
			validadorGnr = false;
		}

		// Al menos una palabra clave
		int palabrasClaves = 0;
		for (Component txbKey : gridPalabrasClave.getRows().getChildren()
				.get(0).getChildren()) {
			if (!((Textbox) txbKey).getValue().isEmpty()) {
				palabrasClaves++;
			}
		}
		if (palabrasClaves <= 0) {
			camposDscrpGenr.append("- Palabras clave\n");
			validadorGnr = false;
		}

		// Al menos un género
		if (!(treeGenre.getSelectedItems() != null && !treeGenre
				.getSelectedItems().isEmpty())) {
			camposDscrpGenr.append("- Género\n");
			validadorGnr = false;
		}

		// Al menos un crédito
		if (!(lstCreditos != null && !lstCreditos.isEmpty())) {
			camposDscrpGenr.append("- Créditos\n");
			validadorGnr = false;
		}

		if (!validadorGnr) {
			validador = false;
			campos.append("*Descripción general del recurso:\n").append(
					camposDscrpGenr.toString());
		}

		/*
		 * Valores obligatorios en características educativas
		 */
		StringBuilder camposCrcEdu = new StringBuilder();

		if (cbxTipoInteractividad.getSelectedItem() == null) {
			camposCrcEdu.append("- Tipo de interactividad\n");
			validadorCrcEdu = false;
		}

		if (!(lstbxTipoRecurso.getSelectedItems() != null && !lstbxTipoRecurso
				.getSelectedItems().isEmpty())) {
			camposCrcEdu.append("- Tipo de recurso\n");
			validadorCrcEdu = false;
		}

		if (txtEducationalUse.getValue() == null
				|| txtEducationalUse.getValue().trim().isEmpty()) {
			camposCrcEdu.append("- Uso educativo\n");
			validadorCrcEdu = false;
		}

		// if(!(treeEducationalContext.getSelectedItems()!=null &&
		// !treeEducationalContext.getSelectedItems().isEmpty())){
		if (!(lstbxEducationalContext.getSelectedItems() != null && !lstbxEducationalContext
				.getSelectedItems().isEmpty())) {
			camposCrcEdu.append("- Contexto\n");
			validadorCrcEdu = false;
		}

		if (!(lstbxEducationalRole.getSelectedItems() != null && !lstbxEducationalRole
				.getSelectedItems().isEmpty())) {
			camposCrcEdu.append("- Rol del usuario objetivo\n");
			validadorCrcEdu = false;
		}

		if (!(treeAgeRange.getSelectedItems() != null && !treeAgeRange
				.getSelectedItems().isEmpty())) {
			camposCrcEdu.append("- Rango de edad típico\n");
			validadorCrcEdu = false;
		}

		if (!(lstbxEduIdiomas.getSelectedItems() != null && !lstbxEduIdiomas
				.getSelectedItems().isEmpty())) {
			camposCrcEdu.append("- Idioma\n");
			validadorCrcEdu = false;
		}

		// Al menos una anotación
		if (!(lstEduAnnotations != null && !lstEduAnnotations.isEmpty())) {
			camposCrcEdu.append("- Anotaciones\n");
			validadorCrcEdu = false;
		}

		if (!(treeAbility.getSelectedItems() != null && !treeAbility
				.getSelectedItems().isEmpty())) {
			camposCrcEdu.append("- Competencias\n");
			validadorCrcEdu = false;
		}

		if (!validadorCrcEdu) {
			validador = false;
			campos.append("*Características educativas del recurso:\n").append(
					camposCrcEdu.toString());
		}

		if (validador) {
			return null;
		} else {
			return campos.toString();
		}
	}

	private void limpiarFormulario() throws Exception {
		txtTitulo.setValue("");
		txtTituloCorto.setValue("");
		txtDescr.setValue("");

		for (Iterator<Component> iter = gridPalabrasClave.getRows()
				.getChildren().iterator(); iter.hasNext();) {
			@SuppressWarnings("unused")
			final Row row = (Row) iter.next();
			iter.remove();
		}
		nuevaFilaPalabrasClave(gridPalabrasClave);

		treeGenre.setSelectedItem(null);
		lstbxIdiomas.setSelectedItem(null);
		dbxFechaProd.setValue(null);
		//txtProdCountry.setValue("");
		lstbxProdCountry.setSelectedItem(null);

		for (Iterator<Component> iter = gridLugaresRepresentados.getRows()
				.getChildren().iterator(); iter.hasNext();) {
			@SuppressWarnings("unused")
			final Row row = (Row) iter.next();
			iter.remove();
		}
		nuevaFilaPalabrasClave(gridLugaresRepresentados);

		treeSitiosRepresentados.setSelectedItem(null);
		//txtEpocaRepresentada.setValue("");
		ibxDurationH.setValue(null);
		ibxDurationM.setValue(null);
		ibxDurationS.setValue(null);

		// Limpiar características educativas
		cbxTipoInteractividad.setSelectedItem(null);
		lstbxTipoRecurso.setSelectedItem(null);
		txtEducationalUse.setValue("");
		// treeEducationalContext.setSelectedItem(null);
		lstbxEducationalContext.setSelectedItem(null);
		lstbxEducationalRole.setSelectedItem(null);
		treeAgeRange.setSelectedItem(null);
		lstbxEduIdiomas.setSelectedItem(null);
		lstbxAnnotations.setSelectedItem(null);
		treeAbility.setSelectedItem(null);

		// Limpiar listas
		lstEduAnnotations.clear();// limpiar la lista de anotaciones
		lstCreditos.clear();
		segmentos.clear();
	}

	/**
	 * M&eacute;todo que dimensiona los componentes de la ventana de acuerdo a
	 * los par&aacute;metros de inicio
	 * 
	 * @author Angela Vargas
	 */
	private void dimensionarVentana() {
		// TAMAÑO DE LA VENTANA
		winDescribirContenido.setWidth(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ANCHO_ESCRITORIO) - 190)
				.append("px").toString());
		winDescribirContenido.setHeight(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ALTO_ESCRITORIO) - 5)
				.append("px").toString());

		// TAMAÑO DEL CONTENEDOR IZQUIERDO (FORMULARIOS PARA EL INGRESO DE LOS
		// METADATOS)
		divFormularios.setWidth(new StringBuilder()
				.append(((Integer) session
						.getAttribute(Constantes.ANCHO_ESCRITORIO) - 215) / 2)																	
				.append("px").toString());
		divFormularios.setHeight(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ALTO_ESCRITORIO) - 65)
				.append("px").toString());

		// TAMAÑO DEL FORMULARIO PARA METADATOS DE DESCRIPCIÓN GENERAL
		grpFormGnrl
				.setWidth(new StringBuilder()
						.append(((Integer) session
								.getAttribute(Constantes.ANCHO_ESCRITORIO) - 215) / 2 - 20)														
						.append("px").toString());
		divFormGnrl.setHeight(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ALTO_ESCRITORIO) - 215)																		
				.append("px").toString());

		// TAMAÑO DEL FORMULARIO PARA METADATOS EDUCATIVOS
		grpFormEdu
				.setWidth(new StringBuilder()
						.append(((Integer) session
								.getAttribute(Constantes.ANCHO_ESCRITORIO) - 215) / 2 - 20)																							
						.append("px").toString());
		divFormEdu.setHeight(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ALTO_ESCRITORIO) - 215)
																		
				.append("px").toString());

		// TAMAÑO DEL ESPACIO PARA LA MARCACIÓN DE SEGMENTOS
		grpSegmentos
				.setWidth(new StringBuilder()
						.append(((Integer) session
								.getAttribute(Constantes.ANCHO_ESCRITORIO) - 215) / 2 - 20)																							
						.append("px").toString());
		divSegmentos.setHeight(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ALTO_ESCRITORIO) - 215)																		
				.append("px").toString());

		// TAMAÑO DEL ESPACIO PARA VISUALIZACIÓN DE VIDEO
		grpGrafico.setWidth(new StringBuilder()
				.append(((Integer) session
						.getAttribute(Constantes.ANCHO_ESCRITORIO) - 215) / 2)																				
				.append("px").toString());
		divGrafico.setHeight(new StringBuilder()
				.append((Integer) session
						.getAttribute(Constantes.ALTO_ESCRITORIO) - 115)
				.append("px").toString());
		chartSegmentos.setWidth(500);

		// TAMAÑO DEL ESPACIO PARA VISUALIZACIÓN DE XML
		// txtVerXML.setWidth(new StringBuilder()
		// .append(((Integer) session
		// .getAttribute(Constantes.ANCHO_ESCRITORIO) - 55) / 2)
		// .append("px").toString());
		// txtVerXML.setHeight(new StringBuilder()
		// .append((Integer) session
		// .getAttribute(Constantes.ALTO_ESCRITORIO) - 120)
		// .append("px").toString());
	}

	/*@SuppressWarnings("unchecked")
	public void onAnnotationCreated(Event event) {
		HashMap<String, Object> map = (HashMap<String, Object>) event.getData();
		String annotation = (String) map.get("annotation");

		TextualType annotationTT = new TextualType();
		annotationTT.setValue(annotation);
		annotationTT.setLang("es");

		lstEduAnnotations.add(annotationTT);

		ListModelList annotationsModel = new ListModelList(lstEduAnnotations);
		annotationsModel.setMultiple(true);
		lstbxAnnotations.setModel(annotationsModel);
		lstbxAnnotations.setItemRenderer(this);
	}*/

	@SuppressWarnings("unchecked")
	public void onSegmentCreated(Event event) {
		winSegmentIsOpen = false;
		
		HashMap<String, Object> map = (HashMap<String, Object>) event.getData();
		segmentos = (List<SegmentInformationType>) map.get("segmentos");
		SegmentInformationType segmento = (SegmentInformationType) map
				.get("segmento");

		ListModelList segmentosModel = new ListModelList(segmentos);
		segmentosModel.setMultiple(true);
		lstbxSgmts.setModel(segmentosModel);
		lstbxSgmts.setItemRenderer(this);

		// AÑADIR SEGMENTO AL GRÁFICO
		// Tiempos
		java.time.Duration tInicio = java.time.Duration.parse(segmento
				.getSegmentLocator().getMediaRelTimePoint().getValue());
		java.time.Duration duration = java.time.Duration.parse(segmento
				.getSegmentLocator().getMediaDuration());
		java.time.Duration tFinal = tInicio.plus(duration);
		chartSegmentos.setVisible(true);
		model.addValue(segmento.getDescription().getTitle().get(0).getValue()
				.trim(), tInicio.toMinutes(), tFinal.toMinutes());
		chartSegmentos.setModel(model);
	}
	
	public void onCloseWinSegment(Event event) {
		winSegmentIsOpen = false;
	}

	@SuppressWarnings("unchecked")
	public void onCreditsCreated(Event event) {
		HashMap<String, Object> map = (HashMap<String, Object>) event.getData();
		lstCreditos = (List<CreditsItemType>) map.get("credits");
	}

	/**
	 * Pinta el gráfico correspondiente a los segmentos
	 * 
	 * @author Angela Vargas
	 */
	@SuppressWarnings("unchecked")
	private void pintarGraficoSegmentos() {
		chartSegmentos.getXAxis().setCategories("SEGMENTO");
		chartSegmentos.getYAxis().setTitle("Tiempo ( min )");
		chartSegmentos.getTooltip().setValueSuffix("min");

		DataLabels dataLabels = chartSegmentos.getPlotOptions()
				.getColumnRange().getDataLabels();
		dataLabels.setEnabled(true);
		dataLabels.setFormat("{point.y}min");

		// hide the legend.
		chartSegmentos.getLegend().setEnabled(false);

		// ocultar la opción de exportar gráfico
		chartSegmentos.getExporting().setEnabled(false);
		// ocultar la etiqueta del eje x
		chartSegmentos.getXAxis().getLabels().setEnabled(false);

		// Añadir evento clic para cada segmento
		chartSegmentos.addEventListener(ChartsEvents.ON_PLOT_CLICK,
				new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						for (SegmentInformationType segment : segmentos) {
							if (segment
									.getDescription()
									.getTitle()
									.get(0)
									.getValue()
									.equalsIgnoreCase(
											((ChartsEvent) event).getSeries()
													.getName())) {

								HashMap map = new HashMap<String, SegmentInformationType>();
								map.put("segment", segment);
								Window window = (Window) Executions
										.createComponents("segmento.zul", null,
												map);
								window.doModal();
							}
						}
					}
				});
	}

	/**
	 * Agrega una fila de textbox para ingresar palabras claves
	 * 
	 * @author Angela Vargas
	 */
	@SuppressWarnings("unused")
	private void nuevaFilaPalabrasClave(Grid grid) {
		Row row = new Row();
		for (Component column : grid.getColumns().getChildren()) {
			Textbox textbox = new Textbox();
			textbox.setWidth("95%");
			row.getChildren().add(textbox);
		}
		row.setParent(grid.getRows());
	}

	/**
	 * 
	 * @param lstbx
	 * @param cbx
	 */
	private void setVisibleLstbxValuesOnCombobox(Listbox lstbx, Bandbox cbx) {
		StringBuilder valorVisible = new StringBuilder(64);
		if (lstbx.getSelectedItems() != null
				&& !lstbx.getSelectedItems().isEmpty()) {
			if (lstbx.getSelectedItem().getValue() instanceof Locale) {
				valorVisible.append(((Locale) lstbx.getSelectedItem()
						.getValue()).toString());
			} else if(lstbx.getSelectedItem().getValue() instanceof CountryCode){
				valorVisible.append(((CountryCode) lstbx.getSelectedItem()
						.getValue()).getName());
			} else{
				valorVisible.append(((Classification) lstbx.getSelectedItem()
						.getValue()).getName());
			}

			if (lstbx.getSelectedItems().size() > 1) {
				valorVisible.append(",...");
			}
		}
		cbx.setValue(valorVisible.toString());
	}
	
	private void setVisibleLstbxValuesOnCombobox(List lst, Bandbox cbx) {
		StringBuilder valorVisible = new StringBuilder(64);
		if (lst != null	&& !lst.isEmpty()) {
			if (lst.get(0) instanceof Locale) {
				valorVisible.append(((Locale) lst.get(0)).toString());
			} else if(lst.get(0) instanceof CountryCode){
				valorVisible.append(((CountryCode) lst.get(0)).getName());
			} 

			if (lst.size() > 1) {
				valorVisible.append(",...");
			}
		}
		cbx.setValue(valorVisible.toString());
	}

	/**
	 * Método que extrae las clasificaciones de una clasificación para
	 * agregarlas a un nodo (para el render del árbol).
	 * 
	 * @param classificationLst
	 * @param rootNode
	 * @author Angela Vargas
	 */
	private void extractClasificationTreeNode(
			List<Classification> classificationLst,
			ClassificationTreeNode rootNode) {
		// Recorro la lista de clasificaciones
		for (Classification c : classificationLst) {
			// Cada clasificación se representa por un TreeNode:
			ClassificationTreeNode classificationNode = new ClassificationTreeNode(
					c);
			// Si la clasificación tiene otra lista de clasificaciones, se le
			// hace el mismo proceso:
			if (c.getClassifications() != null
					&& !c.getClassifications().isEmpty()) {
				extractClasificationTreeNode(c.getClassifications(),
						classificationNode);
			}
			// else {
			// classificationNode = new ClassificationTreeNode(c,false);
			// }
			rootNode.add(classificationNode);
		}
	}

	private void changeGroupboxCaptionArrow(Groupbox grp) {
		if (grp.isOpen()) {
			grp.getCaption().setImage("/imgs/arrow_up.png");
		} else {
			grp.getCaption().setImage("/imgs/arrow_down.png");
		}
	}

	/*
	 * ------SECCIÓN RENDER------
	 */

	/**
	 * Render para los combobox que contienen valores controlados de un
	 * classificationScheme
	 * 
	 * @author Angela Vargas
	 */
	@Override
	public void render(Comboitem item, Object data, int index) throws Exception {
		item.setLabel(((Classification) data).getName());
		item.setValue(data);

		if (!((Classification) data).getDefinition().isEmpty()) {
			item.setTooltiptext(((Classification) data).getDefinition());
		}
	}

	/**
	 * Render para los listbox
	 * 
	 * @author Angela Vargas
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void render(final Listitem item, Object data, int index)
			throws Exception {
		if (data instanceof Locale) {
			final Locale locale = (Locale) data;
			item.setValue(locale);

			Listcell identificadorCell = new Listcell();
			identificadorCell.setParent(item);
			Label lblIdentificador = new Label(locale.toString());
			lblIdentificador.setParent(identificadorCell);
			lblIdentificador.setWidth("100%");

			Listcell nombreCell = new Listcell();
			nombreCell.setParent(item);
			Label lblnombre = new Label(locale.getDisplayName());
			lblnombre.setParent(nombreCell);
			lblnombre.setWidth("100%");

			Listcell countryCell = new Listcell();
			countryCell.setParent(item);
			Label lblCountry = new Label(locale.getDisplayCountry());
			lblCountry.setParent(countryCell);
			lblCountry.setWidth("100%");
			
			item.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event arg0) throws Exception {
					if(item.getParent().getId().equalsIgnoreCase(lstbxIdiomas.getId())){
						if(item.isSelected()){
							localesLstSelect.add(locale);
						}else{
							localesLstSelect.remove(locale);
						}
						onSelect$lstbxIdiomas();
					}else if (item.getParent().getId().equalsIgnoreCase(lstbxEduIdiomas.getId())) {
						if(item.isSelected()){
							localesEduLstSelect.add(locale);
						}else{
							localesEduLstSelect.remove(locale);
						}
						onSelect$lstbxEduIdiomas();
					}					
				}
			});
			
		} else if (data instanceof SegmentInformationType) {
			final SegmentInformationType segment = (SegmentInformationType) data;
			item.setValue(segment);

			Listcell checkCell = new Listcell();
			checkCell.setParent(item);

			Listcell titleCell = new Listcell();
			titleCell.setParent(item);
			Label lblTitle = new Label(segment.getDescription().getTitle()
					.get(0).getValue());
			lblTitle.setParent(titleCell);
			lblTitle.setWidth("100%");

			// Tiempos
			java.time.Duration tInicio = java.time.Duration.parse(segment
					.getSegmentLocator().getMediaRelTimePoint().getValue());
			java.time.Duration duration = java.time.Duration.parse(segment
					.getSegmentLocator().getMediaDuration());
			java.time.Duration tFinal = tInicio.plus(duration);

			Listcell timeICell = new Listcell();
			timeICell.setParent(item);
			Label lblTimeI = new Label(tInicio.toString().substring(2,
					tInicio.toString().length()));
			lblTimeI.setParent(timeICell);
			lblTimeI.setWidth("100%");

			Listcell timeFCell = new Listcell();
			timeFCell.setParent(item);
			Label lblTimeF = new Label(tFinal.toString().substring(2,
					tFinal.toString().length()));
			lblTimeF.setParent(timeFCell);
			lblTimeF.setWidth("100%");

			Listcell descriptionCell = new Listcell();
			descriptionCell.setParent(item);
			A descriptionLink = new A("Ver Descripción");
			descriptionLink.setParent(descriptionCell);
			descriptionLink.addEventListener(Events.ON_CLICK,
					new EventListener() {
						public void onEvent(Event arg0) throws Exception {
							HashMap map = new HashMap<String, SegmentInformationType>();
							map.put("segment", segment);
							Window window = (Window) Executions
									.createComponents("segmento.zul", null, map);
							window.doModal();
						}
					});
		} else if (data instanceof Classification) {
			Classification classification = (Classification) data;
			item.setValue(data);

			item.setTooltiptext(classification.getDefinition());
			
			Listcell checkCell = new Listcell();
			checkCell.setParent(item);

			Listcell classificationCell = new Listcell();
			classificationCell.setParent(item);
			Label lblClassification = new Label(classification.getName());
			lblClassification.setParent(classificationCell);
			lblClassification.setWidth("100%");
		} else if (data instanceof TextualType) {
			TextualType textualType = (TextualType) data;
			item.setValue(textualType);

			Listcell textCell = new Listcell();
			textCell.setParent(item);
			Label lblText = new Label(textualType.getValue());
			lblText.setParent(textCell);
			lblText.setWidth("100%");
		}else if (data instanceof CountryCode){
			final CountryCode country = (CountryCode) data;
			item.setValue(country);

			Listcell codCell = new Listcell();
			codCell.setParent(item);
			Label lblCod = new Label(country.getAlpha2());
			lblCod.setParent(codCell);
			lblCod.setWidth("100%");
			
			Listcell countryCell = new Listcell();
			countryCell.setParent(item);
			Label lblCountry = new Label(country.getName());
			lblCountry.setParent(countryCell);
			lblCountry.setWidth("100%");
			
			item.addEventListener(Events.ON_CLICK, new EventListener() {
				public void onEvent(Event arg0) throws Exception {
					if(item.isSelected()){
						countryLstSelect.add(country);
					}else{
						countryLstSelect.remove(country);
					}
					onSelect$lstbxProdCountry();				
				}
			});
			
//			for (int i = 0; i < CountryCode.values().length; i++) {
//				if (CountryCode.values()[i].getAlpha3() != null) {
//					System.out.println(CountryCode.values()[i].getName() + " - "
//							+ CountryCode.values()[i].getAlpha2() + " - "
//							+ CountryCode.values()[i].getAlpha3());
//				}
//			}
		}
	}

	/**
	 * Render para el árbol que contiene los géneros definidos en TVA
	 * 
	 * @author Angela Vargas
	 */
	@Override
	public void render(final Treeitem treeItem, Object data, int index)
			throws Exception {

		ClassificationTreeNode treeNode = (ClassificationTreeNode) data;
		Classification classification = (Classification) treeNode.getData();

		treeItem.setValue(classification);
		// debe estar abierto para que realice la carga de todo el arbol desde
		// el inicio y poder al evento ON_CLICK definido para cada uno.
		treeItem.setOpen(true);

		Treerow dataRow = new Treerow();
		dataRow.setParent(treeItem);
		dataRow.setTooltiptext(classification.getDefinition());
		dataRow.appendChild(new Treecell(classification.getName()));

		dataRow.addEventListener(Events.ON_CLICK, new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {

				if (treeItem.isSelected()) {
					// Seleccionar los hijos
					if (((Classification) treeItem.getValue())
							.getClassifications() != null
							&& !((Classification) treeItem.getValue())
									.getClassifications().isEmpty()) {
						selectTreeitemChildren(treeItem, true);
					}
				} else {
					// Se des-seleccionan sus hijos
					if (((Classification) treeItem.getValue())
							.getClassifications() != null
							&& !((Classification) treeItem.getValue())
									.getClassifications().isEmpty()) {
						selectTreeitemChildren(treeItem, false);
					}
				}

				// Seleccionar o des-seleccionar los nodos padre
				if (treeItem.getParent() != null && // el padre siempre es de
													// tipo TreeChildren
						treeItem.getParent().getParent() != null && // El padre
																	// del padre
																	// puede ser
																	// un
																	// treeItem
						treeItem.getParent().getParent() instanceof Treeitem) { // Si
																				// el
																				// padre
																				// del
																				// padre
																				// es
																				// un
																				// treeitem
					if (treeItem.isSelected()) {// si el item se está
												// seleccionando
						// Se seleccionan sus padres
						selectTreeitemParent(treeItem, true);
					} else {
						selectTreeitemParent(treeItem, false);
					}
				}
			}

			private void selectTreeitemParent(Treeitem treeItem, boolean select) {
				if (treeItem.getParent().getParent() instanceof Treeitem) {
					Treeitem treeitemParent = (Treeitem) treeItem.getParent()
							.getParent();
					if (select) {
						// Verificar si todos sus hermanos están seleccionados
						// para seleccionar el padre
						if (areAllChildrenSelected(treeitemParent)) {
							treeitemParent.setSelected(select);
							selectTreeitemParent(treeitemParent, select);
						}
					} else {
						// Des-seleccionar a los padres
						treeitemParent.setSelected(select);
						if (treeitemParent.getParent().getParent() != null
								&& treeitemParent.getParent().getParent() instanceof Treeitem) {
							selectTreeitemParent(treeitemParent, select);
						}
					}
				}
			}

			private boolean areAllChildrenSelected(Treeitem parent) {
				boolean validador = true;

				for (Component component : parent.getChildren()) {
					if (component instanceof Treechildren) {
						for (Treeitem co : ((Treechildren) component)
								.getItems()) {
							if (!co.isSelected()) {
								validador = false;
							}
						}
					}
				}
				return validador;
			}

			private void selectTreeitemChildren(Treeitem treeItem,
					boolean select) {
				for (Component comp : treeItem.getChildren()) {
					if (comp instanceof Treechildren
							&& ((Treechildren) comp).getItems() != null
							&& !((Treechildren) comp).getItems().isEmpty()) {
						for (Component c : ((Treechildren) comp).getItems()) {
							if (c instanceof Treeitem) {
								((Treeitem) c).setSelected(select);
								selectTreeitemChildren((Treeitem) c, select);
							}
						}
					}
				}
			}
		});

	}

}
