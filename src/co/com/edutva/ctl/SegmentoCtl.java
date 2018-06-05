package co.com.edutva.ctl;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;

//import co.com.edutva.tva2v616.GenreType;
//import co.com.edutva.tva2v616.KeywordType;
//import co.com.edutva.tva2v616.SegmentInformationType;

import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treechildren;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;

import co.com.edutva.bd.Classification;
import co.com.edutva.edutvav1.ControlledTermType;
import co.com.edutva.edutvav1.EducationalAudienceType;
import co.com.edutva.edutvav1.EducationalContextAttributesType;
import co.com.edutva.edutvav1.EducationalResourceType;
import co.com.edutva.edutvav1.EducationalResultsType;
import co.com.edutva.edutvav1.ExtendedSegmentDescriptionType;
import co.com.edutva.edutvav1.GenreType;
import co.com.edutva.edutvav1.KeywordType;
import co.com.edutva.edutvav1.LanguageType;
import co.com.edutva.edutvav1.MediaRelTimePointType;
import co.com.edutva.edutvav1.SegmentInformationType;
import co.com.edutva.edutvav1.SynopsisType;
import co.com.edutva.edutvav1.TVAMediaTimeType;
import co.com.edutva.edutvav1.TermNameType;
import co.com.edutva.edutvav1.TextualType;
import co.com.edutva.edutvav1.TitleType;
import co.com.edutva.ngc.VrblSstmNgc;
import co.com.edutva.utl.Constantes;
import co.com.edutva.utl.LectorCS;
import co.com.edutva.utl.LocaleComparator;

