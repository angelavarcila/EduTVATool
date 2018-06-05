package co.com.edutva.utl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import co.com.edutva.bd.Classification;

public class LectorCS {

	/**
	 * Log de la aplicaci&oacute;n (log4j).
	 */
	private Logger logger = Logger.getLogger(this.getClass());

	private String classificationScheme;

	public LectorCS() {
		super();
	}

	public String getClassificationScheme() {
		return classificationScheme;
	}

	public void setClassificationScheme(String classificationScheme) {
		this.classificationScheme = classificationScheme;
	}

	/**
	 * 
	 * @return
	 */
	public List<Classification> getElements() {
		try {
			/* Creamos una instancia del archivo data.xml */
			File archivo = new File(classificationScheme);
			List<Classification> lstClassification= new ArrayList<Classification>();
			
			/* Creamos el documento xml a partir del archivo File */
			SAXBuilder constructorSAX = new SAXBuilder();

			/* Almacenara el documento XML */
			Document documento = (Document) constructorSAX.build(archivo);

			/*
			 * Obtenemos el nodo raiz o principal del documento ClassificationScheme
			 */
			Element nodoRaiz = documento.getRootElement();
			
			/*
			 * Obtenemos la lista de los nodos con la etiqueta "Term" que se
			 * encuentran en el nodo raiz
			 */
			List<Element> listaTerm = nodoRaiz.getChildren("Term");
			
			/*
			 * Extraemos los términos de los nodos anteriores
			 */
			extractTerms(lstClassification, listaTerm);
			
			return lstClassification;
		} catch (JDOMException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		} catch (IOException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
		return null;		
	}

	/**
	 * Método que extrae los términos que contiene una lista de elmentos extraidos de un xml
	 * 
	 * @param lstClassification
	 * @param listaTerm
	 */
	private void extractTerms(List<Classification> lstClassification,
			List<Element> listaTerm) {
		/*
		 * Recorremos esta lista 
		 */
		for (Element nodo : listaTerm) {
			
			Classification classification = new Classification();
			
			/* Obtenemos el atributo "termID" del nodo */
			String termID = nodo.getAttribute("termID").getValue();

			/*
			 * Obtenemos los datos almacenados en el subnodo "Name"
			 */
			List<Element> nombres = nodo.getChildren("Name");
			StringBuilder nombre = new StringBuilder(64);
			for (int i = 0; i < nombres.size(); i++) {
				nombre.append(nombres.get(i).getValue());
				if(i!=nombres.size()-1){
					nombre.append(", ");
				}
			}
		
			Element elmntDefinition = nodo.getChild("Definition");
			String definition ="";
			if(elmntDefinition!=null){
				definition = elmntDefinition.getValue();
			}
			
			List<Element> terms = nodo.getChildren("Term");
			if(terms!=null && !terms.isEmpty()){
				List<Classification> classifications = new ArrayList<Classification>();
				extractTerms(classifications, terms);
				classification.setClassifications(classifications);
			}
			
			classification.setName(nombre.toString());
			classification.setTermID(termID);
			classification.setDefinition(definition);
			
			lstClassification.add(classification);			
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUri() {
		try {
			/* Creamos una instancia del archivo data.xml */
			File archivo = new File(classificationScheme);
			
			/* Creamos el documento xml a partir del archivo File */
			SAXBuilder constructorSAX = new SAXBuilder();

			/* Almacenara el documento XML */
			Document documento = (Document) constructorSAX.build(archivo);

			/*
			 * Obtenemos el nodo raiz o principal del documento ClassificationScheme
			 */
			Element nodoRaiz = documento.getRootElement();
			
			String uri = nodoRaiz.getAttribute("uri").getValue();
	
			return uri;
		} catch (JDOMException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		} catch (IOException e) {
			logger.error(new StringBuilder(e.getClass().getName()).append(": ")
					.append(e.getMessage()), e);
		}
		return null;		
	}

}
