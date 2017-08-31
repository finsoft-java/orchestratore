package it.finsoft.manager;

public class UtilityCheck {

	/**
	 * Serve solamente a gestire eventuali errori di battitura semplici per es:
	 * spazi iniziali o finali, spazi intermedi, lettere minuscole. Elimina
	 * anche le virgole.
	 * 
	 */
	public String trimToUp(String s) {
		if (s == null)
			return "";
		s = s.trim().toUpperCase().replaceAll(" ", "").replaceAll(",", "");
		return s;
	}

	/**
	 * Serve solamente a gestire eventuali errori di battitura semplici per es:
	 * spazi iniziali o finali, spazi intermedi, lettere minuscole.
	 * 
	 */
	public String toUp(String s) {
		if (s == null)
			return "";
		s = s.trim().toUpperCase().replaceAll("  ", " ");
		return s;
	}
}