@SuppressWarnings("rawtypes")
public class SegmentoCtl extends GenericForwardComposer implements
ComboitemRenderer, TreeitemRenderer, ListitemRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3416200163129525093L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	protected Window winSegmento;
	protected Div divPrincipal;
	
	protected Window parentWindow;
	
	protected Grid gridView;
	protected Textbox txtViewTituloSgmt;
	protected Textbox txtViewTiempoInicial;
	protected Textbox txtViewTiempoFinal;
	protected Textbox txtViewDescrSgmt;
	protected Textbox txtViewPalabrasClave;
	protected Textbox txtViewGenreSgmt;
	protected Textbox txtViewTipoInteractividadSgmt;
	protected Textbox txtViewTipoRecursoSgmt;
	protected Textbox txtViewEducationalUse;
	protected Textbox txtViewEducationalContext;
	protected Textbox txtViewEducationalRole;
	protected Textbox txtViewAgeRange;
	protected Textbox txtViewLanguage;
	protected Listbox lstbxViewAnnotations;
	protected Textbox txtViewAbilitySgmt;
	
	protected Grid gridCreate;
	protected Textbox txtTituloSgmt;
	protected Textbox txtDescrSgmt;
	protected Grid gridPalabrasClaveSgmt;
	protected Tree treesGenreSgmt;
	protected Bandbox cbxIdiomaSgmt;
	protected Intbox ibxInicioSgmtH;
	protected Intbox ibxInicioSgmtM;
	protected Intbox ibxInicioSgmtS;
	protected Intbox ibxFinSgmtH;
	protected Intbox ibxFinSgmtM;
	protected Intbox ibxFinSgmtS;

	///.----------educativo------------	
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
	protected Listbox lstbxEducationalContext;
	
	protected Bandbox cbxEducationalRole;
	protected Listbox lstbxEducationalRole;
	protected Tree treeAgeRange;
	protected Bandbox cbxEduIdioma;
	protected Listbox lstbxEduIdiomas;
	protected Textbox txtEduFiltroIdiomas;
	
	protected Listbox lstbxAnnotations;
	
	protected Tree treeAbility;	
	
	protected Button btnAceptar;
	protected Button btnCancelar;	
	
	private SegmentInformationType segment;
	public List<SegmentInformationType> segmentos;
	
	public Set<Treeitem> generos;
	public List<Locale> idiomas;
	
	public List<TextualType> lstEduAnnotations;
	protected Textbox txtAnnotation;

	public List<Locale> localesLst;//lista todos los idiomas
	public List<Locale> localesEduLstSelect; //lista de los idiomas seleccionados en caract educativas
		
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
		segment = (SegmentInformationType)execution.getArg().get("segment");
		segmentos = (List<SegmentInformationType>)execution.getArg().get("segmentos");
		parentWindow = (Window) execution.getArg().get("parentWindow");
		
		//campos para heredar del recurso:
		generos = (Set<Treeitem>)execution.getArg().get("generos");
		//idiomas = (Set<Listitem>)execution.getArg().get("idiomas");
		idiomas = (List<Locale>)execution.getArg().get("idiomas");
		
		localesEduLstSelect = new ArrayList<>();
		
		lstEduAnnotations = new ArrayList<TextualType>();
	}
	
	@SuppressWarnings("unchecked")
	public void onCreate$winSegmento() {
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Ingresando a crear/ver segmento ")
					.append(self.getId()));
		try {		
			if(segment!=null){
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
				
				winSegmento.setTitle(new StringBuilder().append("Descripción del segmento educativo: ").append(segment.getDescription().getTitle().get(0).getValue()).toString());
				gridView.setVisible(true);
								
				//Título
				txtViewTituloSgmt.setValue(segment.getDescription().getTitle().get(0).getValue());
				
				//Tiempos
				Duration tInicio = Duration.parse(segment.getSegmentLocator().getMediaRelTimePoint().getValue());
				Duration duration = Duration.parse(segment.getSegmentLocator().getMediaDuration());
				Duration tFinal = tInicio.plus(duration);
				
				txtViewTiempoInicial.setValue(tInicio.toString().substring(2, tInicio.toString().length()));
				txtViewTiempoFinal.setValue(tFinal.toString().substring(2, tFinal.toString().length()));
				
				//Descripción
				txtViewDescrSgmt.setValue(segment.getDescription().getSynopsis().get(0).getValue());
				
				//Palabras clave
				StringBuilder keywords = new StringBuilder(64);
				for(KeywordType keyword : segment.getDescription().getKeyword()){
					keywords.append(keyword.getValue()).append(", ");
				}
				txtViewPalabrasClave.setValue(keywords.toString().substring(0, keywords.length()-2));
				
				//Géneros
				if(segment.getDescription().getGenre()!=null && !segment.getDescription().getGenre().isEmpty()){
					StringBuilder genres = new StringBuilder(64);
					for(GenreType genre : segment.getDescription().getGenre()){
						genres.append(genre.getName().getValue()).append("\n");
					}
					txtViewGenreSgmt.setValue(genres.toString());
				}
				
				EducationalContextAttributesType educationalAttr = (EducationalContextAttributesType)((ExtendedSegmentDescriptionType)segment.getDescription()).getContextAttributes().get(0);
				
				//Tipo de interactividad
				if(educationalAttr.getEducationalResource().getInteractivityType()!=null ){
					txtViewTipoInteractividadSgmt.setValue(educationalAttr.getEducationalResource().getInteractivityType().getName().getValue());
				}
				//Tipo de recurso
				if(educationalAttr.getEducationalResource().getEducationalType()!=null && !educationalAttr.getEducationalResource().getEducationalType().isEmpty()){
					StringBuilder tipos = new StringBuilder(64);
					for(ControlledTermType tipo : educationalAttr.getEducationalResource().getEducationalType()){
						tipos.append(tipo.getName().getValue()).append(", ");
					}
					txtViewTipoRecursoSgmt.setValue(tipos.substring(0, tipos.length()-2));
				}

				//Uso educativo
				if(educationalAttr.getEducationalResource().getEducationalUse()!=null ){
					txtViewEducationalUse.setValue(((TextualType)educationalAttr.getEducationalResource().getEducationalUse().get(0)).getValue());
				}
				
				//Contexto
				if(educationalAttr.getEducationalContext()!=null && !educationalAttr.getEducationalContext().isEmpty()){
					StringBuilder contextos = new StringBuilder(64);
					for(ControlledTermType context : educationalAttr.getEducationalContext()){
						contextos.append(context.getName().getValue()).append(", ");
					}
					txtViewEducationalContext.setValue(contextos.substring(0, contextos.length()-2));
				}
				
				//Rol de usuario obj
				if(educationalAttr.getEducationalAudience().getEducationalRole()!=null && !educationalAttr.getEducationalAudience().getEducationalRole().isEmpty()){
					StringBuilder roles = new StringBuilder(64);
					for(ControlledTermType role : educationalAttr.getEducationalAudience().getEducationalRole()){
						roles.append(role.getName().getValue()).append(", ");
					}
					txtViewEducationalRole.setValue(roles.substring(0, roles.length()-2));
				}
				
				//Rango de edad
				if(educationalAttr.getEducationalAudience().getTypicalAgeRange()!=null && !educationalAttr.getEducationalAudience().getTypicalAgeRange().isEmpty()){
					StringBuilder edades = new StringBuilder(64);
					for(ControlledTermType edad : educationalAttr.getEducationalAudience().getTypicalAgeRange()){
						edades.append(edad.getName().getValue()).append(", ");
					}
					txtViewAgeRange.setValue(edades.substring(0, edades.length()-2));
				}
				
				//Idioma
				if(educationalAttr.getEducationalAudience().getLanguage()!=null && !educationalAttr.getEducationalAudience().getLanguage().isEmpty()){
					StringBuilder idiomas = new StringBuilder(64);
					for(LanguageType idioma : educationalAttr.getEducationalAudience().getLanguage()){
						idiomas.append(idioma.getValue()).append(", ");
					}
					txtViewLanguage.setValue(idiomas.substring(0, idiomas.length()-2));
				}

				// Anotaciones
				if(educationalAttr.getAnnotation()!=null && !educationalAttr.getAnnotation().isEmpty()){
					ListModelList annotationModel = new ListModelList(educationalAttr.getAnnotation());
					lstbxViewAnnotations.setModel(annotationModel);
					lstbxViewAnnotations.setItemRenderer(this);
				}
				
				//Competencias
				if(educationalAttr.getEducationalResults().getAbility()!=null && !educationalAttr.getEducationalResults().getAbility().isEmpty()){
					StringBuilder abilities = new StringBuilder(64);
					for(ControlledTermType ability : educationalAttr.getEducationalResults().getAbility()){
						abilities.append(ability.getName().getValue()).append("\n");
					}
					txtViewAbilitySgmt.setValue(abilities.toString());
				}
			}else {
				// TAMAÑO DE LA VENTANA
//				divPrincipal.setHeight(new StringBuilder()
//				.append((Integer) session
//						.getAttribute(Constantes.ALTO_ESCRITORIO)-60)
//				.append("px").toString());
				
				gridCreate.setVisible(true);
				grpMetadataEduResource.setVisible(true);
				grpMetadataEduContext.setVisible(true);
				grpMetadataEduAudience.setVisible(true);
				grpMetadataEduAnotation.setVisible(true);
				grpMetadataEduResults.setVisible(true);
				
				// Lectura de classification schemes
				LectorCS lector = new LectorCS();
				
				// Agrego fila para ingreso de palabras clave en la sección segmento
				Rows rowsPalabrasClaveSgmt = new Rows();
				rowsPalabrasClaveSgmt.setParent(gridPalabrasClaveSgmt);
				nuevaFilaPalabrasClave(gridPalabrasClaveSgmt);
				
				// Datos de EducationalUseCS
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_USE)).toString());
				ListModelList tiposRecEduModel = new ListModelList(lector.getElements());
				tiposRecEduModel.setMultiple(true);
				lstbxTipoRecurso.setModel(tiposRecEduModel);
				lstbxTipoRecurso.setItemRenderer(this);
				
				// Datos de EducationalContextCS
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_CONTEXT)).toString());
				ListModelList classificationModelContext = new ListModelList(lector.getElements());
				classificationModelContext.setMultiple(true);
				lstbxEducationalContext.setModel(classificationModelContext);
				lstbxEducationalContext.setItemRenderer(this);
				
				// Datos IntendedEducationalUserCS
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_INTENDED_EDUCATIONAL_USER)).toString());
				ListModelList educationalRoleModel = new ListModelList(lector.getElements());
				educationalRoleModel.setMultiple(true);
				lstbxEducationalRole.setModel(educationalRoleModel);
				lstbxEducationalRole.setItemRenderer(this);
				
				// Datos de AbilityCS
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_ABILITY)).toString());
				ClassificationTreeNode rootNodeAbility = new ClassificationTreeNode(null); 
				extractClasificationTreeNode(lector.getElements(), rootNodeAbility);	
				DefaultTreeModel<Classification> classificationModelAbility = new DefaultTreeModel<Classification>(rootNodeAbility);
				classificationModelAbility.setMultiple(true);
				treeAbility.setModel(classificationModelAbility);
				treeAbility.setItemRenderer(this);
				
				// Datos de AgeRangeCS
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_AGE_RANGE)).toString());
				ClassificationTreeNode rootNodeAgeRange = new ClassificationTreeNode(null); 
				extractClasificationTreeNode(lector.getElements(), rootNodeAgeRange);	
				DefaultTreeModel<Classification> classificationModelAgeRange = new DefaultTreeModel<Classification>(rootNodeAgeRange);
				classificationModelAgeRange.setMultiple(true);
				treeAgeRange.setModel(classificationModelAgeRange);
				treeAgeRange.setItemRenderer(this);
				
				// Datos InteractivityCS
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_INTERACTIVITY_TYPE)).toString());
				ListModelList interactivityType = new ListModelList(lector.getElements());
				cbxTipoInteractividad.setModel(interactivityType);
				cbxTipoInteractividad.setItemRenderer(this);
								
				// Árbol que contiene los géneros definidos en el estándar TVA
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_CONTENT)).toString());
				ClassificationTreeNode rootNodeGenre = new ClassificationTreeNode(null); 
				extractClasificationTreeNode(lector.getElements(), rootNodeGenre);	
				DefaultTreeModel<Classification> classificationModelGenreSgmnt = new DefaultTreeModel<Classification>(rootNodeGenre);
				classificationModelGenreSgmnt.setMultiple(true);
				treesGenreSgmt.setModel(classificationModelGenreSgmnt); //Para la sección de segmentos
				treesGenreSgmt.setItemRenderer(this);
				
				// Combobox que contiene los idiomas
				Locale locales[] = SimpleDateFormat.getAvailableLocales();
				//List<Locale> localesLst = new ArrayList<Locale>();
				localesLst = new ArrayList<Locale>();
				for (int i = 1; i < locales.length; i++) {// el primero viene vacío
					localesLst.add(locales[i]);
				}
				
				localesLst.sort(new LocaleComparator(true, 1));
				
				ListModelList eduIdiomasModel = new ListModelList(localesLst);
				eduIdiomasModel.setMultiple(true);
				lstbxEduIdiomas.setModel(eduIdiomasModel);
				lstbxEduIdiomas.setItemRenderer(this);
				lstbxEduIdiomas.renderAll();
				
				cargarDatosHeredados();
			}	
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cargarDatosHeredados(){
		
		final boolean heredarGeneros;
		final boolean heredarIdiomas;
		
		if(generos!=null && !generos.isEmpty()){
			heredarGeneros = true;
		}else {
			heredarGeneros = false;
		}
		
		if(idiomas!=null && !idiomas.isEmpty()){
			heredarIdiomas = true;
		}else {
			heredarIdiomas = false;
		}
		
		//if(generos!=null && !generos.isEmpty()){
		if(heredarGeneros){
			winSegmento.setVisible(false);
			Messagebox.show(
					"¿Desea heredar los géneros establecidos para todo el recurso?",
					"Heredar géneros", Messagebox.OK | Messagebox.NO,
					Messagebox.QUESTION, new EventListener() {
						public void onEvent(Event evt) throws InterruptedException {
							if (evt.getName().equals("onOK")) {
								for (Treeitem genreItem : generos) {
									for (Treeitem treeitem : treesGenreSgmt.getItems()) {
										if (((Classification) treeitem.getValue())
												.getName()
												.trim()
												.equalsIgnoreCase(((Classification)genreItem.getValue()).getName().trim())) {
											treeitem.setSelected(true);
										}
									}
								}
								if(!heredarIdiomas){
									winSegmento.setVisible(true);
								}
								
							}else {
								if(!heredarIdiomas){
									winSegmento.setVisible(true);
								}
							}
						}
					});
		}
		
		//if(idiomas!=null && !idiomas.isEmpty()){
		if(heredarIdiomas){
			winSegmento.setVisible(false);
			Messagebox.show(
				"¿Desea heredar los idiomas de la audiencia objetivo establecidos para todo el recurso?",
				"Heredar idiomas", Messagebox.OK | Messagebox.NO,
				Messagebox.QUESTION, new EventListener() {
					public void onEvent(Event evt) throws InterruptedException {
						if (evt.getName().equals("onOK")) {
							for (Locale idioma : idiomas) {
								for (Listitem listitem : lstbxEduIdiomas.getItems()) {
									if (((Locale) listitem.getValue())
											.toString()
											.equalsIgnoreCase(idioma.toString())) {
										listitem.setSelected(true);
										localesEduLstSelect.add((Locale) listitem.getValue());
									}
								}
							}
							onSelect$lstbxEduIdiomas();
							winSegmento.setVisible(true);
						}else {
							winSegmento.setVisible(true);
						}
					}
				});
		}
		
	}
	
	/**
	 * M&eacute;todo que se encarga de agregar una fila para permitir el ingreso
	 * de m&aacute palabras clave de un segmento
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnAgregarFilaPalabrasClaveSgmt() {
		nuevaFilaPalabrasClave(gridPalabrasClaveSgmt);
	}
	
	/**
	 * M&eacute;todo que se encarga de eliminar una fila de ingreso de palabras clave de un segmento
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnEliminarFilaPalabrasClaveSgmt() {
		if (gridPalabrasClaveSgmt.getRows().getChildren().size() != 1) {
			gridPalabrasClaveSgmt.getRows().removeChild(
					gridPalabrasClaveSgmt.getRows().getLastChild());
		}
	}
	
	@SuppressWarnings("unchecked")
	public void onChange$txtEduFiltroIdiomas(){	
		String lang = txtEduFiltroIdiomas.getText().toLowerCase();
		
		List<Locale> localesLstTmp = new ArrayList<Locale>();
		   
        for (Iterator<Locale> i = localesLst.iterator(); i.hasNext();) {
        	Locale tmp = i.next();
            if (tmp.getDisplayName().toLowerCase().contains(lang)) {
            	localesLstTmp.add(tmp);
            }
        }
        
        localesLstTmp.sort(new LocaleComparator(true, 1));

		ListModelList idiomasModel = new ListModelList(localesLstTmp);
		idiomasModel.setMultiple(true);
		lstbxEduIdiomas.setModel(idiomasModel);
		lstbxEduIdiomas.setItemRenderer(this);
		lstbxEduIdiomas.renderAll();
		
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
	
	/**
	 * 
	 */
	public void onSelect$lstbxEduIdiomas(){	
//		setVisibleLstbxValuesOnCombobox(lstbxEduIdiomas, cbxEduIdioma);
		setVisibleLstbxValuesOnCombobox(localesEduLstSelect, cbxEduIdioma);
	}
	
	/**
	 * 
	 */
	public void onSelect$lstbxTipoRecurso(){	
		setVisibleLstbxValuesOnCombobox(lstbxTipoRecurso, cbxTipoRecurso);
	}
	
	/**
	 * 
	 */
	public void onSelect$lstbxEducationalRole(){	
		setVisibleLstbxValuesOnCombobox(lstbxEducationalRole, cbxEducationalRole);
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
	
	@SuppressWarnings("unchecked")
	public void onClick$btnAddAnnotation(){
		try {
			/*HashMap map = new HashMap<String, List<SegmentInformationType>>();
			map.put("parentWindow", winSegmento);
			Window window = (Window)Executions.createComponents(
	                "annotation.zul", null, map);
	        window.doModal();*/  
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
	public void onClick$btnDeleteAnnotation(){
		try {
			if (!lstbxAnnotations.getSelectedItems().isEmpty()){
				for(Listitem listitem : lstbxAnnotations.getSelectedItems()){
					TextualType annotation = listitem.getValue();
					// ELIMINAR DEL ARCHIVO DESCRIPTOR
					lstEduAnnotations.remove(annotation);
				}
				
				// VOLVER A RENDERIZAR
				ListModelList annotationsModel = new ListModelList(lstEduAnnotations);
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
	 * Evento que cierra la ventana y cancela la operación de crear un segmento.
	 * @throws InterruptedException
	 */
	public void onClick$btnCancelar() throws InterruptedException {
		self.detach();
		Events.sendEvent(new Event("onCloseWinSegment", parentWindow, null));
	}
	
	/**
	 * Evento que crea un segmento con la información ingresada en el formulario
	 * 
	 * @author Angela Vargas
	 */
	public void onClick$btnAceptar() {
		try {
			String validador = validarCamposObligatoriosSgmt();
			
			if (validador==null) {
				if(validarNombreSgmt(txtTituloSgmt.getValue().trim())){
					// AÑADIR SEGMENTO AL ARCHIVO DESCRIPTOR
					segment = new SegmentInformationType();
					segment.setSegmentId(String.valueOf(segment.hashCode()));//id obligatorio para el segmento, lo genero a partir del id del objeto
					//BasicSegmentDescriptionType segmentDescriptionType = new BasicSegmentDescriptionType();
					ExtendedSegmentDescriptionType segmentDescriptionType = new ExtendedSegmentDescriptionType();
					
					// Título del segmento:
					TitleType titleType = new TitleType();
					titleType.setValue(txtTituloSgmt.getValue().trim());
					segmentDescriptionType.getTitle().add(titleType);
					
					// Ubicación del segmento
					java.time.Duration jInicioSgmnt = java.time.Duration.ZERO;
					if(ibxInicioSgmtH.getValue()!=null){
						jInicioSgmnt = jInicioSgmnt.plus(java.time.Duration.ofHours(ibxInicioSgmtH.getValue().longValue()));
					}
					if(ibxInicioSgmtM.getValue()!=null){
						jInicioSgmnt  = jInicioSgmnt.plus(java.time.Duration.ofMinutes(ibxInicioSgmtM.getValue().longValue()));
					}
					if(ibxInicioSgmtS.getValue()!=null){
						jInicioSgmnt  = jInicioSgmnt.plus(java.time.Duration.ofSeconds(ibxInicioSgmtS.getValue().longValue()));
					}
					MediaRelTimePointType inicioSgmnt = new MediaRelTimePointType();
					inicioSgmnt.setValue(jInicioSgmnt.toString());
					TVAMediaTimeType tiemposSgmt = new TVAMediaTimeType();
					tiemposSgmt.setMediaRelTimePoint(inicioSgmnt); //tiempo de inicio
					
					java.time.Duration jFinSgmnt = java.time.Duration.ZERO;
					if(ibxFinSgmtH.getValue()!=null){
						jFinSgmnt = jFinSgmnt.plus(java.time.Duration.ofHours(ibxFinSgmtH.getValue().longValue()));
					}
					if(ibxFinSgmtM.getValue()!=null){
						jFinSgmnt  = jFinSgmnt.plus(java.time.Duration.ofMinutes(ibxFinSgmtM.getValue().longValue()));
					}
					if(ibxFinSgmtS.getValue()!=null){
						jFinSgmnt  = jFinSgmnt.plus(java.time.Duration.ofSeconds(ibxFinSgmtS.getValue().longValue()));
					}
					
					java.time.Duration duration = jFinSgmnt.minus(jInicioSgmnt);
					tiemposSgmt.setMediaDuration(duration.toString()); //duración
					
					segment.setSegmentLocator(tiemposSgmt);
					
					// Descripción del segmento
					SynopsisType synopsisType = new SynopsisType();
					synopsisType.setValue(txtDescrSgmt.getValue().trim());
					segmentDescriptionType.getSynopsis().add(synopsisType);
	
					// Palabras clave
					for (Component rowKey : gridPalabrasClaveSgmt.getRows().getChildren()) {
						for (Component txbKey : rowKey.getChildren()) {
							if (!((Textbox) txbKey).getValue().isEmpty()) {
								KeywordType keywordType = new KeywordType();
								keywordType.setValue(((Textbox) txbKey).getValue());
								if (segmentDescriptionType.getKeyword().isEmpty()) {
									keywordType.setType(Constantes.MAIN_KEYWORD);
								} else if (segmentDescriptionType.getKeyword().size() == 1) {
									keywordType.setType(Constantes.SECOND_KEYWORD);
								} else {
									keywordType.setType(Constantes.OTHER_KEYWORD);
								}
								segmentDescriptionType.getKeyword().add(keywordType);
							}
						}
					}
	
					// Género
					Set<Treeitem> genreSelectedItems = (Set<Treeitem>) treesGenreSgmt.getSelectedItems();
					for(Treeitem item : genreSelectedItems){
						GenreType newGenre = new GenreType();
						StringBuilder href = new StringBuilder(64).append("urn:tva:metadata:cs:ContentCS:2011:");
						href.append(((Classification)item.getValue()).getTermID());
						newGenre.setHref(href.toString());
						TermNameType genreName = new TermNameType();
						genreName.setValue(((Classification)item.getValue()).getName());
						newGenre.setName(genreName);
						segmentDescriptionType.getGenre().add(newGenre);
						
					}
					
					// LA INFORMACIÓN EDUCATIVA ES UN ATRIBUTO DE CONTEXTO DEL SEGMENTO
					segmentDescriptionType.getContextAttributes().add(crearInfoEducativa());
					
					segment.setDescription(segmentDescriptionType);
					segmentos.add(segment);
					
					final HashMap<String, Object> map = new HashMap<String, Object>();
			        map.put("segmentos", segmentos);
			        map.put("segmento", segment);
					Messagebox.show("Segmento educativo creado exitosamente.", "Información", Messagebox.OK, Messagebox.INFORMATION);
					self.detach();
			        Events.sendEvent(new Event("onSegmentCreated", parentWindow, map));
				}else {
					Messagebox.show("Ya existe un segmento con el mismo nombre, por favor ingrese un nombre diferente.", "Error", Messagebox.OK, Messagebox.ERROR);
					txtTituloSgmt.setValue("");
				}
			}else {
				Messagebox.show(new StringBuilder().append("Los siguientes campos obligatorios no han sido ingresados: \n").append(validador).toString(), "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
			
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
	
	/**
	 * Método que obtiene los metadatos educativos ingresados por el usuario
	 * 
	 * @author Angela Vargas
	 * @return EducationalContextAttributesType eAttributesType
	 */
	private EducationalContextAttributesType crearInfoEducativa()
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

		// Instancia de EducationalResourceType para agregar los metadatos relacionados con el recurso educativo
		EducationalResourceType educationalResourceType = new EducationalResourceType();
		eAttributesType.setEducationalResource(educationalResourceType);
		
		// PARA AGREGAR EL TIPO DE INTERACTIVIDAD
		if(cbxTipoInteractividad.getSelectedItem()!=null){
			ControlledTermType interactivityTypeCT = new ControlledTermType();
			lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_INTERACTIVITY_TYPE)).toString());
			StringBuilder href = new StringBuilder(lector.getUri()).append(":");
			href.append(((Classification)cbxTipoInteractividad.getSelectedItem().getValue()).getTermID());
			interactivityTypeCT.setHref(href.toString());
			TermNameType nmbInteractivity = new TermNameType();
			nmbInteractivity.setValue(((Classification)cbxTipoInteractividad.getSelectedItem().getValue()).getName());
			interactivityTypeCT.setName(nmbInteractivity);
			educationalResourceType.setInteractivityType(interactivityTypeCT);
		}
		
		// PARA AGREGAR EL TIPO DE RECURSO
		if(lstbxTipoRecurso.getSelectedItems()!=null && !lstbxTipoRecurso.getSelectedItems().isEmpty()){
			for(Listitem item : lstbxTipoRecurso.getSelectedItems()){
				ControlledTermType educationalUseCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_USE)).toString());
				StringBuilder href = new StringBuilder(lector.getUri()).append(":");
				href.append(((Classification)item.getValue()).getTermID());
				educationalUseCT.setHref(href.toString());
				TermNameType nmbEducationalUse = new TermNameType();
				nmbEducationalUse.setValue(((Classification)item.getValue()).getName());
				educationalUseCT.setName(nmbEducationalUse);
				educationalResourceType.getEducationalType().add(educationalUseCT);
			}			
		}
		
		// PARA AGREGAR EL USO EDUCATIVO
		if(!txtEducationalUse.getValue().trim().isEmpty()){
			TextualType educationalUseTT = new TextualType();
			educationalUseTT.setValue(txtEducationalUse.getValue().trim());
			educationalUseTT.setLang("es");
			educationalResourceType.getEducationalUse().add(educationalUseTT);		
		}
		
		// PARA AGREGAR EL CONTEXTO EDUCATIVO
		if(lstbxEducationalContext.getSelectedItems()!=null && !lstbxEducationalContext.getSelectedItems().isEmpty()){
			for(Listitem item : lstbxEducationalContext.getSelectedItems()){
				ControlledTermType eduContextCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_EDUCATIONAL_CONTEXT)).toString());
				StringBuilder href = new StringBuilder(lector.getUri()).append(":");
				href.append(((Classification)item.getValue()).getTermID());
				eduContextCT.setHref(href.toString());
				TermNameType nmbContext = new TermNameType();
				nmbContext.setValue(((Classification)item.getValue()).getName());
				eduContextCT.setName(nmbContext);
				eAttributesType.getEducationalContext().add(eduContextCT);
			}
		}
			
		// Instancia de EducationalAudienceType para agregar los metadatos relacionados con la audiencia educativa
		EducationalAudienceType educationalAudienceType = new EducationalAudienceType();
		eAttributesType.setEducationalAudience(educationalAudienceType);
		
		// PARA AGREGAR EL ROL DEL USUARIO
		if(lstbxEducationalRole.getSelectedItems()!=null && !lstbxEducationalRole.getSelectedItems().isEmpty()){
			for(Listitem item : lstbxEducationalRole.getSelectedItems()){
				ControlledTermType educationalRoleCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_INTENDED_EDUCATIONAL_USER)).toString());
				StringBuilder href = new StringBuilder(lector.getUri()).append(":");
				href.append(((Classification)item.getValue()).getTermID());
				educationalRoleCT.setHref(href.toString());
				TermNameType nmbEducationalRole = new TermNameType();
				nmbEducationalRole.setValue(((Classification)item.getValue()).getName());
				educationalRoleCT.setName(nmbEducationalRole);
				educationalAudienceType.getEducationalRole().add(educationalRoleCT);
			}			
		}
		
		// PARA AGREGAR EL RANGO DE EDAD TÍPICO
		if(treeAgeRange.getSelectedItems()!=null && !treeAgeRange.getSelectedItems().isEmpty()){
			for(Treeitem item : treeAgeRange.getSelectedItems()){
				ControlledTermType ageRangeCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_AGE_RANGE)).toString());
				StringBuilder href = new StringBuilder(lector.getUri()).append(":");
				href.append(((Classification)item.getValue()).getTermID());
				ageRangeCT.setHref(href.toString());
				TermNameType nmbAgeRange = new TermNameType();
				nmbAgeRange.setValue(((Classification)item.getValue()).getName());
				ageRangeCT.setName(nmbAgeRange);
				educationalAudienceType.getTypicalAgeRange().add(ageRangeCT);
			}
		}
		
		// PARA AGREGAR EL IDIOMA DEL USUARIO OBJETIVO
		/*if(lstbxEduIdiomas.getSelectedItems()!=null && !lstbxEduIdiomas.getSelectedItems().isEmpty()){
			for(Listitem item : lstbxEduIdiomas.getSelectedItems()){
				LanguageType languaje = new LanguageType();
				languaje.setValue(((Locale) item.getValue()).toString().replace("_", "-"));	
				educationalAudienceType.getLanguage().add(languaje);
			}			
		}*/
		if (localesEduLstSelect != null	&& !localesEduLstSelect.isEmpty()) {
			for (Locale locale : localesEduLstSelect) {
				LanguageType languaje = new LanguageType();
				languaje.setValue(locale.toString().replace("_", "-"));
				educationalAudienceType.getLanguage().add(languaje);
			}
		}
		
		// PARA AGREGAR LA DESCRIPCIÓN EDUCATIVA (anotaciones)
		if(lstEduAnnotations!=null && !lstEduAnnotations.isEmpty()){
			eAttributesType.getAnnotation().addAll(lstEduAnnotations);
		}

		// Instancia de EducationalResultsType para agregar los metadatos relacionados con la audiencia educativa
		EducationalResultsType educationalResultsType = new EducationalResultsType();
		eAttributesType.setEducationalResults(educationalResultsType);
		
		// PARA AGREGAR LAS HABILIDADES
		if(treeAbility.getSelectedItems()!=null && !treeAbility.getSelectedItems().isEmpty()){
			for(Treeitem item : treeAbility.getSelectedItems()){
				ControlledTermType abilityCT = new ControlledTermType();
				lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_ABILITY)).toString());
				StringBuilder href = new StringBuilder(lector.getUri()).append(":");
				href.append(((Classification)item.getValue()).getTermID());
				abilityCT.setHref(href.toString());
				TermNameType nmbAbility = new TermNameType();
				nmbAbility.setValue(((Classification)item.getValue()).getName());
				abilityCT.setName(nmbAbility);
				educationalResultsType.getAbility().add(abilityCT);
			}
		}
	
		return eAttributesType;
	}
	
	/*@SuppressWarnings("unchecked")
	public void onAnnotationCreated(Event event){
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
	
	/**
	 * Método que verifica si existe algún segmento que tenga el nombre especificado en los parámetros.
	 * 
	 * @author Angela Vargas
	 * @param nombreSgmt
	 * @return
	 */
	private boolean validarNombreSgmt(String nombreSgmt) {
		boolean validador = true;
		if(segmentos!=null && !segmentos.isEmpty()){
			for(SegmentInformationType segment : segmentos){
				if(segment.getDescription().getTitle().get(0).getValue().equalsIgnoreCase(nombreSgmt)){
					validador = false;
				}
			}
		}
		return validador;
	}
	
	/**
	 * M&eacute;todo que valida que los campos obligatorios sean ingresados por el usuario
	 * 
	 * @author Angela Vargas
	 * @return 
	 */
	private String validarCamposObligatoriosSgmt() {
		boolean validador = true;
		StringBuilder campos = new StringBuilder();	
		
		// Título obigatorio
		if(txtTituloSgmt.getValue()==null || txtTituloSgmt.getValue().trim().isEmpty()){
			campos.append("- Título\n");
			validador = false;
		}
		
		// Descripción obligatoria
		if (txtDescrSgmt.getValue()==null || txtDescrSgmt.getValue().trim().isEmpty()) {
			campos.append("- Sinopsis\n");
			validador = false;
		}

		// Al menos una palabra clave
		int palabrasClaves =0;
		for(Component txbKey : gridPalabrasClaveSgmt.getRows().getChildren().get(0).getChildren()){
			if (!((Textbox) txbKey).getValue().isEmpty()) {
				palabrasClaves ++;
			}
		}
		if(palabrasClaves<=0){
			campos.append("- Palabras clave\n");
			validador = false;
		}		
		
		// Tiempo de inicio
		int nulos = 0;
		if(ibxInicioSgmtH.getValue()==null){
			nulos++;
		}
		if(ibxInicioSgmtM.getValue()==null){
			nulos++;
		}
		if(ibxInicioSgmtS.getValue()==null){
			nulos++;
		}	
		if(nulos==3){
			campos.append("- Tiempo inicial\n");
			validador = false;
		}
		
		if(cbxTipoInteractividad.getSelectedItem()==null){
			campos.append("- Tipo de interactividad\n");
			validador = false;
		}
		
		if(!(lstbxTipoRecurso.getSelectedItems()!=null && !lstbxTipoRecurso.getSelectedItems().isEmpty())){
			campos.append("- Tipo de recurso\n");
			validador = false;
		}
		
		if(txtEducationalUse.getValue()==null || txtEducationalUse.getValue().trim().isEmpty()){
			campos.append("- Uso educativo\n");
			validador = false;
		}
		
		if(!(lstbxEducationalContext.getSelectedItems()!=null && !lstbxEducationalContext.getSelectedItems().isEmpty())){
			campos.append("- Contexto\n");
			validador = false;
		}
		
		if(!(lstbxEducationalRole.getSelectedItems()!=null && !lstbxEducationalRole.getSelectedItems().isEmpty())){
			campos.append("- Rol del usuario objetivo\n");
			validador = false;
		}
		
		if(!(treeAgeRange.getSelectedItems()!=null && !treeAgeRange.getSelectedItems().isEmpty())){
			campos.append("- Rango de edad típico\n");
			validador = false;
		}
		
		if(!(lstbxEduIdiomas.getSelectedItems()!=null && !lstbxEduIdiomas.getSelectedItems().isEmpty())){
			campos.append("- Idioma\n");
			validador = false;
		}
		
		// Al menos una anotación
		if(!(lstEduAnnotations!=null && !lstEduAnnotations.isEmpty())){
			campos.append("- Anotaciones\n");
			validador = false;
		}
		
		if(!(treeAbility.getSelectedItems()!=null && !treeAbility.getSelectedItems().isEmpty())){
			campos.append("- Competencias\n");
			validador = false;
		}
		
		if(validador){
			return null;
		}else {
			return campos.toString();
		}
	}
	
	/**
	 * Limplia al formulario de ingreso de parámetros para un segmento
	 * 
	 * @author Angela Vargas
	 */
