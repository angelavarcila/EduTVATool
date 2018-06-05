package co.com.edutva.ctl;

import java.util.HashMap;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import co.com.edutva.bd.Classification;
import co.com.edutva.edutvav1.CreditsItemType;
import co.com.edutva.edutvav1.NameComponentType;
import co.com.edutva.edutvav1.OrganizationNameType;
import co.com.edutva.edutvav1.PersonNameType;
import co.com.edutva.edutvav1.TextualType;
import co.com.edutva.ngc.VrblSstmNgc;
import co.com.edutva.utl.Constantes;
import co.com.edutva.utl.LectorCS;

@SuppressWarnings("rawtypes")
public class CreditoCtl extends GenericForwardComposer implements ComboitemRenderer, ListitemRenderer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8459908082341450120L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	protected Window winCredits;
	protected Listbox lstbxCredits;
	protected Radiogroup rdgrpPrsnOrgn;
	protected Radio rdbtnPrsn;
	protected Combobox cbxCreditsSelRol;
	protected Textbox txtNmbr;
	protected Textbox txtPrsnj;
	protected Div divNewCredit;
	
	public Window parentWindow;
	public List<CreditsItemType> lstCredits; 
	
	
	private VrblSstmNgc vrblSstmNgc;
	
	public VrblSstmNgc getVrblSstmNgc() {
		return vrblSstmNgc;
	}

	public void setVrblSstmNgc(VrblSstmNgc vrblSstmNgc) {
		this.vrblSstmNgc = vrblSstmNgc;
	}
	
	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);	
		lstCredits = (List<CreditsItemType>)execution.getArg().get("credits");
		parentWindow = (Window) execution.getArg().get("parentWindow");
	}
	
	@SuppressWarnings("unchecked")
	public void onCreate$winCredits() {
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Ingresando a crear/ver segmento ")
					.append(self.getId()));
		try {
			//Render para cŕeditos existentes
			ListModelList creditsModel = new ListModelList(lstCredits);
			creditsModel.setMultiple(true);
			lstbxCredits.setModel(creditsModel);
			lstbxCredits.setItemRenderer(this);
			
			// Lectura de classification schemes
			LectorCS lector = new LectorCS();

			// Datos de RolCS
			lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_ROLE)).toString());
			ListModelList rols = new ListModelList(lector.getElements());
			cbxCreditsSelRol.setModel(rols);
			cbxCreditsSelRol.setItemRenderer(this);

		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
	
	public void onClick$btnAddCredit(){
		divNewCredit.setVisible(true);
		winCredits.setWidth("830px");
	}
	
	@SuppressWarnings("unchecked")
	public void onClick$btnDeleteCredit(){
		try {
			if (!lstbxCredits.getSelectedItems().isEmpty()){
				for(Listitem listitem : lstbxCredits.getSelectedItems()){
					CreditsItemType credit = listitem.getValue();
					// ELIMINAR DEL ARCHIVO DESCRIPTOR
					lstCredits.remove(credit);
				}
				
				// VOLVER A RENDERIZAR
				ListModelList creditsModel = new ListModelList(lstCredits);
				creditsModel.setMultiple(true);
				lstbxCredits.setModel(creditsModel);
				lstbxCredits.setItemRenderer(this);
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
	}
	
	public void onCheck$rdgrpPrsnOrgn(){
		String tipo = rdgrpPrsnOrgn.getSelectedItem().getValue();
		if(tipo.equalsIgnoreCase("P")){
			//-Nombre
			txtPrsnj.setDisabled(false);
		}else if (tipo.equalsIgnoreCase("O")) {
			//-Organización
			txtPrsnj.setDisabled(true);
		}
	}
	
	public void onClick$btnCancelar(){
		//limpiar formulario
		txtNmbr.setValue(null);
		txtPrsnj.setValue(null);
		cbxCreditsSelRol.setSelectedItem(null);
		rdbtnPrsn.setSelected(true);
		txtPrsnj.setDisabled(false);
		//ocultar formulario
		divNewCredit.setVisible(false);
		//redimensionar ventana
		winCredits.setWidth("500px");
	}
	
	@SuppressWarnings("unchecked")
	public void onClick$btnAceptar(){
	
		if(rdgrpPrsnOrgn.getSelectedItem()!=null && !txtNmbr.getValue().isEmpty() && cbxCreditsSelRol.getSelectedItem()!=null){
			CreditsItemType credito = new CreditsItemType();
			
			//-Role seleccionado por el usuario y definido en RoleCS
//			TextualType presentationRole = new TextualType();
//			presentationRole.setValue(((Classification)cbxCreditsSelRol.getSelectedItem().getValue()).getName());
//			credito.getPresentationRole().add(presentationRole);
			// Campo obligatorio por CreditsItem que también indica el rol
			StringBuilder rolRef = new StringBuilder(64).append("urn:mpeg:mpeg7:cs:RoleCS:2011:");
			rolRef.append(((Classification) cbxCreditsSelRol.getSelectedItem().getValue()).getTermID());
			credito.setRole(rolRef.toString());
			
			//-Personaje 
			if(txtPrsnj.getValue()!=null && !txtPrsnj.getValue().isEmpty()){
				PersonNameType personaje = new PersonNameType();
				QName personajeGivenNameQName = new QName("urn:tva:mpeg7:2008","GivenName");
				JAXBElement<String> personajeGivenNameValue = new JAXBElement<String>(personajeGivenNameQName, String.class,txtPrsnj.getValue());
				personaje.getGivenNameOrLinkingNameOrFamilyName().add(personajeGivenNameValue);
				credito.getCharacter().add(personaje);
			}	
			
			//-Nombre/Organización
			 
//			String tipo = rdgrpPrsnOrgn.getSelectedItem().getValue();
//			if(tipo.equalsIgnoreCase("P")){
//				//-Nombre
//				PersonNameType persona = new PersonNameType();
//				QName personaGivenNameQName = new QName("GivenName");
//				JAXBElement<String> personaGivenNameValue = new JAXBElement<String>(personaGivenNameQName, String.class, txtNmbr.getValue());
//				persona.getGivenNameOrLinkingNameOrFamilyName().add(personaGivenNameValue);
//				QName personaQName = new QName("PersonName");
//				JAXBElement<PersonNameType> personaValue = new JAXBElement<PersonNameType>(personaQName, PersonNameType.class, persona);	
//				credito.getPersonNameOrPersonNameIDRefOrOrganizationName().add(personaValue);
//			}else if (tipo.equalsIgnoreCase("O")) {
//				//-Organización
//				OrganizationNameType organization = new OrganizationNameType();
//				organization.setValue(txtNmbr.getValue());
//				QName organizationQName = new QName("OrganizationName");
//				JAXBElement<OrganizationNameType> organizationValue = new JAXBElement<OrganizationNameType>(organizationQName, OrganizationNameType.class, organization);
//				credito.getPersonNameOrPersonNameIDRefOrOrganizationName().add(organizationValue);
//			}
			
			String tipo = rdgrpPrsnOrgn.getSelectedItem().getValue();
			if(tipo.equalsIgnoreCase("P")){
				//-Nombre
				PersonNameType persona = new PersonNameType();
				QName personaQName = new QName("urn:tva:metadata:2012","PersonName");		
				NameComponentType givenName = new NameComponentType();
				givenName.setValue(txtNmbr.getValue());
				QName givenNameQName = new QName("urn:tva:mpeg7:2008","GivenName");
				JAXBElement<NameComponentType> personaGivenNameValue = new JAXBElement<NameComponentType>(givenNameQName, NameComponentType.class, givenName);
			
				persona.getGivenNameOrLinkingNameOrFamilyName().add(personaGivenNameValue);
				
				JAXBElement<PersonNameType> personaValue = new JAXBElement<PersonNameType>(personaQName, PersonNameType.class, persona);
				credito.getPersonNameOrPersonNameIDRefOrOrganizationName().add(personaValue);
			}else if (tipo.equalsIgnoreCase("O")) {
				//-Organización
				OrganizationNameType organization = new OrganizationNameType();
				organization.setValue(txtNmbr.getValue());
				QName organizationQName = new QName("urn:tva:metadata:2012","OrganizationName");
				JAXBElement<OrganizationNameType> organizationValue = new JAXBElement<OrganizationNameType>(organizationQName, OrganizationNameType.class, organization);
				credito.getPersonNameOrPersonNameIDRefOrOrganizationName().add(organizationValue);
			}
			
		
			lstCredits.add(credito);
			
			//Render para cŕeditos existentes
			ListModelList creditsModel = new ListModelList(lstCredits);
			creditsModel.setMultiple(true);
			lstbxCredits.setModel(creditsModel);
			lstbxCredits.setItemRenderer(this);
			
			//limpiar formulario
			txtNmbr.setValue(null);
			txtPrsnj.setValue(null);
			cbxCreditsSelRol.setSelectedItem(null);
			rdbtnPrsn.setSelected(true);
			txtPrsnj.setDisabled(false);
			// ocultar formulario
			divNewCredit.setVisible(false);
			//redimensionar ventana
			winCredits.setWidth("500px");
		}else {
			Messagebox.show("Verifique que todos los campos obligatorios estén ingresados.", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	public void onClose$winCredits(){
		final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("credits", lstCredits);
		self.detach();
        Events.sendEvent(new Event("onCreditsCreated", parentWindow, map));
	}
	
	@Override
	public void render(Comboitem item, Object data, int index) throws Exception {
		item.setLabel(((Classification) data).getName());
		item.setValue(data);
		
		if(!((Classification) data).getDefinition().isEmpty()){
			item.setTooltiptext(((Classification) data).getDefinition());
		}	
	}

	@Override
	public void render(Listitem item, Object data, int index) throws Exception {
		CreditsItemType credit = (CreditsItemType) data;
		item.setValue(credit);
		
		Listcell checkCell = new Listcell();
		checkCell.setParent(item);
		
		Listcell prsOrgnCell = new Listcell();
		prsOrgnCell.setParent(item);
		
		Listcell nmbrOrgnNmCell = new Listcell();
		nmbrOrgnNmCell.setParent(item);
		
		if(credit.getPersonNameOrPersonNameIDRefOrOrganizationName().get(0).getValue() instanceof PersonNameType){
			prsOrgnCell.setLabel("Persona");
			nmbrOrgnNmCell.setLabel(((NameComponentType)((PersonNameType)credit.getPersonNameOrPersonNameIDRefOrOrganizationName().get(0).getValue()).getGivenNameOrLinkingNameOrFamilyName().get(0).getValue()).getValue());
		}else {
			prsOrgnCell.setLabel("Organización");
			nmbrOrgnNmCell.setLabel(((TextualType)credit.getPersonNameOrPersonNameIDRefOrOrganizationName().get(0).getValue()).getValue());
//			nmbrOrgnNmCell.setLabel(((OrganizationNameType)credit.getPersonNameOrPersonNameIDRefOrOrganizationName().get(0).getValue()).getValue());
		}
				
		Listcell selRolCell = new Listcell();
		//selRolCell.setLabel(((TextualType)credit.getPresentationRole().get(0)).getValue());
		String idRole = credit.getRole().replace("urn:mpeg:mpeg7:cs:RoleCS:2011:", "");
		LectorCS lector = new LectorCS();
		lector.setClassificationScheme(new StringBuilder(vrblSstmNgc.obtenerVrblSstm(Constantes.RUTA_CLASSIFICATION_SCHEMES)).append(vrblSstmNgc.obtenerVrblSstm(Constantes.CS_ROLE)).toString());
		for(Classification c : lector.getElements()){
			if(c.getTermID().equalsIgnoreCase(idRole)){
				selRolCell.setLabel(c.getName());		
			}
		}
		selRolCell.setParent(item);
		
		Listcell personajeCell = new Listcell();
		personajeCell.setParent(item);
		if(credit.getCharacter()!=null && !credit.getCharacter().isEmpty()){
//			personajeCell.setLabel((String)((PersonNameType)credit.getCharacter().get(0)).getGivenNameOrLinkingNameOrFamilyName().get(0).getValue());
			personajeCell.setLabel(((NameComponentType)((PersonNameType)credit.getCharacter().get(0)).getGivenNameOrLinkingNameOrFamilyName().get(0).getValue()).getValue());
		}
	}

}
