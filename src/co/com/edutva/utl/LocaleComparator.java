package co.com.edutva.utl;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class LocaleComparator implements Comparator<Locale>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 122433776330322553L;

	private boolean asc = true;
    private int type = 0;
 
    public LocaleComparator(boolean asc, int type) {
        this.asc = asc;
        this.type = type;
    }
 
    public int getType() {
        return type;
    }
 
    public void setType(int type) {
        this.type = type;
    }
 
	@Override
	public int compare(Locale o1, Locale o2) {

		Locale locale1 = (Locale) o1;
		Locale locale2 = (Locale) o2;
		switch (type) {
		case 1: // Compare Display Name
			// Se comparan los nombres de los idiomas sin tener en cuenta los acentos.
			return remove(locale1.getDisplayName()).compareTo(remove(locale2.getDisplayName()))	* (asc ? 1 : -1);
		case 2: // Compare Country
			return locale1.getDisplayCountry().compareTo(locale2.getDisplayCountry())	* (asc ? 1 : -1);
		default: // ID
			return locale1.toString().compareTo(locale2.toString())	* (asc ? 1 : -1);
		}
	}

	@Override
	public Comparator<Locale> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Locale> thenComparing(Comparator<? super Locale> other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<Locale> thenComparing(
			Function<? super Locale, ? extends U> keyExtractor,
			Comparator<? super U> keyComparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<Locale> thenComparing(
			Function<? super Locale, ? extends U> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Locale> thenComparingInt(
			ToIntFunction<? super Locale> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Locale> thenComparingLong(
			ToLongFunction<? super Locale> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<Locale> thenComparingDouble(
			ToDoubleFunction<? super Locale> keyExtractor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Función que elimina acentos y caracteres especiales de
	 * una cadena de texto.
	 * @param input
	 * @return cadena de texto limpia de acentos y caracteres especiales.
	 */
	public static String remove(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i
	    return output;
	}
	
}
