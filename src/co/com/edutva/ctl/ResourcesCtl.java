package co.com.edutva.ctl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;

import co.com.edutva.bd.DscrRecurso;
import co.com.edutva.bd.Recurso;
import co.com.edutva.bd.Usuario;
import co.com.edutva.ngc.ResourceNgc;
import co.com.edutva.utl.Constantes;

@SuppressWarnings("rawtypes")
public class ResourcesCtl extends GenericForwardComposer implements RowRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6731307982040870924L;

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());
	
	protected Window winResources;
	protected Window parentWindow;
	protected Grid gridResources;
	protected Rows rowsResources;
	protected Radiogroup groupResources;
	
	private ResourceNgc resourceNgc;
	
	public ResourceNgc getResourceNgc() {
		return resourceNgc;
	}

	public void setResourceNgc(ResourceNgc resourceNgc) {
		this.resourceNgc = resourceNgc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		parentWindow = (Window) execution.getArg().get("parentWindow");
	}
	
	@SuppressWarnings("unchecked")
	public void onCreate$winResources(){
		if (logger.isDebugEnabled())
			logger.debug(new StringBuilder("Ingresando a lista de recursos")
					.append(self.getId()));
		try {
			//Obtener todos los recursos existentes para marcar
			List <Recurso> recursos = (List<Recurso>) resourceNgc.obtenerRecursos();			
			//Obtener los recursos marcados por el usuario
			List<DscrRecurso> recursosMarcados = resourceNgc.getDscrRecursoForUser((Usuario) session.getAttribute(Constantes.USUARIO));

			if (recursosMarcados != null && !recursosMarcados.isEmpty()) {
				for (Iterator<Recurso> iter = recursos.iterator(); iter.hasNext();) {
					Recurso recurso = iter.next();
					for(DscrRecurso recursoMarcado : recursosMarcados){
						if(recurso.getIdRecurso().intValue()==recursoMarcado.getRecurso().getIdRecurso().intValue()){
							//Si es el mismo recurso verifico si ya se finalizó la descripción
							if(recursoMarcado.getFnlDscrRecurso().booleanValue()){//recursoMarcado.isDescrito){
								iter.remove();
							}else {//no se ha finalizado la descripción cargo el xml del usuario
								recurso.setDscrpcnRecurso(recursoMarcado.getRutaDscrRecurso());
							}
							break;
						}
					}	
				}
			}

			if(recursos!=null && !recursos.isEmpty()){
				//La tabla tiene 4 columnas, entonces debo organizar los recursos en grupos de 4
				Double groups = new Double(recursos.size())/4;
				int grps = 0;
				if(groups>groups.intValue()){
					grps = groups.intValue()+1;
				}else {
					grps = groups.intValue();
				}
				
				List<List<Recurso>> recursosAgrupados = new ArrayList<List<Recurso>>();
				for (int i = 0; i < grps; i++) {
					int fromIndex = i*4;
					int toIndex = 0;
					if(i==grps-1){
						toIndex = recursos.size();
					}else {
						toIndex =((i+1)*4);	
					}
					List<Recurso> newList = recursos.subList(fromIndex, toIndex);
					recursosAgrupados.add(newList);
				}
				//Render de la tabla
				ListModelList recursosModel = new ListModelList(recursosAgrupados);
				gridResources.setModel(recursosModel);
				gridResources.setRowRenderer(this);
				
				
				groupResources.addEventListener(Events.ON_CHECK, new EventListener() {
					public void onEvent(Event arg0) throws Exception {						
						List<Radio> radioLst = groupResources.getItems();
						for (Radio rad : radioLst) {
							if(rad.isChecked()){
								Div divRadSel = (Div) rad.getParent();
								divRadSel.setStyle("background:#BDBDBD;");//#FFBF00---amarillo
							}else {
								Div divRad = (Div) rad.getParent();
								divRad.setStyle("");
							}
						}			
					}
				});
			}else {
				winResources.detach();
				Messagebox.show("Usted no tiene recursos por marcar.", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		} catch (Exception e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
		
	}
	
	public void onClick$btnCancelar(){
		self.detach();
	}
	
	public void onClick$btnAceptar(){
		if(groupResources.getSelectedItem()!=null){
			HashMap<String, Object> map = new HashMap<String, Object>();
	        map.put("resource", groupResources.getSelectedItem().getValue());
			self.detach();
	        Events.sendEvent(new Event("onSelectedResource", parentWindow, map));
		}else {
			Messagebox.show("Seleccione un recurso para marcar.", "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void render(Row row, Object data, int index) throws Exception {
		List<Recurso> recursos = (List<Recurso>) data;
		for(Recurso r :  recursos){
			final Div divRadio = new Div();
			divRadio.setAlign("center");
			divRadio.setParent(row);
			final Radio resourceRad = new Radio();
			resourceRad.setValue(r);
			resourceRad.setRadiogroup(groupResources);
			resourceRad.setParent(divRadio);//row);
			resourceRad.setLabel(r.getNmbrRecurso());
			AImage imgRad = new AImage(new File(r.getImgnRecurso()));
			resourceRad.setImageContent(imgRad);
		}
	}
}