//	private void limpiarFormularioSegmento() {
//		txtTituloSgmt.setValue("");
//		txtDescrSgmt.setValue("");
//		treesGenreSgmt.setSelectedItem(null);
//		ibxInicioSgmtH.setValue(null);
//		ibxInicioSgmtM.setValue(null);
//		ibxInicioSgmtS.setValue(null);
//		ibxFinSgmtH.setValue(null);
//		ibxFinSgmtM.setValue(null);
//		ibxFinSgmtS.setValue(null);
//		for (Iterator<Component> iter=gridPalabrasClaveSgmt.getRows().getChildren().iterator(); iter.hasNext();) {
//			  @SuppressWarnings("unused")
//			final Row row = (Row) iter.next();
//			  iter.remove(); 
//			}
//		nuevaFilaPalabrasClave(gridPalabrasClaveSgmt);	
//		
//		//Limpieza de los componentes educativos del segmento
//		fg
//	}
	
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
	 * Método que extrae las clasificaciones de una clasificación para agregarlas a un nodo (para el render del árbol).
	 * 
	 * @param classificationLst
	 * @param rootNode
	 * @author Angela Vargas
	 */
	private void extractClasificationTreeNode(List<Classification> classificationLst, ClassificationTreeNode rootNode) {
		// Recorro la lista de clasificaciones
		for(Classification c : classificationLst){
			// Cada clasificación se representa por un TreeNode:
			ClassificationTreeNode classificationNode = new ClassificationTreeNode(c);
			// Si la clasificación tiene otra lista de clasificaciones, se le hace el mismo proceso:
			if(c.getClassifications()!=null && !c.getClassifications().isEmpty()){
				extractClasificationTreeNode(c.getClassifications(), classificationNode);
			}
			rootNode.add(classificationNode);	
		}
	}
	
	/**
	 * 
	 * @param lstbx
	 * @param cbx
	 */
	private void setVisibleLstbxValuesOnCombobox(Listbox lstbx, Bandbox cbx) {
		StringBuilder valorVisible = new StringBuilder(64);
		if(lstbx.getSelectedItems()!=null && !lstbx.getSelectedItems().isEmpty()){
			if(lstbx.getSelectedItem().getValue() instanceof Locale){
				valorVisible.append(((Locale)lstbx.getSelectedItem().getValue()).toString());
			}else {
				valorVisible.append((((Classification)lstbx.getSelectedItem().getValue()).getName()).toString());
			}
			
			if(lstbx.getSelectedItems().size()>1){
				valorVisible.append(",...");
			}
		}
		cbx.setValue(valorVisible.toString());
	}
	
	private void setVisibleLstbxValuesOnCombobox(List lst, Bandbox cbx) {
		StringBuilder valorVisible = new StringBuilder(64);
		if (lst != null	&& !lst.isEmpty()) {	
			valorVisible.append(((Locale) lst.get(0)).toString());
			if (lst.size() > 1) {
				valorVisible.append(",...");
			}
		}
		cbx.setValue(valorVisible.toString());
	}
	
	private void changeGroupboxCaptionArrow(Groupbox grp) {
		if(grp.isOpen()){
			grp.getCaption().setImage("/imgs/arrow_up.png");
		}else {
			grp.getCaption().setImage("/imgs/arrow_down.png");
		}
	}

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
		
		if(!((Classification) data).getDefinition().isEmpty()){
			item.setTooltiptext(((Classification) data).getDefinition());
		}
	}
	
	/**
	 * Render para el árbol que contiene los géneros definidos en TVA
	 * 
	 * @author Angela Vargas
	 */
	@Override
	public void render(final Treeitem treeItem, Object data, int index) throws Exception {	
		
		ClassificationTreeNode treeNode = (ClassificationTreeNode) data;
		Classification classification = (Classification) treeNode.getData();

		treeItem.setValue(classification);
		//debe estar abierto para que realice la carga de todo el arbol desde el inicio y poder al evento ON_CLICK definido para cada uno.
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

	@SuppressWarnings("unchecked")
	@Override
	public void render(final Listitem item, Object data, int index) throws Exception {
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
					if(item.isSelected()){
						localesEduLstSelect.add(locale);
					}else{
						localesEduLstSelect.remove(locale);
					}
					onSelect$lstbxEduIdiomas();
				}
			});
		}else if (data instanceof Classification) {
			Classification classification =  (Classification) data;
			item.setValue(data);
						
			Listcell checkCell = new Listcell();
			checkCell.setParent(item);
			
			Listcell classificationCell = new Listcell();
			classificationCell.setParent(item);
			Label lblClassification = new Label(classification.getName());
			lblClassification.setParent(classificationCell);
			lblClassification.setWidth("100%");
		}else if (data instanceof TextualType) {
			TextualType textualType =  (TextualType) data;
			item.setValue(textualType);
					
			Listcell textCell = new Listcell();
			textCell.setParent(item);
			Label lblText = new Label(textualType.getValue());
			lblText.setParent(textCell);
			lblText.setWidth("100%");
		}	
	}
}
